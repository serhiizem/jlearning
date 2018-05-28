package telecom.authserver.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import telecom.authserver.RoleBasedAuthenticationSuccessHandler;

@Configuration
@Order(-20)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationManager authenticationManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().successHandler(new RoleBasedAuthenticationSuccessHandler())
                .loginPage("/login")
                .permitAll();

        http.requestMatchers().antMatchers("/login", "/oauth/authorize");
        http.authorizeRequests().anyRequest().authenticated();
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
