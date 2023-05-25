package TODO.Conexion;

import java.sql.Connection;
import java.sql.SQLException;

public class BaseDatos {
    private Connection getConnection() throws SQLException {
        return ConectorBD.getInstance();
    }
}
