package learn.solarfarm.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final JwtConverter converter;

    public SecurityConfig(JwtConverter converter) {
        this.converter = converter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationConfiguration config) throws Exception {
        http.csrf().disable();
        http.cors();

        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/solarpanel", "/api/solarpanel/*").permitAll()
                .antMatchers(HttpMethod.POST, "/api/solarpanel").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/solarpanel/*").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/solarpanel/*").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/authenticate", "/encode").permitAll()
                .antMatchers("/**").denyAll()
                .and()
                .addFilter(new JwtRequestFilter(manager(config), converter))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }

    @Bean
    public AuthenticationManager manager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}