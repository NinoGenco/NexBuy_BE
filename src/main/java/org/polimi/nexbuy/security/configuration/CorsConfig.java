package org.polimi.nexbuy.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


/**
 * Configurazione centralizzata delle regole CORS applicate dalla security chain (Spring Security 6).
 *
 * <p><strong>Contesto</strong>: le regole CORS dichiarate via {@code WebMvcConfigurer#addCorsMappings}
 * NON vengono prese in carico dai filtri di Spring Security. Esporre un {@link CorsConfigurationSource}
 * consente a {@code http.cors()} di applicare le stesse regole sia alle richieste reali sia alle preflight
 * {@code OPTIONS}.</p>
 *
 * <p><strong>Integrazione con Security</strong>:
 * <ul>
 *   <li>Nella {@code SecurityFilterChain} abilita CORS con {@code http.cors()}.</li>
 *   <li>Consente la preflight: {@code .authorizeHttpRequests(a -> a.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll())}.</li>
 * </ul>
 * </p>
 */
@Configuration
public class CorsConfig {

    /**
     * Crea il {@link CorsConfigurationSource} usato da Spring Security per rispondere
     * correttamente alle preflight {@code OPTIONS} e alle richieste CORS del frontend.
     *
     * <h3>Regole configurate</h3>
     * <ul>
     *   <li><b>AllowedOrigins</b>: consente l’origin dell’app Angular in dev ({@code http://localhost:4200}).</li>
     *   <li><b>AllowedMethods</b>: include i metodi REST comuni e {@code OPTIONS} per la preflight.</li>
     *   <li><b>AllowedHeaders</b>: abilita almeno {@code Authorization}, {@code Content-Type}, {@code X-Requested-With}.</li>
     *   <li><b>ExposedHeaders</b>: espone {@code Authorization} se deve essere leggibile lato client.</li>
     *   <li><b>AllowCredentials</b>: impostato a {@code false} perché si usa Bearer token; metterlo a {@code true} solo con cookie/sessione.</li>
     *   <li><b>MaxAge</b>: cache della preflight per 3600s per ridurre round-trip.</li>
     * </ul>
     *
     * @return sorgente CORS registrata su {@code /**}, consumata da {@code http.cors()} nella Security chain.
     * @implNote Questa configurazione è efficace solo se nella {@code SecurityFilterChain} è presente {@code http.cors()}.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration cors = new CorsConfiguration();
        cors.setAllowedOrigins(List.of("http://localhost:4200")); // origin esatto dell’app front-end
        cors.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        cors.setAllowedHeaders(List.of("Authorization", "Content-Type", "X-Requested-With"));
        cors.setExposedHeaders(List.of("Authorization")); // opzionale: leggibilità lato client
        cors.setAllowCredentials(false); // true SOLO se usi cookie/sessione
        cors.setMaxAge(3600L); // cache preflight

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cors);
        return source;
    }

}