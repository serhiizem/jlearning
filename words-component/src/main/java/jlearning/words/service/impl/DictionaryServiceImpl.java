package jlearning.words.service.impl;

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
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static jlearning.words.service.impl.LambdaHelper.*;

@Service
@RequiredArgsConstructor
public class DictionaryServiceImpl implements DictionaryService {

    private final WordsDao wordsDao;
    private final FileService fileService;

    @Override
    @Validate
    public Long save(@ValidatorTypes(values = WordValidator.class) Word word,
                     String userRef) {
        String base64Image = word.getBase64Image();
        File imageFile = fileService.createFileFromBase64Content(base64Image);
        String fileLocation = fileService.upload(imageFile);

        WordDto wordDto = new WordDto(word.getValue(), userRef, word.getTranslations());
        wordDto.setFileLocation(fileLocation);

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
}