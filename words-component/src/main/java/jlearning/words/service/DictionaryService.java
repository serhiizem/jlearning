package jlearning.words.service;

import jlearning.words.service.model.Word;
import jlearning.words.service.model.WordGroup;

import java.util.List;

public interface DictionaryService extends BaseService<Word>  {
    List<WordGroup> getVerbsGroupedByFirstLetter(String userRef);
}
