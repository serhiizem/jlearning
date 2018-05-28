package telecom.ui;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/troubleshooting")
                .setViewName("forward:index.html");
        registry.addViewController("/dashboard")
                .setViewName("forward:index.html");
        registry.addViewController("/tariffs")
                .setViewName("forward:index.html");
    }
}
