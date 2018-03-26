package ua.goit.servlets.utils;

import org.apache.commons.lang3.StringUtils;
import ua.goit.servlets.model.entity.Manufacturer;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Optional;
import static ua.goit.servlets.utils.Constants.*;

public class ServletUtils {

    public static String checkProductPrice(String errorString, String productPrice) {

        if (StringUtils.isEmpty(productPrice)) {
            if (StringUtils.isEmpty(errorString)) {
                errorString = "Product price is empty!";
            } else {
                errorString += " And Product price is empty!";
            }
        } else {
            try {
                new BigDecimal(productPrice);
            } catch (NumberFormatException e) {
                if (StringUtils.isEmpty(errorString)) {
                    errorString = "Product price is invalid!";
                } else {
                    errorString += " And Product price is invalid!";
                }
            }
        }
        return errorString;
    }

    public static String checkManufacturerId(String errorString, String manufacturerId, Optional<Manufacturer> manufacturerOptional) {

        if (StringUtils.isEmpty(manufacturerId)) {
            if (StringUtils.isEmpty(errorString)) {
                errorString = "Manufacturer id is empty!";
            } else {
                errorString += " And Manufacturer id is empty!";
            }
        } else {
            if (!manufacturerOptional.isPresent()) {
                if (StringUtils.isEmpty(errorString)) {
                    errorString = "Manufacturer id is invalid!";
                } else {
                    errorString += " And Manufacturer id is invalid!";
                }
            }
        }
        return errorString;
    }
}
