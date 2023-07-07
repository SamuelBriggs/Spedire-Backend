package com.spedire.Spedire.security.provider;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
<<<<<<< HEAD
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
=======
>>>>>>> e10467bc7d8031ee3cfb02133d2fff3769db5c0d
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
<<<<<<< HEAD

@AllArgsConstructor
@Component
public class SpedireAuthenticationProvider implements AuthenticationProvider {
    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;


=======
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class SpedireAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

>>>>>>> e10467bc7d8031ee3cfb02133d2fff3769db5c0d
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication authResult;
        String phoneNumber = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        UserDetails userDetails = userDetailsService.loadUserByUsername(phoneNumber);
        String userPhoneNumber = userDetails.getUsername();
        String userPassword = userDetails.getPassword();
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

<<<<<<< HEAD
        if (passwordEncoder.matches(password, userPassword)){
          authResult = new UsernamePasswordAuthenticationToken(userPhoneNumber, userPassword, authorities);
          return authResult;
        }
        throw new BadCredentialsException("False credentials");


=======
        if (passwordEncoder.matches(password, userPassword)) {
            authResult = new UsernamePasswordAuthenticationToken(userPhoneNumber, userPassword, authorities);
            return authResult;
        }
        throw new BadCredentialsException("Oops!");
>>>>>>> e10467bc7d8031ee3cfb02133d2fff3769db5c0d
    }

    @Override
    public boolean supports(Class<?> authentication) {
<<<<<<< HEAD
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
=======
        return true;
>>>>>>> e10467bc7d8031ee3cfb02133d2fff3769db5c0d
    }
}
