package ua.goit.servlets.controller.servlets;

import org.apache.commons.lang3.StringUtils;
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

import static ua.goit.servlets.utils.Constants.*;

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

        if (StringUtils.isEmpty(productId)) {
            errorString = "Product UUID is empty!";
        }

        RequestDispatcher requestDispatcher;
        if (StringUtils.isEmpty(errorString)) {
            productOptional = hibernateProductDao.read(UUID.fromString(productId));
            if (productOptional.isPresent()) {
                product = productOptional.get();

                request.setAttribute("product", product);
                request.setAttribute("manufacturersList", hibernateManufacturerDao.getAll());
                initConstants(request);
                requestDispatcher = request.getServletContext().getRequestDispatcher("/UpdateProduct.jsp");
                requestDispatcher.forward(request, response);
            } else {
                errorString = "Product with UUID " + productId + " is not in DB!";
            }
        }

        request.setAttribute("errorString", errorString);
        request.setAttribute("productsList", hibernateProductDao.getAll());
        initProductsConstants(request);
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

        if (StringUtils.isEmpty(productName)) {
            errorString = "Product name is empty!";
        }

        if (StringUtils.isEmpty(productId)) {
            if (StringUtils.isEmpty(errorString)) {
                errorString = "Product id is empty!";
            } else {
                errorString += " And Product id is empty!";
            }
        }

        errorString = ServletUtils.checkProductPrice(errorString, productPrice);
        manufacturerOptional = hibernateManufacturerDao.read(UUID.fromString(manufacturerId));
        errorString = ServletUtils.checkManufacturerId(errorString, manufacturerId, manufacturerOptional);

        if (StringUtils.isEmpty(errorString)) {
            productOptional = hibernateProductDao.read(UUID.fromString(productId));

            if (productOptional.isPresent()) {
                if (StringUtils.isEmpty(errorString)) {
                    product = productOptional.get();
                    product.setName(productName);
                    product.setPrice(new BigDecimal(productPrice));
                    product.setManufacturer(manufacturerOptional.get());
                    hibernateProductDao.update(product);
                } else {
                    errorString = "Product id is invalid!";
                }
            }
        }

        RequestDispatcher requestDispatcher;
        if (!StringUtils.isEmpty(errorString)) {
            if (product == null) product = new Product();
            if (!StringUtils.isEmpty(productId)) product.setId(UUID.fromString(productId));
            if (!StringUtils.isEmpty(productPrice)) product.setPrice(new BigDecimal(productPrice));
            if (!StringUtils.isEmpty(productName)) product.setName(productName);
            request.setAttribute("errorString", errorString);
            request.setAttribute("product", product);
            request.setAttribute("manufacturersList", hibernateManufacturerDao.getAll());
            initConstants(request);
            requestDispatcher = request.getServletContext().getRequestDispatcher("/UpdateProduct.jsp");
        } else {
            request.setAttribute("productsList", hibernateProductDao.getAll());
            initProductsConstants(request);
            requestDispatcher = request.getServletContext().getRequestDispatcher("/Products.jsp");
        }
        requestDispatcher.forward(request, response);
    }

    private void initConstants(HttpServletRequest request) {
        request.setAttribute("BORDER_WIDTH_0", BORDER_WIDTH_0);
        request.setAttribute("TWO_COLUMNS", TWO_COLUMNS);

        request.setAttribute("PRODUCT_EDIT", PRODUCT_EDIT);
        request.setAttribute("PRODUCT_NAME", PRODUCT_NAME);
        request.setAttribute("PRODUCT_PRICE", PRODUCT_PRICE);
        request.setAttribute("MANUFACTURER_NAME", MANUFACTURER_NAME);
    }


}

