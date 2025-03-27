package org.polimi.nexbuy.security.config;

import org.polimi.nexbuy.security.jwt.AuthEntryPointJwt;
import org.polimi.nexbuy.security.jwt.AuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery("SELECT email, password, enabled=1 FROM user WHERE email = ?");
        userDetailsManager.setAuthoritiesByUsernameQuery("SELECT u.email, UPPER(u.role) FROM user u WHERE u.email = ?");
        return userDetailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers("/Ecommerce/auth/login", "/Ecommerce/auth/register").permitAll()

                                .requestMatchers(HttpMethod.DELETE, "/Ecommerce/user/disable/**").hasAnyRole("ADMIN", "SUPER_ADMIN")

                                .requestMatchers(HttpMethod.POST, "/Ecommerce/address/add").hasAnyRole("USER", "SELLER", "ADMIN", "SUPER_ADMIN")
                                
                                .requestMatchers(HttpMethod.POST, "/Ecommerce/product/add").hasAnyRole("SELLER", "ADMIN", "SUPER_ADMIN")

                                .anyRequest().authenticated()
                )
                .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
}
