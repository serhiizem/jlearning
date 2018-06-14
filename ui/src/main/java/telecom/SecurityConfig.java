package telecom;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        "/",
                        "/index.html",
                        "/login",
                        "/uaa/oauth/token",
                        "/assets/**",
                        "/bootstrap.min.css.map").permitAll().anyRequest()
                .authenticated().and()
                .addFilterAfter(new CsrfCookieSenderFilter(), CsrfFilter.class);
    }
}
