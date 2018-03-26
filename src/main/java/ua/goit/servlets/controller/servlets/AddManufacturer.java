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

import static ua.goit.servlets.utils.Constants.*;

@WebServlet(name = "AddManufacturer", urlPatterns = "/manufacturer/add")
public class AddManufacturer extends HttpServlet{

    HibernateManufacturerDao hibernateManufacturerDao = HibernateManufacturerDao.getInstance(HibernateUtil.getSessionFactory());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        initConstants(request);
        RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher("/AddManufacturer.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String manufacturerName = request.getParameter("manufacturerName");
        Manufacturer manufacturer = null;

        String errorString = null;

        if (StringUtils.isEmpty(manufacturerName)) {
            errorString = "Manufacturer name is empty!";
        }

        if (StringUtils.isEmpty(errorString)) {
            manufacturer = new Manufacturer(manufacturerName);
            hibernateManufacturerDao.create(manufacturer);
        }

        request.setAttribute("errorString", errorString);

        RequestDispatcher requestDispatcher;
        if (!StringUtils.isEmpty(errorString)) {
            initConstants(request);
            requestDispatcher = request.getServletContext().getRequestDispatcher("/AddManufacturer.jsp");
        }
        else {
            initManufacturersConstants(request);
            request.setAttribute("manufacturersList", hibernateManufacturerDao.getAll());
            requestDispatcher = request.getServletContext().getRequestDispatcher("/Manufacturers.jsp");
        }
        requestDispatcher.forward(request, response);
    }

    private void initConstants(HttpServletRequest request) {
        request.setAttribute("BORDER_WIDTH_0", BORDER_WIDTH_0);
        request.setAttribute("TWO_COLUMNS", TWO_COLUMNS);

        request.setAttribute("MANUFACTURER_TITLE", MANUFACTURER_TITLE);
        request.setAttribute("MANUFACTURER_NAME", MANUFACTURER_NAME);
    }
}
