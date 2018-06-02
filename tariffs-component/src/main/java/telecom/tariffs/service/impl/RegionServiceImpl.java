package telecom.tariffs.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import telecom.tariffs.dao.RegionDao;
import telecom.tariffs.model.Region;
import telecom.tariffs.service.RegionService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionDao regionDao;

    @Override
    public Region save(Region region) {
        return regionDao.save(region);
    }

    @Override
    public Region update(Region region) {
        return regionDao.update(region);
    }

    @Override
    public Region findById(Long id) {
        return regionDao.findOne(id);
    }

    @Override
    public List<Region> findAll() {
        return regionDao.findAll();
    }
}
