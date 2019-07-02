package jlearning.words.dao.impl;

import org.springframework.stereotype.Repository;
import jlearning.words.dao.WordsDao;
import jlearning.words.dao.domain_model.WordDto;

import javax.annotation.PostConstruct;
import java.util.List;

import static jlearning.words.util.HibernateUtils.listAndCast;

@Repository
public class WordsDaoImpl extends BaseDaoImpl<WordDto> implements WordsDao {

    @PostConstruct
    public void init() {
        System.out.println("dfasdg");
    }

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
}
