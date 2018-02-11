package ua.goit.servlets.controller.servlets;


import ua.goit.servlets.model.dao.hibernate.HibernateManufacturerDao;
import ua.goit.servlets.model.dao.hibernate.HibernateProductDao;
import ua.goit.servlets.model.entity.Manufacturer;
import ua.goit.servlets.model.entity.Product;
import ua.goit.servlets.utils.HibernateUtil;
import ua.goit.servlets.utils.ServletUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@WebServlet(name = "AddProduct", urlPatterns = "/product/add")
public class AddProduct extends HttpServlet {

    HibernateProductDao hibernateProductDao = HibernateProductDao.getInstance(HibernateUtil.getSessionFactory());
    HibernateManufacturerDao hibernateManufacturerDao = HibernateManufacturerDao.getInstance(HibernateUtil.getSessionFactory());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("manufacturersList", hibernateManufacturerDao.getAll());
        RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher("/AddProduct.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Product product = null;
        Optional<Manufacturer> manufacturerOptional = null;
        String errorString = null;
        BigDecimal price = null;

        String productName = request.getParameter("productName");
        String productPrice = request.getParameter("productPrice");
        String manufacturerId = request.getParameter("manufacturerId");

        if (productName == null || productName.isEmpty()) {
            errorString = "Product name is empty!";
        }

        errorString = ServletUtils.checkProductPrice(errorString, productPrice);
        manufacturerOptional = hibernateManufacturerDao.read(UUID.fromString(manufacturerId));
        errorString = ServletUtils.checkManufacturerId(errorString, manufacturerId, manufacturerOptional);

        if (errorString == null) {
            product = new Product(productName, new BigDecimal(productPrice), manufacturerOptional.get());
            hibernateProductDao.create(product);
        }

        RequestDispatcher requestDispatcher;
        if (errorString != null) {
            request.setAttribute("errorString", errorString);
            request.setAttribute("manufacturersList", hibernateManufacturerDao.getAll());
            requestDispatcher = request.getServletContext().getRequestDispatcher("/AddProduct.jsp");
        } else {
            request.setAttribute("productsList", hibernateProductDao.getAll());
            requestDispatcher = request.getServletContext().getRequestDispatcher("/Products.jsp");
        }
        requestDispatcher.forward(request, response);

    }
}
