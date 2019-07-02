package telecom.service;

import telecom.service.model.Word;
import telecom.service.model.WordGroup;

import java.util.List;

public interface DictionaryService extends BaseService<Word>  {
    List<WordGroup> getVerbsGroupedByFirstLetter(String userRef);
}
