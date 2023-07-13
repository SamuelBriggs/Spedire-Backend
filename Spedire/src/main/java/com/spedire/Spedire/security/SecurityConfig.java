package com.spedire.Spedire.security;

import com.spedire.Spedire.models.Role;
import com.spedire.Spedire.security.filter.SpedireAuthenticationFilter;
import com.spedire.Spedire.security.filter.SpedireAuthorizationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


<<<<<<< HEAD
//import static com.spedire.Spedire.AppUtils.SecurityUtils.JWT_SIGNING_SECRET;
=======
//import static com.spedire.Spire.AppUtils.SecurityUtils.JWT_SIGNING_SECRET;
>>>>>>> 181dcc0b515128c01be6a4fd393d4635d2185536

@AllArgsConstructor
@Configuration
public class SecurityConfig {

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtil;



//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        UsernamePasswordAuthenticationFilter authenticationFilter = new SpedireAuthenticationFilter(authenticationManager, jwtUtil, null, null
//                );
//        return httpSecurity.csrf(AbstractHttpConfigurer::disable).
//                cors(Customizer.withDefaults()).
//                sessionManagement(c->c.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
//                addFilterBefore(new SpedireAuthorizationFilter(), SpedireAuthenticationFilter.class)
//                .addFilterAt(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                .authorizeHttpRequests(c->c.requestMatchers(POST, "/vi/users/register")
//                        .permitAll())
//                .authorizeHttpRequests(c->c.requestMatchers(POST,"/api/user/hello").permitAll()).
//                authorizeHttpRequests(c->c.requestMatchers( "/api/user/detail").
//                        hasAnyRole("ADMIN", "USER")).
//                build();
//
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        UsernamePasswordAuthenticationFilter authenticationFilter = new SpedireAuthenticationFilter(authenticationManager, jwtUtil, null, null
        );
        var authorizationFilter =new SpedireAuthorizationFilter(jwtUtil);

        return httpSecurity.csrf(AbstractHttpConfigurer::disable).
                cors(Customizer.withDefaults()).
                sessionManagement(c->c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(authorizationFilter, SpedireAuthenticationFilter.class)
                .addFilterAt(authenticationFilter, UsernamePasswordAuthenticationFilter.class)

                .authorizeHttpRequests(c->c.requestMatchers( "/api/user/welcome", "/api/v1/user/verify-number").permitAll())

                .authorizeHttpRequests(c->c.requestMatchers( "matchOrder", "acceptOrder").permitAll()).
<<<<<<< HEAD
                authorizeHttpRequests(c->c.requestMatchers( "/api/v1/user/getCurrentUser" ).

                        hasAnyAuthority(String.valueOf(Role.ADMIN), Role.SENDER.name()))

                .authorizeHttpRequests(c->c.requestMatchers("/api/v1/user/**", "/api/v1/user/verify-otp").permitAll()).
                authorizeHttpRequests(c->c.requestMatchers( "/api/user/detail").
                        hasAnyRole("ADMIN", "NEW_USER")).
=======
                authorizeHttpRequests(c->c.requestMatchers( "/api/v1/user/getCurrentUser", "/api/v1/user/updateProfile" ).
                        hasAnyAuthority(String.valueOf(Role.SENDER), Role.USER.name()))

                .authorizeHttpRequests(c->c.requestMatchers( "/api/v1/user/verify-otp","/api/v1/user/buildToken", "/api/v1/user/register").permitAll()).
                authorizeHttpRequests(c->c.requestMatchers( "a").
                        hasAnyRole("ADMIN", "USER", "SENDER")).
>>>>>>> 181dcc0b515128c01be6a4fd393d4635d2185536

                build();

    }
}