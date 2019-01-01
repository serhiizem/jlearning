package telecom_test.dao;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import telecom.dao.RegionDao;
import telecom.dao.TariffDao;
import telecom.model.Region;
import telecom.model.Tariff;

import java.util.List;

public class RegionDaoTest extends AbstractDaoTest {

    @Autowired
    private RegionDao regionDao;

    @Test
    public void shouldFindAllTheExistingRegions() {
        List<Region> regions = regionDao.findAll();
        Assertions.assertEquals(100, regions.size());
    }
}
