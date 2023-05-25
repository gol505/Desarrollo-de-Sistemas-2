package TODO.Movimientos;

import javax.swing.*;
import java.sql.*;

public class Eliminar {
    public void eliminarProducto() {
        try {
            String url = "jdbc:mysql://localhost:3306/Jugueteria";
            String username = "root";
            String password = "hola123";
            String nombre= JOptionPane.showInputDialog("¿Cuál va a ser el nombre del producto a eliminar ?");
            int cantidad=Integer.parseInt(JOptionPane.showInputDialog("¿Qué cantidad ?"));
            //double precio=Double.parseDouble(JOptionPane.showInputDialog("¿Qué precio va a tener el producto nuevo?"));
            Connection conn = DriverManager.getConnection(url, username, password);
            String sql = "SELECT Cantidad FROM juguetes WHERE Nombre = ? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, nombre);
           // pstmt.setInt(2, cantidad);
            //pstmt.setDouble(3, precio);

            ResultSet resultSet= pstmt.executeQuery();

            if (resultSet.next()) {
                int cantidadActual = resultSet.getInt("Cantidad");
                if (cantidadActual <= cantidad) {
                    sql = "DELETE FROM juguetes WHERE Nombre = ?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, nombre);
                    pstmt.executeUpdate();
                    System.out.println("Se eliminó el producto");
                } else {
                    sql = "UPDATE juguetes SET Cantidad = ? WHERE Nombre = ?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, cantidadActual - cantidad);
                    pstmt.setString(2, nombre);
                    pstmt.executeUpdate();
                    System.out.println("Se actualizó la cantidad del producto");
                }
            } else {
                System.out.println("No se encontró el producto");
            }

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
