package telecom.dao.impl;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import telecom.dao.TariffDao;
import telecom.model.Tariff;

import java.util.List;

import static telecom.util.HibernateUtils.listAndCast;

@Repository
public class TariffDaoImpl extends CrudDaoImpl<Tariff> implements TariffDao {

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
    @Transactional(readOnly = true)
    public List<Tariff> findAll() {
        Query regionsQuery = getSession().createQuery("from Tariff t");
        return listAndCast(regionsQuery);
    }
}
