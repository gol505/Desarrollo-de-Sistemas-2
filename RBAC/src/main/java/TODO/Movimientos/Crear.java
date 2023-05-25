package TODO.Movimientos;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Crear {
    public void crearProducto() {
        try {
            String url = "jdbc:mysql://localhost:3306/Jugueteria";
            String username = "root";
            String password = "hola123";
            String nombre= JOptionPane.showInputDialog("¿Cuál va a ser el nombre del producto a registrar ?");
            int cantidad=Integer.parseInt(JOptionPane.showInputDialog("¿Qué cantidad inicial ?"));
            double precio=Double.parseDouble(JOptionPane.showInputDialog("¿Qué precio va a tener el producto nuevo?"));
            Connection conn = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO juguetes(Nombre, Cantidad, Precio) VALUES(?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, nombre);
            pstmt.setInt(2, cantidad);
            pstmt.setDouble(3, precio);


            int filasInsertadas = pstmt.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("Registro exitoso");
            } else {
                System.out.println("Error al registrar usuario");
            }

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
