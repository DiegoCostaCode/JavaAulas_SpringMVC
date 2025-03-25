package br.com.fiap.spring_mvc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        return (http.authorizeHttpRequests(authorizeRequests ->
            authorizeRequests
                    .requestMatchers("/").permitAll()
                    .anyRequest().authenticated()
            ).oauth2Login(oauth2 ->
                oauth2.defaultSuccessUrl("/filme/list")).formLogin(Customizer.withDefaults())
                .build()
        );

    }
}
