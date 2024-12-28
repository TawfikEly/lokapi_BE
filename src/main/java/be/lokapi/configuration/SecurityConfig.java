package be.lokapi.configuration;

import be.lokapi.service.CustomUserDetailsService;
import be.lokapi.utils.Constantes;
import be.lokapi.utils.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    //Bean pour encoder les mots de passe
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    //Configuration de la sécurité HTTP
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtRequestFilter jwtRequestFilter) throws Exception{
        http
                .csrf(csrf -> csrf.disable()) // Désactiver CSRF pour l'instant
                .cors(Customizer.withDefaults()) // Activer CORS
                //.cors(cors -> cors.configurationSource(corsConfigurationSource())) // Associe la configuration CORS
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/auth/**").permitAll() // Permettre l'accès public aux routes d'authentification
                        .requestMatchers("/api/users/change-password").authenticated()
                        .requestMatchers("/api/auth/infoUser/**").permitAll()
                        .requestMatchers("/api/users/**").permitAll()
                        .requestMatchers("/api/properties/**").permitAll()
                        .requestMatchers("/api/leases/**").permitAll()
                        .requestMatchers("/api/file/**").permitAll()
                        .requestMatchers("/api/file/preview/uploads/**").permitAll() // Permettre l'accès à toutes les URL dynamiques de type /api/file/preview/uploads/...
                        .requestMatchers("/api/file/download/**").permitAll()
                        .requestMatchers("/api/file/uploadProfilePicture/**").permitAll()

                        .anyRequest().authenticated() // Toutes les autres requêtes nécessitent une authentification
                )
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin(form -> form
                        .loginPage("/login") // Page de connexion personnalisée
                        .permitAll()
                )
                .logout(logout -> logout.permitAll());
        return http.build();

    }



    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

   @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(Constantes.HTTP_ORIGIN_FRONT_CHROME, Constantes.HTTP_ORIGIN_FRONT_MACOS));
        configuration.setAllowedMethods(Arrays.asList(Constantes.GET, Constantes.POST, Constantes.PUT, Constantes.DELETE, Constantes.OPTIONS));
        configuration.setAllowedHeaders(Arrays.asList(Constantes.AUTHORIZATION, Constantes.CONTENT_TYPE));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);  // Applique la configuration CORS à tous les endpoints
        return source;

    }

}
