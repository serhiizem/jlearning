package telecom.authserver.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import telecom.authserver.SameOriginRoleBasedAuthenticationSuccessHandler;

@Configuration
@Order(-20)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationManager authenticationManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(corsConfigurationSource());

        http.formLogin().successHandler(new SameOriginRoleBasedAuthenticationSuccessHandler())
                .loginPage("/login")
                .permitAll();

        http.requestMatchers().antMatchers("/login", "/oauth/authorize", "/logout");
        http.authorizeRequests().anyRequest().authenticated();
    }

    private CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.setAllowCredentials(true);
        config.addAllowedHeader("X-Requested-With");
        config.addAllowedHeader("Content-Type");
        config.addAllowedMethod(HttpMethod.GET);
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.parentAuthenticationManager(authenticationManager);

        auth.inMemoryAuthentication()
                .withUser("user")
                .password("user").authorities("USER")
                .and()
                .withUser("csr")
                .password("csr").authorities("ROLE_CSR")
                .and()
                .withUser("services")
                .password("services").authorities("ROLE_SERVICE_MANAGER");
    }
}
