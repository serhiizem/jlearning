package jlearning.words;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(basePackageClasses = {
        WordsApplication.class,
        Jsr310JpaConverters.class
})
public class WordsApplication {
    public static void main(String[] args) {
        SpringApplication.run(WordsApplication.class);
    }
}
