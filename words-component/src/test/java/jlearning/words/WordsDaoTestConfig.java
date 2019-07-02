package jlearning.words;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import jlearning.words.dao.WordsDao;
import jlearning.words.dao.impl.WordsDaoImpl;

@TestConfiguration
public class WordsDaoTestConfig {

    @Bean
    public WordsDao wordsDao() {
        return new WordsDaoImpl();
    }
}
