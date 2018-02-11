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

@WebServlet(name = "UpdateProduct", urlPatterns = "/product/update")
public class UpdateProduct extends HttpServlet {

    HibernateProductDao hibernateProductDao = HibernateProductDao.getInstance(HibernateUtil.getSessionFactory());
    HibernateManufacturerDao hibernateManufacturerDao = HibernateManufacturerDao.getInstance(HibernateUtil.getSessionFactory());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("id");
        String errorString = null;
        Product product = null;
        Optional<Product> productOptional;

        if (productId == null || productId.isEmpty()) {
            errorString = "Product UUID is empty!";
        }

        RequestDispatcher requestDispatcher;
        if (errorString == null) {
            productOptional = hibernateProductDao.read(UUID.fromString(productId));
            if (productOptional.isPresent()) {
                product = productOptional.get();

                request.setAttribute("product", product);
                request.setAttribute("manufacturersList", hibernateManufacturerDao.getAll());

                requestDispatcher = request.getServletContext().getRequestDispatcher("/UpdateProduct.jsp");
                requestDispatcher.forward(request, response);
            } else {
                errorString = "Product with UUID " + productId + " is not in DB!";
            }
        }

        request.setAttribute("errorString", errorString);
        request.setAttribute("productsList", hibernateProductDao.getAll());
        requestDispatcher = request.getServletContext().getRequestDispatcher("/Products.jsp");
        requestDispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = null;
        Optional<Product> productOptional = null;
        Optional<Manufacturer> manufacturerOptional = null;
        String errorString = null;

        String productId = request.getParameter("productId");
        String productName = request.getParameter("productName");
        String productPrice = request.getParameter("productPrice");
        String manufacturerId = request.getParameter("manufacturerId");

        if (productName == null || productName.isEmpty()) {
            errorString = "Product name is empty!";
        }

        if (productId == null || productId.isEmpty()) {
            if (errorString == null) {
                errorString = "Product id is empty!";
            } else {
                errorString += " And Product id is empty!";
            }
        }

        errorString = ServletUtils.checkProductPrice(errorString, productPrice);
        manufacturerOptional = hibernateManufacturerDao.read(UUID.fromString(manufacturerId));
        errorString = ServletUtils.checkManufacturerId(errorString, manufacturerId, manufacturerOptional);

        if (errorString == null) {
            productOptional = hibernateProductDao.read(UUID.fromString(productId));

            if (productOptional.isPresent()) {
                if (errorString == null) {
                    product = productOptional.get();
                    product.setProductName(productName);
                    product.setProductPrice(new BigDecimal(productPrice));
                    product.setManufacturer(manufacturerOptional.get());
                    hibernateProductDao.update(product);
                } else {
                    errorString = "Product id is invalid!";
                }
            }
        }

        RequestDispatcher requestDispatcher;
        if (errorString != null) {
            request.setAttribute("errorString", errorString);
            request.setAttribute("product", product);
            request.setAttribute("manufacturersList", hibernateManufacturerDao.getAll());
            requestDispatcher = request.getServletContext().getRequestDispatcher("/UpdateProduct.jsp");
        } else {
            request.setAttribute("productsList", hibernateProductDao.getAll());
            requestDispatcher = request.getServletContext().getRequestDispatcher("/Products.jsp");
        }
        requestDispatcher.forward(request, response);
    }
}

