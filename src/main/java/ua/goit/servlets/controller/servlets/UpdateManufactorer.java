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
import java.util.Optional;
import java.util.UUID;

@WebServlet(name = "UpdateManufactorer", urlPatterns = "/manufacturer/update")
public class UpdateManufactorer extends HttpServlet{

    HibernateManufacturerDao hibernateManufacturerDao = HibernateManufacturerDao.getInstance(HibernateUtil.getSessionFactory());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String manufacturerId = request.getParameter("id");
        String errorString = null;
        Manufacturer manufacturer = null;
        Optional<Manufacturer> manufacturerOptional;

        if (manufacturerId == null || manufacturerId.isEmpty()) {
            errorString = "Manufacturer UUID is invalid!";
        }

        RequestDispatcher requestDispatcher;
        if (errorString == null) {
            manufacturerOptional = hibernateManufacturerDao.read(UUID.fromString(manufacturerId));
            if (manufacturerOptional.isPresent()) {
                manufacturer = manufacturerOptional.get();

                request.setAttribute("manufacturerId", manufacturer.getManufacturerId());
                request.setAttribute("manufacturerName", manufacturer.getManufacturerName());

                requestDispatcher = request.getServletContext().getRequestDispatcher("/UpdateManufacturer.jsp");
                requestDispatcher.forward(request, response);
            } else {
                errorString = "Manufacturer with UUID " + manufacturerId + " is not in DB!";
            }
        }

        request.setAttribute("errorString", errorString);
        request.setAttribute("manufacturersList", hibernateManufacturerDao.getAll());
        requestDispatcher = request.getServletContext().getRequestDispatcher("/Manufacturers.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Manufacturer manufacturer = null;
        String manufacturerId = request.getParameter("manufacturerId");
        String manufacturerName = request.getParameter("manufacturerName");

        String errorString = null;

        if (manufacturerId == null || manufacturerId.isEmpty()) {
            errorString = "Manufacturer UUID is empty!";
        }
        if (manufacturerName == null || manufacturerName.isEmpty()) {
            if (errorString == null) {
                errorString = "Manufacturer name is invalid!";
            } else {
                errorString += " And Manufacturer name is invalid!";
            }
        }

        request.setAttribute("errorString", errorString);

        RequestDispatcher dispatcher;
        if (errorString == null) {
            manufacturer = new Manufacturer(UUID.fromString(manufacturerId), manufacturerName);
            hibernateManufacturerDao.update(manufacturer);
            request.setAttribute("manufacturersList", hibernateManufacturerDao.getAll());
            dispatcher = request.getServletContext().getRequestDispatcher("/Manufacturers.jsp");

        } else {
            request.setAttribute("manufacturerId", manufacturerId);
            request.setAttribute("manufacturerName", manufacturerName);
            dispatcher = request.getServletContext().getRequestDispatcher("/UpdateManufacturer.jsp");
        }
        dispatcher.forward(request, response);
    }
}


