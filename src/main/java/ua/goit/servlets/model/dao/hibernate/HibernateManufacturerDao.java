package ua.goit.servlets.model.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.goit.servlets.model.dao.ManufacturerDao;
import ua.goit.servlets.model.entity.Manufacturer;
import ua.goit.servlets.model.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class HibernateManufacturerDao implements ManufacturerDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateManufacturerDao.class);
    private static HibernateManufacturerDao instance;
    private static SessionFactory sessionFactory;

    public HibernateManufacturerDao() {
    }

    public static HibernateManufacturerDao getInstance(SessionFactory sessionFactoryParam) {
        if (instance == null) {
            instance = new HibernateManufacturerDao();
            sessionFactory = sessionFactoryParam;
        }
        return instance;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public Optional<Manufacturer> read(UUID key) {
        try (Session session = getSessionFactory().openSession()){
            Manufacturer manufacturer = session.get(Manufacturer.class, key);
            if (manufacturer != null) {
                return Optional.of(manufacturer);
            } else {
                return Optional.empty();
            }
        }
    }

    @Override
    public void create(Manufacturer manufacturer) {
        try (Session session = getSessionFactory().openSession()){
            try {
                session.beginTransaction();
                session.save(manufacturer);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                LOGGER.error(String.format("%s %s", "Exception while saving manufacturer", e.getMessage()));
            }
        }
    }

    @Override
    public void update(Manufacturer manufacturer) {
        try (Session session = getSessionFactory().openSession()){
            try {
                session.beginTransaction();
                session.update(manufacturer);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                LOGGER.error(String.format("%s %s", "Exception while updating manufacturer", e.getMessage()));
            }
        }
    }

    @Override
    public void delete(Manufacturer manufacturer) {
        try (Session session = getSessionFactory().openSession()){
            try {
                session.beginTransaction();
                session.delete(manufacturer);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                LOGGER.error(String.format("%s %s", "Exception while deleting manufacturer", e.getMessage()));
            }
        }
    }

    @Override
    public List<Manufacturer> getAll() {
        List<Manufacturer> manufacturerList = new ArrayList<>();
        try (Session session = getSessionFactory().openSession()){
            try {
                manufacturerList = session.createQuery("FROM Manufacturer ").list();
            } catch (Exception e) {
                LOGGER.error(String.format("%s %s", "Exception while selecting all manufacturers", e.getMessage()));
            }
        }
        return manufacturerList;
    }
}
