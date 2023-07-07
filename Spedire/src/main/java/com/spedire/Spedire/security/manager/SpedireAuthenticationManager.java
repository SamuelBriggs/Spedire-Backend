package com.spedire.Spedire.security.manager;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

<<<<<<< HEAD

@AllArgsConstructor
@Component
public class SpedireAuthenticationManager implements AuthenticationManager {
=======
@Component
@AllArgsConstructor
public class SpedireAuthenticationManager implements AuthenticationManager {

>>>>>>> e10467bc7d8031ee3cfb02133d2fff3769db5c0d
    private final AuthenticationProvider authenticationProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication authResult = null;
<<<<<<< HEAD
        if(authenticationProvider.supports(authentication.getClass())){
=======
        if (authenticationProvider.supports(authentication.getClass())) {
>>>>>>> e10467bc7d8031ee3cfb02133d2fff3769db5c0d
            authResult = authenticationProvider.authenticate(authentication);
            return authResult;
        }
        else throw new BadCredentialsException("Failed");
    }
}
