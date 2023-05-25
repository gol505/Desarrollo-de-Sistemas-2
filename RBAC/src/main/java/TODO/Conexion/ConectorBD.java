package TODO.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectorBD {
    private static String url = "jdbc:mysql://localhost:3306/Jugueteria";
    private static String username = "root";
    private static String password = "hola123";
    private static Connection connection;

    public static Connection getInstance() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;
    }





}