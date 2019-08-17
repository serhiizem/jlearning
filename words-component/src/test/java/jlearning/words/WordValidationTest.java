package jlearning.words;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import jlearning.words.dao.WordsDao;
import jlearning.words.service.DictionaryService;
import jlearning.words.service.exception.ValidationException;
import jlearning.words.service.model.Word;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ValidationTestConfig.class)
public class WordValidationTest {

    @MockBean
    private WordsDao wordsDao;

    @Autowired
    private DictionaryService dictionaryService;

    @Test(expected = ValidationException.class)
    public void shouldThrowValidationExceptionIfWordIsNull() {
        dictionaryService.save(null, Mocks.MOCK_USER_REFERENCE);
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowValidationExceptionIfWordValueIsNull() {
        Word word = new Word(null, asList("translation1", "translation2"), "");
        dictionaryService.save(word, Mocks.MOCK_USER_REFERENCE);
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowValidationExceptionIfTranslationsAreMissing() {
        Word word = new Word("Mock", emptyList(), "");
        dictionaryService.save(word, Mocks.MOCK_USER_REFERENCE);
    }
}
