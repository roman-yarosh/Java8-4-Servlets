package ua.goit.servlets.controller.servlets;

import org.apache.commons.lang3.StringUtils;
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

import static ua.goit.servlets.utils.Constants.*;

@WebServlet(name = "UpdateManufactorer", urlPatterns = "/manufacturer/update")
public class UpdateManufactorer extends HttpServlet{

    HibernateManufacturerDao hibernateManufacturerDao = HibernateManufacturerDao.getInstance(HibernateUtil.getSessionFactory());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String manufacturerId = request.getParameter("id");
        String errorString = null;
        Manufacturer manufacturer = null;
        Optional<Manufacturer> manufacturerOptional;

        if (StringUtils.isEmpty(manufacturerId)) {
            errorString = "Manufacturer UUID is invalid!";
        }

        RequestDispatcher requestDispatcher;
        if (StringUtils.isEmpty(errorString)) {
            manufacturerOptional = hibernateManufacturerDao.read(UUID.fromString(manufacturerId));
            if (manufacturerOptional.isPresent()) {
                manufacturer = manufacturerOptional.get();

                request.setAttribute("manufacturerId", manufacturer.getId());
                request.setAttribute("manufacturerName", manufacturer.getName());
                initConstants(request);
                requestDispatcher = request.getServletContext().getRequestDispatcher("/UpdateManufacturer.jsp");
                requestDispatcher.forward(request, response);
            } else {
                errorString = "Manufacturer with UUID " + manufacturerId + " is not in DB!";
            }
        }

        request.setAttribute("errorString", errorString);
        request.setAttribute("manufacturersList", hibernateManufacturerDao.getAll());
        initManufacturersConstants(request);
        requestDispatcher = request.getServletContext().getRequestDispatcher("/Manufacturers.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Manufacturer manufacturer = null;
        String manufacturerId = request.getParameter("manufacturerId");
        String manufacturerName = request.getParameter("manufacturerName");

        String errorString = null;

        if (StringUtils.isEmpty(manufacturerId)) {
            errorString = "Manufacturer UUID is empty!";
        }
        if (StringUtils.isEmpty(manufacturerName)) {
            if (StringUtils.isEmpty(errorString)) {
                errorString = "Manufacturer name is invalid!";
            } else {
                errorString += " And Manufacturer name is invalid!";
            }
        }

        request.setAttribute("errorString", errorString);

        RequestDispatcher dispatcher;
        if (StringUtils.isEmpty(errorString)) {
            manufacturer = new Manufacturer(UUID.fromString(manufacturerId), manufacturerName);
            hibernateManufacturerDao.update(manufacturer);
            request.setAttribute("manufacturersList", hibernateManufacturerDao.getAll());
            initManufacturersConstants(request);
            dispatcher = request.getServletContext().getRequestDispatcher("/Manufacturers.jsp");

        } else {
            initConstants(request);
            request.setAttribute("manufacturerId", manufacturerId);
            request.setAttribute("manufacturerName", manufacturerName);
            dispatcher = request.getServletContext().getRequestDispatcher("/UpdateManufacturer.jsp");
        }
        dispatcher.forward(request, response);
    }

    private void initConstants(HttpServletRequest request) {
        request.setAttribute("BORDER_WIDTH_0", BORDER_WIDTH_0);
        request.setAttribute("TWO_COLUMNS", TWO_COLUMNS);

        request.setAttribute("MANUFACTURER_EDIT", MANUFACTURER_EDIT);
        request.setAttribute("MANUFACTURER_NAME", MANUFACTURER_NAME);
    }
}


