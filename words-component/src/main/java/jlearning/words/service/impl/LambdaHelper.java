package jlearning.words.service.impl;

import jlearning.words.dao.domain_model.WordDto;
import jlearning.words.service.model.WordGroup;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

class LambdaHelper {

    private LambdaHelper() {
    }

    static final Function<WordDto, String> firstLetter = word -> {
        String value = word.getValue();
        return value.substring(0, 1);
    };

    static final Function<Map.Entry<String, List<WordDto>>, WordGroup> toWordsByFirstLetterGroup =
            firstLetterAndItsWordsEntry -> {
                String letter = firstLetterAndItsWordsEntry.getKey();
                List<String> words = firstLetterAndItsWordsEntry.getValue()
                        .stream()
                        .map(WordDto::getValue)
                        .collect(toList());

                return new WordGroup(letter, words);
            };

    static final Comparator<WordGroup> byFirstLetter = comparing(WordGroup::getFirstLetter);
}
