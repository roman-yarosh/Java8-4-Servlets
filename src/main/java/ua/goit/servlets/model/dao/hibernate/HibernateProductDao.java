package ua.goit.servlets.model.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.goit.servlets.model.dao.ProductDao;
import ua.goit.servlets.model.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class HibernateProductDao implements ProductDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateProductDao.class);
    private static HibernateProductDao instance;
    private static SessionFactory sessionFactory;

    public HibernateProductDao() {
    }

    public static HibernateProductDao getInstance(SessionFactory sessionFactoryParam) {
        if (instance == null) {
            instance = new HibernateProductDao();
            sessionFactory = sessionFactoryParam;
        }
        return instance;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public Optional<Product> read(UUID key) {
        try (Session session = getSessionFactory().openSession()){
            Product product = session.get(Product.class, key);
            if (product != null) {
                return Optional.of(product);
            } else {
                return Optional.empty();
            }
        }
    }

    @Override
    public void create(Product product) {
        try (Session session = getSessionFactory().openSession()){
            try {
                session.beginTransaction();
                session.save(product);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                LOGGER.error(String.format("%s %s", "Exception while saving product", e.getMessage()));
            }
        }
    }

    @Override
    public void update(Product product) {
        try (Session session = getSessionFactory().openSession()){
            try {
                session.beginTransaction();
                session.update(product);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                LOGGER.error(String.format("%s %s", "Exception while updating product", e.getMessage()));
            }
        }
    }

    @Override
    public void delete(Product product) {
        try (Session session = getSessionFactory().openSession()){
            try {
                session.beginTransaction();
                session.delete(product);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                LOGGER.error(String.format("%s %s", "Exception while deleting product", e.getMessage()));
            }
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> productList = new ArrayList<>();
        try (Session session = getSessionFactory().openSession()){
            try {
                productList = session.createQuery("FROM Product").list();
            } catch (Exception e) {
                LOGGER.error(String.format("%s %s", "Exception while selecting all products", e.getMessage()));
            }
        }
        return productList;
    }
}
