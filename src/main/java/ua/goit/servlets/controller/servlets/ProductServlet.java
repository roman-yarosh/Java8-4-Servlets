package ua.goit.servlets.controller.servlets;

import ua.goit.servlets.model.dao.hibernate.HibernateProductDao;
import ua.goit.servlets.utils.HibernateUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.goit.servlets.utils.Constants.initProductsConstants;


@WebServlet(name = "ProductServlet", urlPatterns = "/product/all")
public class ProductServlet extends HttpServlet {

    HibernateProductDao hibernateProductDao = HibernateProductDao.getInstance(HibernateUtil.getSessionFactory());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("productsList", hibernateProductDao.getAll());

        initProductsConstants(request);
        RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher("/Products.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
