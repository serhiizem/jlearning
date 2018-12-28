package telecom.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import telecom.dao.RegionDao;
import telecom.model.Region;

import javax.transaction.Transactional;
import java.util.List;

import static telecom.util.HibernateUtils.listAndCast;

@Repository
public class RegionDaoImpl extends CrudDaoImpl<Region> implements RegionDao {

    @Autowired
    public RegionDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Region save(Region entity) {
        return null;
    }

    @Override
    public Region update(Region entity) {
        return null;
    }

    @Override
    public Region findOne(Long id) {
        return null;
    }

    @Override
    @Transactional
    public List<Region> findAll() {
        Query regionsQuery = getSession().createQuery("from Region r");
        return listAndCast(regionsQuery);
    }
}
