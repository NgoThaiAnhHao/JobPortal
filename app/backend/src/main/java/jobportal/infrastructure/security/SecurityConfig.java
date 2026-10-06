package jobportal.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final String[] SWAGGER_UI_ENDPOINTS = {
            "/swagger-ui/**",
            "/v3/api-docs/**"
    };

    private final String[] AUTH_ENDPOINTS = {
            "/auth/**"
    };

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {

        http
                // Bật cơ chế HTTP Basic Authentication, mục đích cho test POSTMAN
                .httpBasic(Customizer.withDefaults())

                // Tắt cơ chế csrf để xác thực API
                .csrf(AbstractHttpConfigurer::disable)

                // ENDPOINTS CONFIGURATION
                .authorizeHttpRequests(request ->
                    {
                        // AUTH ENDPOINTS
                        request.requestMatchers(AUTH_ENDPOINTS).permitAll();

                        // SWAGGER PUBLIC ENDPOINTS
                        request.requestMatchers(SWAGGER_UI_ENDPOINTS).permitAll();

                        // ANY ENDPOINTS
                        request.anyRequest().authenticated();
                    }
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(customUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return new ProviderManager(daoAuthenticationProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
