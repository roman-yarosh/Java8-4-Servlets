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

import static ua.goit.servlets.utils.Constants.initManufacturersConstants;

@WebServlet(name = "DeleteManufacturer", urlPatterns = "/manufacturer/delete" )
public class DeleteManufacturer extends HttpServlet{

    HibernateManufacturerDao hibernateManufacturerDao = HibernateManufacturerDao.getInstance(HibernateUtil.getSessionFactory());



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String manufacturerId = request.getParameter("id");
        String errorString = null;
        Manufacturer manufacturer = null;
        Optional<Manufacturer> manufacturerOptional;

        if (StringUtils.isEmpty(manufacturerId)) {
            errorString = "Manufacturer UUID is empty!";
        }

        RequestDispatcher requestDispatcher;
        if (StringUtils.isEmpty(errorString)) {
            manufacturerOptional = hibernateManufacturerDao.read(UUID.fromString(manufacturerId));
            if (manufacturerOptional.isPresent()){
                manufacturer = manufacturerOptional.get();
                hibernateManufacturerDao.delete(manufacturer);
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
}
