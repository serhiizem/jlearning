package telecom.dao.impl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import telecom.dao.TariffDao;
import telecom.model.Tariff;

import javax.transaction.Transactional;
import java.util.List;

import static telecom.util.HibernateUtils.listAndCast;

@Repository
public class TariffDaoImpl extends CrudDaoImpl<Tariff>
        implements TariffDao {

    public TariffDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Tariff save(Tariff entity) {
        return null;
    }

    @Override
    public Tariff update(Tariff entity) {
        return null;
    }

    @Override
    public Tariff findOne(Long id) {
        return null;
    }

    @Override
    @Transactional
    public List<Tariff> findAll() {
        Query regionsQuery = getSession().createQuery("from Tariff t");
        return listAndCast(regionsQuery);
    }
}
