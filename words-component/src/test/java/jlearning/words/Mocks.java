package jlearning.words;

import jlearning.words.dao.domain_model.WordDto;

import java.util.List;

import static java.util.Arrays.asList;

public class Mocks {

    public static final String MOCK_USER_REFERENCE = "200";

    public static final List<WordDto> MOCK_USER_WORD_DTOS = asList(
            new WordDto("nehmen", MOCK_USER_REFERENCE, asList("take", "use", "accept", "pick")),
            new WordDto("nähren", MOCK_USER_REFERENCE, asList("nourish", "nurture", "feed")),
            new WordDto("laufen", MOCK_USER_REFERENCE, asList("run", "walk", "go")),
            new WordDto("legen", MOCK_USER_REFERENCE, asList("place", "put", "set", "lay")),
            new WordDto("lernen", MOCK_USER_REFERENCE, asList("learn", "study")),
            new WordDto("brauchen", MOCK_USER_REFERENCE, asList("need", "require")),
            new WordDto("müssen", MOCK_USER_REFERENCE, asList("must", "have", "shall", "need to", "ought to")),
            new WordDto("machen", MOCK_USER_REFERENCE, asList("do", "make"))
    );

    private Mocks() {
    }
}
