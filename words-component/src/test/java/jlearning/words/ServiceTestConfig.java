package jlearning.words;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.convert.ConversionService;

import static org.mockito.Mockito.mock;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(
        value = {
                "jlearning.words.service",
                "jlearning.words.conversion"
        }
)
public class ServiceTestConfig {

    @Bean
    public ConversionService conversionService() {
        return mock(ConversionService.class);
    }
}
