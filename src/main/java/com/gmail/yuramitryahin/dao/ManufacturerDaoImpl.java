package com.gmail.yuramitryahin.dao;

import com.gmail.yuramitryahin.db.Storage;
import com.gmail.yuramitryahin.lib.Dao;
import com.gmail.yuramitryahin.modal.Manufacturer;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Dao
public class ManufacturerDaoImpl implements ManufacturerDao {

    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        Storage.addManufacturer(manufacturer);
        return manufacturer;
    }

    @Override
    public Optional<Manufacturer> get(Long id) {
        return Storage.manufacturers.stream()
                .filter(m -> m.getId() == id)
                .findFirst();
    }

    @Override
    public List<Manufacturer> getAll() {
        return Storage.manufacturers;
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        int indexOfElement = IntStream.range(0, Storage.manufacturers.size())
                .filter(i -> Storage.manufacturers.get(i).getId().equals(manufacturer.getId()))
                .findFirst()
                .getAsInt();
        Storage.manufacturers.set(indexOfElement, manufacturer);
        return manufacturer;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.manufacturers.remove(get(id).get());
    }
}
