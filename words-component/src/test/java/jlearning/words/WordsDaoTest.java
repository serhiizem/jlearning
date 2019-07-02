package telecom;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import jlearning.words.dao.WordsDao;
import jlearning.words.dao.domain_model.WordDto;

import java.util.List;

import static telecom.Mocks.MOCK_USER_REFERENCE;

//@DatabaseSetup("user_words_data.xml")
@ContextConfiguration(classes = WordsDaoTestConfig.class)
public class WordsDaoTest extends AbstractDaoTest {

    @Autowired
    private WordsDao wordsDao;

    @Test
    public void s() {
        List<WordDto> words = wordsDao.findAllByUser(MOCK_USER_REFERENCE);
        for (WordDto word : words) {
            System.out.println(word);
        }
    }
}
