package jlearning.words;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import jlearning.words.dao.WordsDao;
import jlearning.words.dao.domain_model.WordDto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static jlearning.words.Mocks.MOCK_USER_REFERENCE;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DatabaseSetup(
        type = DatabaseOperation.INSERT,
        value = "/user_words_data.xml"
)
@ContextConfiguration(classes = WordsDaoTestConfig.class)
public class WordsDaoTest extends AbstractDaoTest {

    @Autowired
    private WordsDao wordsDao;

    @Test
    public void shouldRetrieveWordOnlyRelatedToRequestedUser() {
        List<WordDto> words = wordsDao.findAllByUser(MOCK_USER_REFERENCE);

        for (WordDto word : words) {
            assertEquals(MOCK_USER_REFERENCE, word.getUserRef());
        }
    }
}
