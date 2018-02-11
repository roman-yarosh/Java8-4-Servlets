package ua.goit.servlets.controller.servlets;

import ua.goit.servlets.model.dao.hibernate.HibernateProductDao;
import ua.goit.servlets.model.entity.Manufacturer;
import ua.goit.servlets.model.entity.Product;
import ua.goit.servlets.utils.HibernateUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@WebServlet(name = "DeleteProduct", urlPatterns = "/product/delete")
public class DeleteProduct extends HttpServlet{

    HibernateProductDao hibernateProductDao = HibernateProductDao.getInstance(HibernateUtil.getSessionFactory());

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
            if (productOptional.isPresent()){
                product = productOptional.get();
                hibernateProductDao.delete(product);
            } else {
                errorString = "Product with UUID " + productId + " is not in DB!";
            }
        }
        request.setAttribute("errorString", errorString);
        request.setAttribute("productsList", hibernateProductDao.getAll());
        requestDispatcher = request.getServletContext().getRequestDispatcher("/Products.jsp");
        requestDispatcher.forward(request, response);
    }
}
