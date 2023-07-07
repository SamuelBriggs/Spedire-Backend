package com.spedire.Spedire.security.filter;

import com.auth0.jwt.interfaces.Claim;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spedire.Spedire.Exception.SpedireException;
import com.spedire.Spedire.security.JwtUtils;
import com.spedire.Spedire.security.SecurityUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
@Component
@Slf4j



public class SpedireAuthorizationFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        boolean isUnAuthorizedPath = SecurityUtils.UNAUTHORIZEDENDPOINTS.contains(request.getServletPath()) &&
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
        Map<String, Claim> map = JwtUtils.extractClaimsFromToken(token);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Claim claim = map.get("Roles");
        Claim phoneNumber = map.get("phoneNumber");
        addClaimToUserAuthorities(authorities,claim);
        Authentication authentication = new UsernamePasswordAuthenticationToken(phoneNumber, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private void addClaimToUserAuthorities(List<SimpleGrantedAuthority> authorities, Claim claim) {
        String role = claim.asMap().get("role").toString();
        authorities.add(new SimpleGrantedAuthority(role));
    }

}
