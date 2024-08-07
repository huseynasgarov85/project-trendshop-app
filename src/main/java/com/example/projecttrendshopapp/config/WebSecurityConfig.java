package com.example.projecttrendshopapp.config;

import com.example.projecttrendshopapp.util.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final UserDetailsService userDetails;

    private final JwtRequestFilter jwtRequestFilter;

    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                                .requestMatchers(SWAGGER_WHITELIST).permitAll()
//                        .requestMatchers(HttpMethod.GET, "/users/**").hasAnyRole("ADMIN", "USER")
                                .requestMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/users/**/cards/**").hasRole("ADMIN")
//                        .requestMatchers("/users/**/cards/**").hasRole("USER")
//                        .requestMatchers("/users/**/balance").hasRole("USER")
//                        .requestMatchers("/trousers/**").hasRole("ADMIN")
//                        .requestMatchers("/trousers").hasRole("ADMIN")
//                        .requestMatchers("/shoes/**").hasRole("ADMIN")
//                        .requestMatchers("/shoes").hasRole("ADMIN")
//                        .requestMatchers("/shirts/**").hasRole("ADMIN")
//                        .requestMatchers("/shirts").hasRole("ADMIN")
//                        .requestMatchers("/electricalEquipment/**").hasRole("ADMIN")
//                        .requestMatchers("/electricalEquipment").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.GET, "/cards/**").hasAnyRole("ADMIN", "USER")
//                        .requestMatchers(HttpMethod.POST, "/cards/**").hasAnyRole("USER", "ADMIN")
//                        .requestMatchers(HttpMethod.PUT, "/cards/**").hasRole("USER")
//                        .requestMatchers(HttpMethod.DELETE, "/cards/**").hasRole("USER")
//                        .requestMatchers("/wallet/**").hasAnyRole("USER", "ADMIN")
//                        .requestMatchers(HttpMethod.GET, "/order/**").hasRole("USER")
//                        .requestMatchers(HttpMethod.PATCH, "/order/**").hasAnyRole("USER", "ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/order/**").hasRole("USER")
//                        .requestMatchers("/basket/**").hasRole("USER")
//                        .requestMatchers(HttpMethod.GET, "/favorites/**").hasAnyRole("USER", "ADMIN")
//                        .requestMatchers(HttpMethod.POST, "/favorites").hasAnyRole("USER", "ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/favorites/**").hasRole("USER")
                                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

                http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(LogoutConfigurer::permitAll);
        http.userDetailsService(userDetails);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("password")
//                        .roles("USER")
//                        .build();
//        manager.createUser(user);
//
//        UserDetails admin =
//                User.withDefaultPasswordEncoder()
//                        .username("admin")
//                        .password("admin")
//                        .roles("ADMIN")
//                        .build();
//        manager.createUser(admin);
//        return manager;
//    }

    private static final String[] SWAGGER_WHITELIST = {
            "/api/v1/auth/",
            "/v2/api-docs",
            "/v3/api-docs",
            "/swagger-resources",
            "/swagger-resources/",
            "/configuration/ui",
            "/swagger-ui/",
            "/swagger-ui.html",
            "/swagger-ui/index.html#/",
            "/api/authenticate",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/configuration/security",
            "/webjars/**"
    };

}
