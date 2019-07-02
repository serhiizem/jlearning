package jlearning.words.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import jlearning.words.dao.BaseDao;
import jlearning.words.dao.domain_model.DomainEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Slf4j
@Repository
@Transactional
public abstract class BaseDaoImpl<T extends DomainEntity> implements BaseDao<T> {

    @PersistenceContext
    private EntityManager entityManager;

    protected Session getSession() {
        return entityManager.unwrap(Session.class);
    }
}
