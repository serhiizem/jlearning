package jlearning.words;

import jlearning.words.service.FileService;
import jlearning.words.service.impl.AwsFileService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AwsFileServiceTest.AwsFileServiceTestConfig.class)
public class AwsFileServiceTest {

    @Autowired
    private FileService fileService;

    @Test
    public void s() {
        fileService.uploadFileToAmazon("/home/sergei/projects/jlearning/words-component/src/main/resources/spring-by-pivotal.png");

        Assertions.assertTrue(true);
    }

    @Configuration
    static class AwsFileServiceTestConfig {

        @Bean
        public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
            PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer =
                    new PropertySourcesPlaceholderConfigurer();
            ClassPathResource classPathResource = new ClassPathResource("s3.properties");
            propertySourcesPlaceholderConfigurer.setLocation(classPathResource);

            return propertySourcesPlaceholderConfigurer;
        }

        @Bean
        public FileService fileService() {
            return new AwsFileService();
        }
    }
}
