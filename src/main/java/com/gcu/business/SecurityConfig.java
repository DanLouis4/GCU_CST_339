package com.gcu.business;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * SecurityConfig
 * -----------------------------------------
 * Configures:
 * - Basic Auth for REST API (/api/**)
 * - Form login for web pages
 */
@Configuration
public class SecurityConfig {

    @Bean
    public DatabaseUserDetailsService userDetailsService() {
        return new DatabaseUserDetailsService();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(
            DatabaseUserDetailsService userDetailsService,
            BCryptPasswordEncoder encoder) {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(encoder);
        return provider;
    }

    @Component
    public class PasswordPrinter implements CommandLineRunner {
        @Override
        public void run(String... args) throws Exception {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String hashed = encoder.encode("testpassword");
            System.out.println("🔐 Hashed password: " + hashed);
        }
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF for simplicity during testing
            .csrf(csrf -> csrf.disable())

            // Authorization configuration
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/signup", "/signin", "/css/**", "/js/**", "/images/**", "/videos/**", "/fonts/**")
                .permitAll()
                // Require login for API routes
                .requestMatchers("/api/**").authenticated()
                .anyRequest().authenticated()
            )

            //  Add Basic Authentication for REST API
            .httpBasic(withDefaults())

            // Add Form Login for web users
            .formLogin(form -> form
                .loginPage("/signin")
                .loginProcessingUrl("/signin")
                .defaultSuccessUrl("/home", true)
                .failureUrl("/signin?error=true")
                .permitAll()
            )

            //  Logout setup
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            );

        return http.build();
    }
}
