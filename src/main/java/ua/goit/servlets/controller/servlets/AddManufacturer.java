package ua.goit.servlets.controller.servlets;

import ua.goit.servlets.model.dao.hibernate.HibernateManufacturerDao;
import ua.goit.servlets.model.entity.Manufacturer;
import ua.goit.servlets.utils.HibernateUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddManufacturer", urlPatterns = "/manufacturer/add")
public class AddManufacturer extends HttpServlet{

    HibernateManufacturerDao hibernateManufacturerDao = HibernateManufacturerDao.getInstance(HibernateUtil.getSessionFactory());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher("/AddManufacturer.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String manufacturerName = request.getParameter("manufacturerName");
        Manufacturer manufacturer = null;

        String errorString = null;

        if (manufacturerName == null || manufacturerName.isEmpty()) {
            errorString = "Manufacturer name is empty!";
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
