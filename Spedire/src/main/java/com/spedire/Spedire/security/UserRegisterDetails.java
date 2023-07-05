package com.spedire.Spedire.security;

import com.spedire.Spedire.repositories.UserRepository;
import com.spedire.Spedire.security.secureUser.SpredireSecureUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@AllArgsConstructor
public class UserRegisterDetails implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new SpredireSecureUser(userRepository.findByPhoneNumber(username));
    }
}
