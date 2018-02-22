package ua.goit.servlets.controller.hiberante;

import ua.goit.servlets.controller.Controller;
import ua.goit.servlets.model.dao.hibernate.HibernateManufacturerDao;
import ua.goit.servlets.model.entity.Manufacturer;
import ua.goit.servlets.utils.HibernateUtil;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class HibernateManufacturerController implements Controller<Manufacturer, UUID> {

    private HibernateManufacturerDao hibernateManufacturerDao = HibernateManufacturerDao.getInstance(HibernateUtil.getSessionFactory());

    @Override
    public Optional<Manufacturer> read(UUID key) {
        return hibernateManufacturerDao.read(key);
    }

    @Override
    public void create(Manufacturer manufacturer) {
        hibernateManufacturerDao.create(manufacturer);
    }

    @Override
    public void update(Manufacturer manufacturer) {
        hibernateManufacturerDao.update(manufacturer);
    }

    @Override
    public void delete(Manufacturer manufacturer) {
        hibernateManufacturerDao.delete(manufacturer);
    }

    @Override
    public List<Manufacturer> getAll() {
        return hibernateManufacturerDao.getAll();
    }
}
