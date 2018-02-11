package ua.goit.servlets.utils;

import ua.goit.servlets.model.entity.Manufacturer;

import java.math.BigDecimal;
import java.util.Optional;

public class ServletUtils {

    public static String checkProductPrice(String errorString, String productPrice) {

        if (productPrice == null || productPrice.isEmpty()) {
            if (errorString == null) {
                errorString = "Product price is empty!";
            } else {
                errorString += " And Product price is empty!";
            }
        } else {
            try {
                new BigDecimal(productPrice);
            } catch (NumberFormatException e) {
                if (errorString == null) {
                    errorString = "Product price is invalid!";
                } else {
                    errorString += " And Product price is invalid!";
                }
            }
        }
        return errorString;
    }

    public static String checkManufacturerId(String errorString, String manufacturerId, Optional<Manufacturer> manufacturerOptional) {

        if (manufacturerId == null || manufacturerId.isEmpty()) {
            if (errorString == null) {
                errorString = "Manufacturer id is empty!";
            } else {
                errorString += " And Manufacturer id is empty!";
            }
        } else {
            if (!manufacturerOptional.isPresent()) {
                if (errorString == null) {
                    errorString = "Manufacturer id is invalid!";
                } else {
                    errorString += " And Manufacturer id is invalid!";
                }
            }
        }
        return errorString;
    }
}
