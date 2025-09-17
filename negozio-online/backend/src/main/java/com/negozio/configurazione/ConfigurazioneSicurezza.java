package com.negozio.configurazione;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;

// configurazione di sicurezza per l enoteca mendoza
// gestisce autenticazione utenti e autorizzazioni
// permette la comunicazione tra frontend angular e backend spring
@Configuration
@EnableWebSecurity
public class ConfigurazioneSicurezza extends WebSecurityConfigurerAdapter {

    // codifica le password degli utenti con bcrypt
    // bcrypt e un algoritmo sicuro che rende le password illeggibili nel database
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // esclude dalla sicurezza il database h2 e le foto dei vini
    // cosi il frontend puo accedere liberamente alle immagini
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
            .antMatchers("/h2-console/**")
            .antMatchers("/Foto_Vini_FINALE/**");
    }

    // configura le regole di accesso alle api
    // decide chi puo accedere a cosa senza token di autenticazione
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors().and()
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/api/database/**").permitAll()
                .antMatchers("/api/test/**").permitAll()
                .antMatchers("/api/auth/**", "/api/prodotti/**", "/api/categorie/**", "/api/ordini/**", "/api/utenti/**").permitAll()
                .antMatchers("/api/**").authenticated()
                .anyRequest().permitAll()
            .and()
            .headers().frameOptions().sameOrigin();
    }

    // configurazione cors per permettere al frontend angular di comunicare
    // angular gira su porta 4200 e deve poter chiamare le api su porta 8080
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}