package jlearning.words.controller;

import jlearning.jwt.annotations.InjectUserRef;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import jlearning.words.service.DictionaryService;
import jlearning.words.service.model.Word;
import jlearning.words.service.model.WordGroup;

import java.util.List;

import static jlearning.words.util.Constants.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DictionaryController {

    private final DictionaryService dictionaryService;

    @GetMapping("/verbs")
    @PreAuthorize("hasAnyRole('ADMIN,STUDENT')")
    public List<WordGroup> getUserVerbs(@InjectUserRef String userRef) {
        return dictionaryService.getVerbsGroupedByFirstLetter(userRef);
    }

    @PostMapping("/verbs")
    @PreAuthorize("hasAnyRole('ADMIN,STUDENT')")
    public ResponseEntity addVerb(@RequestBody Word verb, @InjectUserRef String userRef) {
        dictionaryService.save(verb, userRef);
        return OK;
    }
}