package com.spedire.Spedire.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spedire.Spedire.dtos.request.LoginRequest;
import com.spedire.Spedire.models.Role;
import com.spedire.Spedire.models.User;
import com.spedire.Spedire.repositories.UserRepository;
import com.spedire.Spedire.security.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static com.spedire.Spedire.AppUtils.ExceptionUtils.BADCREDENTIALSEXCEPTION;
import static java.time.Instant.now;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@Slf4j
public class SpedireAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final JwtUtils jwtUtil;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private String phoneNumber = null;

    private String password = null;

    @Override

    public Authentication attemptAuthentication (HttpServletRequest request, HttpServletResponse response){


        try {
            LoginRequest loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class);
            phoneNumber = loginRequest.getPhoneNumber();
            password = loginRequest.getPassword();
            Authentication authentication = new UsernamePasswordAuthenticationToken(phoneNumber, password);
            Authentication authResult = authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authResult);
            log.info(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString() + "this should be the phone number");
            return authResult;
        } catch (IOException e) {
            throw new BadCredentialsException(BADCREDENTIALSEXCEPTION);
        }
    }
    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                         FilterChain chain, Authentication authResult
                                         ) throws IOException {

        User user = userRepository.findUserByPhoneNumber(phoneNumber).get();
        String accessToken = jwtUtil.generateAccessToken(user,Role.SENDER);
        //String accessToken = generateAccessToken(authResult.getAuthorities(), request);
        response.setContentType(APPLICATION_JSON_VALUE);
        response.getOutputStream().write(objectMapper.writeValueAsBytes(Map.of("access_token", accessToken)));
     }

    private String generateAccessToken(Collection<? extends GrantedAuthority> authorities, HttpServletRequest request ) throws IOException {
        Map<String, String> map = new HashMap<>();
        int number = 1;
        for (GrantedAuthority authority:authorities) {
            map.put("role"+number, authority.getAuthority());
            number++;
        }
     //   Map<String, String> map = new HashMap<>();
    //    log.info(authorities.size() + "size of authories at this point");
       // for (int i = 0; i < authorities.size()+1 ; i++) {

           // log.info(authorities.toArray()[i].toString());
         //   map.put("role", authorities.stream().toArray()[i].toString());
           // log.info(authorities.toArray() + "");
        //log.info(map + "this is map at this point ");

        //}
       // for (GrantedAuthority authority: authorities){
         //   map.put("role", Collections.singletonList(authority.getAuthority()));
      //  }
     //   log.info(map + "this is map");

        List<String> grantedAutho = new ArrayList<>();
        for (int i = 0; i < authorities.stream().toList().size() ; i++) {
            grantedAutho.add(authorities.stream().toList().get(i).toString());
        }

     //   log.info("please worrkkkkkk" + grantedAutho);


        return JWT.create().withIssuedAt(now()).
                withExpiresAt(now().plusSeconds(120000L)).withClaim("phoneNumber", phoneNumber).
                withClaim("Roles", map).
                sign(Algorithm.HMAC512(jwtUtil.getSecret()));



    }


}
