package ua.goit.servlets.utils;

import javax.servlet.http.HttpServletRequest;

public class Constants {

    public static final int BORDER_WIDTH_0 = 0;
    public static final int BORDER_WIDTH_1 = 1;
    public static final int CELL_PADDING_5 = 5;
    public static final int CELL_SPACING_1 = 1;
    public static final int TWO_COLUMNS = 2;

    public static final String MANUFACTURER_TITLE = "Create new Manufacturer";
    public static final String MANUFACTURER_NAME = "Manufacturer name";
    public static final String PRODUCT_TITLE = "Create new Product";
    public static final String PRODUCT_NAME = "Product name";
    public static final String PRODUCT_PRICE = "Product price";

    public static final String MANUFACTURERS_TITLE = "Manufacturers List";
    public static final String PRODUCTS_TITLE = "Products List";
    public static final String MANUFACTURER_CREATE = "Create new Manufacturer";
    public static final String PRODUCT_CREATE = "Create new Product";
    public static final String MANUFACTURER_EDIT = "Edit Manufacturer";
    public static final String PRODUCT_EDIT = "Product edit";

    public static final String UUID_HEADER = "UUID";
    public static final String NAME = "Name";
    public static final String PRICE = "Price";
    public static final String MANUFACTURER = "Manufacturer";
    public static final String EDIT = "Edit";
    public static final String DELETE = "Delete";
    public static final String HOME = "Home";


    public static void initProductsConstants(HttpServletRequest request) {
        request.setAttribute("BORDER_WIDTH_1", BORDER_WIDTH_1);
        request.setAttribute("CELL_PADDING_5", CELL_PADDING_5);
        request.setAttribute("CELL_SPACING_1", CELL_SPACING_1);

        request.setAttribute("PRODUCTS_TITLE", PRODUCTS_TITLE);
        request.setAttribute("PRODUCT_CREATE", PRODUCT_CREATE);
        request.setAttribute("UUID_HEADER", UUID_HEADER);
        request.setAttribute("NAME", NAME);
        request.setAttribute("PRICE", PRICE);
        request.setAttribute("MANUFACTURER", MANUFACTURER);
        request.setAttribute("EDIT", EDIT);
        request.setAttribute("DELETE", DELETE);
        request.setAttribute("HOME", HOME);
    }

    public static void initManufacturersConstants(HttpServletRequest request) {
        request.setAttribute("BORDER_WIDTH_1", BORDER_WIDTH_1);
        request.setAttribute("CELL_PADDING_5", CELL_PADDING_5);
        request.setAttribute("CELL_SPACING_1", CELL_SPACING_1);

        request.setAttribute("MANUFACTURERS_TITLE", MANUFACTURERS_TITLE);
        request.setAttribute("MANUFACTURER_CREATE", MANUFACTURER_CREATE);
        request.setAttribute("UUID_HEADER", UUID_HEADER);
        request.setAttribute("NAME", NAME);
        request.setAttribute("EDIT", EDIT);
        request.setAttribute("DELETE", DELETE);
        request.setAttribute("HOME", HOME);
    }


}
