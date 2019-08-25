package jlearning.words.dao.impl;

import jlearning.words.dao.WordsDao;
import jlearning.words.dao.domain_model.WordDto;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jlearning.words.util.HibernateUtils.listAndCast;

@Repository
public class WordsDaoImpl extends BaseDaoImpl<WordDto> implements WordsDao {

    @Override
    public List<WordDto> findAllByUser(String userRef) {
        return listAndCast(getSession()
                .createQuery("SELECT w FROM WordDto w WHERE w.userRef = :userRef")
                .setParameter("userRef", userRef)
                .setReadOnly(true));
    }

    @Override
    public Long save(WordDto entity) {
        getSession().save(entity);
        return entity.getId();
    }

    @Override
    public void deleteAll() {
        getSession().createQuery("DELETE FROM WordDto");
    }
}
