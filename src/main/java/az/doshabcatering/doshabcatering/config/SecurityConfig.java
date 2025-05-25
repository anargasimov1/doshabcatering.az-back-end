package az.doshabcatering.doshabcatering.config;

import az.doshabcatering.doshabcatering.security.handler.CustomAccessDeniedHandler;
import az.doshabcatering.doshabcatering.security.handler.CustomAuthenticationEntryPoint;
import az.doshabcatering.doshabcatering.security.jwtfilter.JwtFilter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SecurityConfig {

    JwtFilter jwtFilter;
    CustomAccessDeniedHandler customAccessDeniedHandler;
    CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("public/**", "auth/**", "/uploads/images/**").permitAll()
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .requestMatchers("/orders/**").authenticated()
                        .anyRequest().authenticated())
                .exceptionHandling(ex -> ex
                        .accessDeniedHandler(customAccessDeniedHandler)
                        .authenticationEntryPoint(customAuthenticationEntryPoint))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
