package telecom.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import telecom.dao.CrudDao;
import telecom.model.DomainEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Slf4j
@Repository
public abstract class CrudDaoImpl<T extends DomainEntity> implements CrudDao<T> {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(@Qualifier("entityManagerFactory") EntityManagerFactory emf) {
        this.sessionFactory = emf.unwrap(SessionFactory.class);
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
