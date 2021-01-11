package com.gmail.yuramitryahin.service;

import com.gmail.yuramitryahin.dao.ManufacturerDao;
import com.gmail.yuramitryahin.lib.Inject;
import com.gmail.yuramitryahin.lib.Service;
import com.gmail.yuramitryahin.modal.Manufacturer;
import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    @Inject
    ManufacturerDao manufacturerDao;

    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        return manufacturerDao.create(manufacturer);
    }

    @Override
    public Manufacturer get(Long id) {
        return manufacturerDao.get(id)
                .orElseThrow(() -> new RuntimeException("Manufacturer not found!"));
    }

    @Override
    public List<Manufacturer> getAll() {
        return manufacturerDao.getAll();
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        return manufacturerDao.update(manufacturer);
    }

    @Override
    public boolean delete(Long id) {
        return manufacturerDao.delete(id);
    }
}
