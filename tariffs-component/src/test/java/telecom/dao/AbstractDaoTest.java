package telecom.dao;

import org.hibernate.Session;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.PostConstruct;

@SuppressWarnings("WeakerAccess")
@DataJpaTest
@ExtendWith(SpringExtension.class)
public class AbstractDaoTest {

    @Autowired
    private TestEntityManager entityManager;

    protected Session session;

    @PostConstruct
    public void init() {
        this.session = entityManager.getEntityManager().unwrap(Session.class);
    }
}
