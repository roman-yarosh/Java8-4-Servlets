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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HibernateManufacturerDao hibernateManufacturerDao = HibernateManufacturerDao.getInstance(HibernateUtil.getSessionFactory());

        String id = (String) request.getParameter("id");
        String manufacturerName = (String) request.getParameter("manufacturerName");

        Manufacturer manufacturer = new Manufacturer(manufacturerName);

        String errorString = null;

        if (manufacturerName == null || manufacturerName.isEmpty()) {
            errorString = "Manufacturer name is empty!";
        }

        if (errorString == null) {
            hibernateManufacturerDao.create(manufacturer);
        }

        request.setAttribute("errorString", errorString);

        RequestDispatcher dispatcher;
        if (errorString != null) {
            request.setAttribute("manufacturer", manufacturer);
            dispatcher = request.getServletContext().getRequestDispatcher("/AddFormManufacturer.jsp");
        }
        else {
            request.setAttribute("manufacturersList", hibernateManufacturerDao.getAll());
            dispatcher = request.getServletContext().getRequestDispatcher("/Manufacturers.jsp");
        }
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
