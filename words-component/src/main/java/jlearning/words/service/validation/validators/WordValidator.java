package jlearning.words.service.validation.validators;

import org.springframework.stereotype.Component;
import jlearning.words.service.exception.WordValidationException;
import jlearning.words.service.model.Word;

import java.util.List;

import static org.springframework.util.CollectionUtils.isEmpty;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class WordValidator implements Validator<Word> {

    @Override
    public void validate(Object target) {
        doBasicValidation(target);

        Word word = cast(target);

        String value = word.getValue();
        if (isEmpty(value)) {
            throw new WordValidationException("Word should not be null");
        }
        List<String> translations = word.getTranslations();
        if (isEmpty(translations)) {
            throw new WordValidationException("Word should have at least one translation");
        }
    }

    @Override
    public Class<Word> getTargetType() {
        return Word.class;
    }
}
