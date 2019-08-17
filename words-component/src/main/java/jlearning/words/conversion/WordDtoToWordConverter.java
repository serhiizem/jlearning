package jlearning.words.conversion;

import jlearning.words.dao.domain_model.WordDto;
import jlearning.words.service.model.Word;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WordDtoToWordConverter implements Converter<WordDto, Word> {

    @Override
    public Word convert(@Nullable WordDto source) {
        if (source == null) {
            return null;
        }

        String value = source.getValue();
        List<String> translations = source.getTranslations();
        String fileLocation = source.getFileLocation();

        return Word.builder()
                .value(value)
                .translations(translations)
                .fileLocation(fileLocation)
                .build();
    }
}
