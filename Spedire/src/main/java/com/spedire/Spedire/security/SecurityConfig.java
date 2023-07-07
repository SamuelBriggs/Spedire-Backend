package com.spedire.Spedire.security;

import com.spedire.Spedire.models.Role;
import com.spedire.Spedire.security.filter.SpedireAuthenticationFilter;
import com.spedire.Spedire.security.filter.SpedireAuthorizationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.SessionManagementFilter;


//import static com.spedire.Spedire.AppUtils.SecurityUtils.JWT_SIGNING_SECRET;

@AllArgsConstructor
@Configuration
public class SecurityConfig {

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtil;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        UsernamePasswordAuthenticationFilter authenticationFilter = new SpedireAuthenticationFilter(authenticationManager, jwtUtil, null, null
                );
        var authorizationFilter =new SpedireAuthorizationFilter();
        return httpSecurity.csrf(AbstractHttpConfigurer::disable).
               cors(Customizer.withDefaults()).
                sessionManagement(c->c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(authorizationFilter, SpedireAuthenticationFilter.class)
                .addFilterAt(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(c->c.requestMatchers("/api/v1/users/register", "/api/user/welcome").permitAll()).
                authorizeHttpRequests(c->c.requestMatchers( "/api/user/detail").
                        hasAnyAuthority(Role.ADMIN.name(), Role.USER.name())).
                build();

    }


}
