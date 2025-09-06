package org.polimi.nexbuy.security.configuration;

import lombok.RequiredArgsConstructor;
import org.polimi.nexbuy.exception.CustomAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Classe di configurazione della sicurezza
 */
@SuppressWarnings("All")
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    /**
     * Metodo per la configurazione della catena di filtri di sicurezza
     * @param http HttpSecurity
     * @return SecurityFilterChain
     * @throws Exception Eccezione lanciata in caso di errore
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .anonymous().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .and()
                .authorizeHttpRequests(authorize -> authorize

                        .requestMatchers("/api/v1/auth/**").permitAll()

                        .requestMatchers("/api/v1/user/update/**").authenticated()
                        .requestMatchers("/api/v1/user/update-password/**").authenticated()
                        .requestMatchers("/api/v1/user/username/**").authenticated()

                        .requestMatchers("/api/v1/user/update-role/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_SUPER_ADMIN")
                        .requestMatchers("/api/v1/user/users").hasAnyAuthority("ROLE_ADMIN", "ROLE_SUPER_ADMIN")

                        .requestMatchers("/api/v1/product/create").hasAnyAuthority("ROLE_ADMIN", "ROLE_SUPER_ADMIN")
                        .requestMatchers("/api/v1/product/update").hasAnyAuthority("ROLE_ADMIN", "ROLE_SUPER_ADMIN")
                        .requestMatchers("/api/v1/product/delete").hasAnyAuthority("ROLE_ADMIN", "ROLE_SUPER_ADMIN")
                        .requestMatchers("/api/v1/product/products").hasAnyAuthority("ROLE_ADMIN", "ROLE_SUPER_ADMIN")

                        .anyRequest().authenticated()
                )
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
