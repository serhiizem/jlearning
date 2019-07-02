package telecom;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AbstractDaoTest {

    @PersistenceContext
    EntityManager em;

    // These fields are designed for future usage
    @SuppressWarnings("WeakerAccess")
    SessionFactory sessionFactory;
    @SuppressWarnings("WeakerAccess")
    JdbcTemplate jdbcTemplate; //for direct db access without hibernate
    @SuppressWarnings("WeakerAccess")
    Session session;

    @Autowired
    public void setSessionFactory(EntityManagerFactory emf,
                                  JdbcTemplate jdbcTemplate) {
        this.sessionFactory = emf.unwrap(SessionFactory.class);
        this.jdbcTemplate = jdbcTemplate;
    }

    @Before
    public void init() {
        this.session = em.unwrap(Session.class);
    }
}
