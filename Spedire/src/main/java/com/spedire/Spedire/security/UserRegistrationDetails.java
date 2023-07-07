package com.spedire.Spedire.security;

import com.spedire.Spedire.models.User;
import com.spedire.Spedire.repositories.UserRepository;
import com.spedire.Spedire.security.securedUser.SpedireSecureUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import static com.spedire.Spedire.utils.AppUtils.USER_WITH_PHONE_NUMBER_NOT_FOUND;

@Component
@AllArgsConstructor
public class UserRegistrationDetails implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        User user = userRepository.findByPhoneNumber(phoneNumber).orElseThrow(()->new UsernameNotFoundException(
                String.format(USER_WITH_PHONE_NUMBER_NOT_FOUND)
        ));
        return new SpedireSecureUser(user);
    }
}
