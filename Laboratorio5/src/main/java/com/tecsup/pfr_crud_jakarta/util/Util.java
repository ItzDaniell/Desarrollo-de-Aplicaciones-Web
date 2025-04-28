package com.tecsup.pfr_crud_jakarta.util;
import java.util.ResourceBundle;

public class Util {

    public static Tipo opc ;
    public static final String ERROR1;
    public static final String ERROR2;

    static {
        ResourceBundle properties = ResourceBundle.getBundle("config");
        opc = Tipo.valueOf(properties.getString("opc"));
        ERROR1 = properties.getString("error1");
        ERROR2 = properties.getString("error2");
    }
    public static final String RUTA= "c:/Datos.ini";
}





