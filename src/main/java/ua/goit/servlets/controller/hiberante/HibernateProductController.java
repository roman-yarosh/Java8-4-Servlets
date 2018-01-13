package ua.goit.servlets.controller.hiberante;

import ua.goit.servlets.controller.Contriller;
import ua.goit.servlets.model.dao.hibernate.HibernateProductDao;
import ua.goit.servlets.model.entity.Product;
import ua.goit.servlets.utils.HibernateUtil;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class HibernateProductController implements Contriller<Product, UUID>{

    private HibernateProductDao hibernateProductDao = HibernateProductDao.getInstance(HibernateUtil.getSessionFactory());
    @Override
    public Optional<Product> read(UUID key) {
        return hibernateProductDao.read(key);
    }

    @Override
    public void create(Product product) {
        hibernateProductDao.create(product);
    }

    @Override
    public void update(Product product) {
        hibernateProductDao.update(product);
    }

    @Override
    public void delete(Product product) {
        hibernateProductDao.delete(product);
    }

    @Override
    public List<Product> getAll() {
        return hibernateProductDao.getAll();
    }
}
