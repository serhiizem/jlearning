package telecom;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import jlearning.words.dao.WordsDao;
import jlearning.words.service.DictionaryService;
import jlearning.words.service.model.WordGroup;

import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static telecom.Mocks.MOCK_USER_REFERENCE;
import static telecom.Mocks.MOCK_USER_WORD_DTOS;

@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CommonServiceConfiguration.class)
public class DictionaryServiceTest {

    @MockBean
    private WordsDao wordsDao;

    @Autowired
    private DictionaryService dictionaryService;

    @Test
    public void shouldGroupWordsByFirstLetter() {
        when(wordsDao.findAllByUser(any())).thenReturn(MOCK_USER_WORD_DTOS);

        List<WordGroup> wordGroups = dictionaryService.getVerbsGroupedByFirstLetter(MOCK_USER_REFERENCE);

        assertThatEveryWordInGroupStartsWithItsCorrespondingLetter(wordGroups);
    }

    private void assertThatEveryWordInGroupStartsWithItsCorrespondingLetter(List<WordGroup> wordGroups) {
        for (WordGroup wordGroup : wordGroups) {
            String expectedFirstLetter = wordGroup.getFirstLetter();
            List<String> words = wordGroup.getWords();
            for (String word : words) {
                String actualFirstLetter = word.substring(0, 1);

                assertEquals(expectedFirstLetter, actualFirstLetter);
            }
        }
    }

    @Test
    public void shouldSortWordGroupsByFirstLetter() {
        when(wordsDao.findAllByUser(any())).thenReturn(MOCK_USER_WORD_DTOS);

        List<WordGroup> wordGroups = dictionaryService.getVerbsGroupedByFirstLetter(MOCK_USER_REFERENCE);

        List<WordGroup> sortedWordGroups = wordGroups
                .stream()
                .sorted(comparing(WordGroup::getFirstLetter))
                .collect(toList());

        assertEquals(sortedWordGroups, wordGroups);
    }
}