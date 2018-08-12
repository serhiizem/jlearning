package telecom.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import telecom.dao.TariffDao;
import telecom.model.Tariff;
import telecom.service.TariffService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TariffServiceImpl implements TariffService {

    private final TariffDao tariffDao;

    @Override
    public Tariff save(Tariff entity) {
        return null;
    }

    @Override
    public Tariff update(Tariff entity) {
        return null;
    }

    @Override
    public Tariff findById(Long id) {
        return null;
    }

    @Override
    public List<Tariff> findAll() {
        return tariffDao.findAll();
    }
}
