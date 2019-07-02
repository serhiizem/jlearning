package telecom.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import telecom.dao.WordsDao;
import telecom.dao.domain_model.WordDto;
import telecom.service.DictionaryService;
import telecom.service.model.Word;
import telecom.service.model.WordGroup;
import telecom.service.validation.Validate;
import telecom.service.validation.ValidatorTypes;
import telecom.service.validation.validators.WordValidator;

import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.Function;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class DictionaryServiceImpl implements DictionaryService {

    private final WordsDao wordsDao;

    private final Function<WordDto, String> firstLetter = word -> {
        String value = word.getValue();
        return value.substring(0, 1);
    };

    private final Function<Entry<String, List<WordDto>>, WordGroup> toWordsByFirstLetterGroup =
            firstLetterAndItsWordsEntry -> {
                String letter = firstLetterAndItsWordsEntry.getKey();
                List<String> words = firstLetterAndItsWordsEntry.getValue()
                        .stream()
                        .map(WordDto::getValue)
                        .collect(toList());

                return new WordGroup(letter, words);
            };

    private final Comparator<WordGroup> byFirstLetter = comparing(WordGroup::getFirstLetter);

    @Override
    @Validate
    public Long save(@ValidatorTypes(values = WordValidator.class) Word word,
                     String userRef) {
        WordDto wordDto = new WordDto(word.getValue(), userRef, word.getTranslations());
        return wordsDao.save(wordDto);
    }

    @Override
    public List<WordGroup> getVerbsGroupedByFirstLetter(String userRef) {
        List<WordDto> verbs = wordsDao.findAllByUser(userRef);

        return verbs
                .stream()
                .filter(Objects::nonNull)
                .collect(groupingBy(firstLetter))
                .entrySet()
                // opening a new stream is a tradeoff between readability and performance
                .stream()
                .map(toWordsByFirstLetterGroup)
                .sorted(byFirstLetter)
                .collect(toList());
    }
}