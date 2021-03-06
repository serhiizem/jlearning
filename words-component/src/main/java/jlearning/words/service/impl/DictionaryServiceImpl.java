package jlearning.words.service.impl;

import jlearning.words.conversion.ExtendedConversionService;
import jlearning.words.dao.WordsDao;
import jlearning.words.dao.domain_model.WordDto;
import jlearning.words.service.DictionaryService;
import jlearning.words.service.FileService;
import jlearning.words.service.model.Word;
import jlearning.words.service.model.WordGroup;
import jlearning.words.service.validation.Validate;
import jlearning.words.service.validation.ValidatorTypes;
import jlearning.words.service.validation.validators.WordValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static jlearning.words.service.impl.LambdaHelper.*;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Slf4j
@Service
@RequiredArgsConstructor
public class DictionaryServiceImpl implements DictionaryService {

    private final WordsDao wordsDao;
    private final FileService fileService;
    private final ExtendedConversionService conversionService;

    @Override
    @Validate
    public Long save(@ValidatorTypes(values = WordValidator.class) Word word,
                     String userRef) {

        WordDto wordDto = new WordDto(word.getValue(), userRef, word.getTranslations());

        String base64Image = word.getBase64Image();
        if (isNotEmpty(base64Image)) {
            File imageFile = fileService.createFileFromBase64Content(base64Image);
            String fileLocation = fileService.upload(imageFile);
            wordDto.setFileLocation(fileLocation);
        }

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
                // opening a new stream is a trade-off between readability and performance
                .stream()
                .map(toWordsByFirstLetterGroup)
                .sorted(byFirstLetter)
                .collect(toList());
    }

    @Override
    public List<Word> findAll(String userRef) {
        List<WordDto> allUserWords = wordsDao.findAllByUser(userRef);
        return conversionService.convert(allUserWords, Word.class);
    }

    @Override
    public void clearDb() {
        try {
            wordsDao.deleteAll();
            log.info("Database was cleared of stale entries");
        } catch (Exception e) {
            log.error("", e);
        }
    }
}