package com.spedire.Spedire.security;

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
import org.springframework.security.web.session.SessionManagementFilter;
=======

import static org.springframework.http.HttpMethod.POST;
>>>>>>> 6fbaeba464c189bb1538bf62ba3dc804757c3eb7


//import static com.spedire.Spedire.AppUtils.SecurityUtils.JWT_SIGNING_SECRET;

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
<<<<<<< HEAD
                );
        var authorizationFilter =new SpedireAuthorizationFilter();
=======
        );
>>>>>>> 6fbaeba464c189bb1538bf62ba3dc804757c3eb7
        return httpSecurity.csrf(AbstractHttpConfigurer::disable).
               cors(Customizer.withDefaults()).
                sessionManagement(c->c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(authorizationFilter, SpedireAuthenticationFilter.class)
                .addFilterAt(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
<<<<<<< HEAD
                .authorizeHttpRequests(c->c.requestMatchers("/api/v1/users/register", "/api/user/welcome").permitAll()).
=======
                .authorizeHttpRequests(c->c.requestMatchers("/api/v1/users/**").permitAll()).
>>>>>>> 6fbaeba464c189bb1538bf62ba3dc804757c3eb7
                authorizeHttpRequests(c->c.requestMatchers( "/api/user/detail").
                        hasAnyAuthority(Role.ADMIN.name(), Role.USER.name())).
                build();

    }
}
