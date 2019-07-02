package jlearning.words;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import jlearning.words.service.impl.ExtendedConversionServiceImpl;
import jlearning.words.service.impl.FileServiceImpl;

@Configuration
@ComponentScan(
        value = {
                "jlearning.words.service"
        },
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ASSIGNABLE_TYPE,
                        classes = {
                                ExtendedConversionServiceImpl.class,
                                FileServiceImpl.class
                        }
                )
        }
)
public class CommonServiceConfiguration {
}
