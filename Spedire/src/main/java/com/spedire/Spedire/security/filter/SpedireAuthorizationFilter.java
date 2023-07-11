package com.spedire.Spedire.security.filter;

import com.auth0.jwt.interfaces.Claim;
import com.spedire.Spedire.Exception.SpedireException;
import com.spedire.Spedire.security.JwtUtils;
import com.spedire.Spedire.security.EndPointConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
@Component
@Slf4j
@AllArgsConstructor
public class SpedireAuthorizationFilter extends OncePerRequestFilter {

    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        boolean isUnAuthorizedPath = EndPointConstants.UNAUTHORIZEDENDPOINTS.contains(request.getServletPath()) &&
                request.getMethod().equals(HttpMethod.POST.name());
        if (isUnAuthorizedPath) filterChain.doFilter(request, response);
        else {
            try {
                authorizeRequest(request, response, filterChain);
            } catch (SpedireException e) {
                throw new RuntimeException(e);
            }
        }

    }
    private void authorizeRequest(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws SpedireException, ServletException, IOException {
        authorize(request, response, filterChain);
        filterChain.doFilter(request, response);
    }

    private void authorize(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws SpedireException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);;
        String token_prefix = "Bearer ";
        boolean isValidAuthorizationHeader = authorizationHeader != null && authorizationHeader.startsWith(token_prefix);
        if(isValidAuthorizationHeader) {
            String token = authorizationHeader.substring(token_prefix.length());
            authorizeToken(token);
        }
    }

    private void authorizeToken(String token) throws SpedireException {
        Map<String, Claim> map = jwtUtils.extractClaimsFromToken(token);
        //List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Claim claim = map.get("Roles");
        Collection<? extends GrantedAuthority> newAuthories =  List.of(new SimpleGrantedAuthority("USER"), new SimpleGrantedAuthority("ADMIN"));
        var string = claim.asList(SimpleGrantedAuthority.class);
        log.info("now log this shit and let's see whats " + string);

        //log.info(string.get(0) + "this is the content of String");
        Claim phoneNumber = map.get("phoneNumber");

      // addClaimToUserAuthorities(authorities,claim);
      // log.info(authorities + "afdfd");
        Authentication authentication = new UsernamePasswordAuthenticationToken(phoneNumber, null, string);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private void addClaimToUserAuthorities(List<SimpleGrantedAuthority> authorities, Claim claim) {
        String role = claim.asMap().get("role").toString();
        authorities.add(new SimpleGrantedAuthority(role));
    }

}
