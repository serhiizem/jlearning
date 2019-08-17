package jlearning.words;

import jlearning.words.service.FileService;
import jlearning.words.service.impl.AwsFileService;
import lombok.SneakyThrows;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.nio.file.Files.lines;
import static java.util.stream.Collectors.joining;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = FileServiceTest.AwsFileServiceTestConfig.class)
public class FileServiceTest {

    private static final String SAMPLE_CROPPED_FILE_NAME = "cropped_image_sample.txt";

    private static final Pair<String, Long> SAMPLE_CROPPED_FILE_NAME_AND_SIZE =
            Pair.of(SAMPLE_CROPPED_FILE_NAME, 28736L);

    @Autowired
    private FileService fileService;

    @Test
    public void shouldCreateFileFromBase64Sample() {
        String fileContent = readContentFromSampleFile();
        File file = fileService.createFileFromBase64Content(fileContent);

        assertEquals(Long.valueOf(file.length()), SAMPLE_CROPPED_FILE_NAME_AND_SIZE.getValue(),
                "Check if sample file was changed \n" +
                        "(in such case bytes number will be different and test will fail)\n");
    }

    @SneakyThrows
    private String readContentFromSampleFile() {
        URL resource = getClass().getClassLoader().getResource("cropped_image_sample.txt");
        if (resource == null) {
            fail(format("Sample file \"%s\" was not found in classpath", SAMPLE_CROPPED_FILE_NAME));
        }
        Path pathToSample = Paths.get(resource.toURI());

        try (Stream<String> lines = lines(pathToSample)) {
            return lines.collect(joining("\n"));
        }
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
