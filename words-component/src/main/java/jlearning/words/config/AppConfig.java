package jlearning.words.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

@Configuration
@EnableScheduling
@EnableResourceServer
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class AppConfig {

    @Profile("!dev")
    @Configuration
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    public class ProdServerConfig extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .exceptionHandling()
                    .authenticationEntryPoint((request, response, authException) ->
                            response.sendError(SC_UNAUTHORIZED))
                    .and()
                    .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .httpBasic();
        }
    }

    @Profile("dev")
    @Configuration
    public class DevServerConfig extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/").permitAll()
                    .and()
                    .httpBasic().disable();
        }
    }
}
