package ua.goit.servlets.controller.servlets;


import ua.goit.servlets.model.dao.hibernate.HibernateManufacturerDao;
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

@WebServlet(name = "AddProduct", urlPatterns = "/product/add")
public class AddProduct extends HttpServlet{

    HibernateProductDao hibernateProductDao = HibernateProductDao.getInstance(HibernateUtil.getSessionFactory());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher("/AddProduct.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Product product = null;
        String errorString = null;

        String productName = (String) request.getParameter("productName");
        String productPrice = (String) request.getParameter("productPrice");
        String manufacturerName = (String) request.getParameter("manufacturerName");



        if (productName == null || productName.isEmpty()) {
            errorString = "Product name is empty!";
        }

        if (productPrice == null || productPrice.isEmpty()) {
            if (errorString == null) {
                errorString = "Product price is empty!";
            } else {
                errorString += " And Product price is empty!";
            }
        }

        if (manufacturerName == null || manufacturerName.isEmpty()) {
            if (errorString == null) {
                errorString = "Manufacturer name is empty!";
            } else {
                errorString += " And Manufacturer name is empty!";
            }
        }


        if (errorString == null) {
            manufacturer = new Manufacturer(manufacturerName);
            hibernateManufacturerDao.create(manufacturer);
        }

        if (errorString == null) {
            manufacturer = new Manufacturer(manufacturerName);
            hibernateManufacturerDao.create(manufacturer);
        }

        request.setAttribute("errorString", errorString);

        RequestDispatcher requestDispatcher;
        if (errorString != null) {
            requestDispatcher = request.getServletContext().getRequestDispatcher("/AddManufacturer.jsp");
        }
        else {
            request.setAttribute("manufacturersList", hibernateManufacturerDao.getAll());
            requestDispatcher = request.getServletContext().getRequestDispatcher("/Manufacturers.jsp");
        }
        requestDispatcher.forward(request, response);

    }
}
