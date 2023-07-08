package com.spedire.Spedire.security;

<<<<<<< HEAD
import com.spedire.Spedire.security.filter.SpedireAuthenticationFilter;
import com.spedire.Spedire.security.filter.SpedireAuthorizationFilter;
import lombok.AllArgsConstructor;
=======
import com.spedire.Spedire.security.filters.SpedireAuthenticationFilter;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
>>>>>>> e10467bc7d8031ee3cfb02133d2fff3769db5c0d
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
import static org.springframework.http.HttpMethod.POST;


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
        );
        return httpSecurity.csrf(AbstractHttpConfigurer::disable).
                cors(Customizer.withDefaults()).
                sessionManagement(c->c.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
                addFilterBefore(new SpedireAuthorizationFilter(), SpedireAuthenticationFilter.class)
                .addFilterAt(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(c->c.requestMatchers("/api/v1/user/**", "/api/v1/user/verify-otp").permitAll()).
                authorizeHttpRequests(c->c.requestMatchers( "/api/user/detail").
                        hasAnyRole("ADMIN", "USER")).
                build();

    }
=======
import static com.spedire.Spedire.security.SecretUtils.JWT_SIGNING_SECRET;
import static com.spedire.Spedire.utils.AppUtils.*;
import static org.springframework.http.HttpMethod.PATCH;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Value(JWT_SIGNING_SECRET)
    private String jwt_secret;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        UsernamePasswordAuthenticationFilter authenticationFilter = new SpedireAuthenticationFilter(authenticationManager, jwtUtil);
//        authenticationFilter.setFilterProcessesUrl(LOGIN_ENDPOINT);
        return httpSecurity
                .csrf(AbstractHttpConfigurer:: disable)
                .cors(Customizer.withDefaults())
                .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new SpedireAuthenticationFilter(authenticationManager, jwtUtil), SpedireAuthenticationFilter.class)
                .addFilterAfter(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(c -> c.requestMatchers(POST, CUSTOMER_API_VALUE).permitAll())
                .authorizeHttpRequests(c -> c.requestMatchers(POST, LOGIN_ENDPOINT).permitAll())
                .authorizeHttpRequests(c -> c.anyRequest().authenticated())
                .build();
    }

>>>>>>> e10467bc7d8031ee3cfb02133d2fff3769db5c0d
}
