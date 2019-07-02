package telecom.dao;

import telecom.dao.domain_model.WordDto;

import java.util.List;

public interface WordsDao extends BaseDao<WordDto> {
    List<WordDto> findAllByUser(String userRef);
}
