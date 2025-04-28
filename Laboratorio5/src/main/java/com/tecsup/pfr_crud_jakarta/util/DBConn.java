package com.tecsup.pfr_crud_jakarta.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.PropertyResourceBundle;

public class DBConn {

    private static String driver= null;
    private static String usuario = null;
    private static String password = null;
    private static String url = null;
    static PropertyResourceBundle properties;

    static{
        try{
            properties = new PropertyResourceBundle(new FileInputStream(Util.RUTA));
            url= properties.getString("URL");
            driver= properties.getString("DRIVER");
            usuario= properties.getString("USER");
            password= properties.getString("PASSWORD");

            Class.forName(driver);

        }
        catch(IOException e){
            System.out.println("Error:"+e);
        }catch (ClassNotFoundException e) {
            System.out.println("");
        }
    }

    public static Connection getConnection() {
        Connection connection=null;
        try{
            connection = DriverManager.getConnection(url,usuario,password);
        }
        catch(SQLException e){
            //Error en base de datos no se puede lograr la conexion
            System.out.println(Util.ERROR1+e);
        }
        return connection;
    }

}