package org.polimi.nexbuy.security.configuration;

import lombok.RequiredArgsConstructor;
import org.polimi.nexbuy.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Classe di configurazione dell'applicazione
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    /**
     * Repository per la gestione degli utenti
     */
    private final UserRepository userRepository;

    /**
     * Metodo per la configurazione dell' AuthenticationManager
     * @param config Configurazione dell' AuthenticationManager
     * @return AuthenticationManager
     * @throws Exception Eccezione lanciata in caso di errore
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Metodo per la configurazione dell' UserDetailsService
     * @return UserDetailsService
     */
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utenza non trovata"));
    }

    /**
     * Metodo per la configurazione dell' AuthenticationProvider
     * @return AuthenticationProvider
     */
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Metodo per la configurazione del PasswordEncoder
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
