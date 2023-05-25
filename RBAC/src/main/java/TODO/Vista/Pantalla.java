package TODO.Vista;

import TODO.Conexion.ConectorBD;
import TODO.Movimientos.Crear;
import TODO.Movimientos.Eliminar;
import TODO.Usuario.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static java.sql.DriverManager.getConnection;

public class Pantalla extends JFrame implements ActionListener {
    private Connection getConnection() throws SQLException {
        return ConectorBD.getInstance();
    }

    JPanel pM,panel;
    JMenuBar barra;
    JButton menu1, menu2, menu3;
    JTextPane textito;



    public Pantalla() throws SQLException  {
        verificarRol();

        setTitle("Jugueteria la chinita");
        setIconImage(new ImageIcon("E:/DesarrolloSistemas/La Chinita.jpg").getImage());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setSize(800,600);
        setLocationRelativeTo(null);
        pM= new JPanel();
        pM.setLayout(null);

        add(pM);
        panel= new JPanel();
        panel.setLayout(null);
        panel.setBounds(200,170,400,300);



        barra= new JMenuBar();
        barra.setLocation(275,25);
        barra.setSize(250,30);
        menu1= new JButton("Ver Productos");
        menu2= new JButton("Crear");
        menu3= new JButton("Eliminar");
        barra.add(menu1);
        barra.add(menu2);
        barra.add(menu3);

        menu1.addActionListener(this);
        menu2.addActionListener(this);
        menu3.addActionListener(this);
        menu1.updateUI();
        //add(panel);
        pM.add(barra);
        JScrollPane scroll = new JScrollPane( textito );













        pM.setBackground(Color.decode("#F1C1B7"));
        panel.setBackground(Color.decode("#F1C1B7"));

        setVisible(true);
    }
    Crear crear= new Crear();

    Eliminar eliminar= new Eliminar();
    Usuario usuario =new Usuario();

    public void verificarRol(){
        if (usuario.getRol() ==3){
            System.out.println(usuario.getRol());
            menu3.setEnabled(false);
            menu2.setEnabled(false);
        }

    }

    public void verProductos() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet= statement.executeQuery("SELECT Nombre, Cantidad, Precio FROM juguetes")){

            StringBuilder stringBuilder = new StringBuilder();
            while (resultSet.next()) {

                String nombreP = resultSet.getString("Nombre");
                int cantidad = resultSet.getInt("Cantidad");
                double precio = resultSet.getDouble("Precio");

                stringBuilder.append("Nombre: ").append(nombreP).append("\t");
                stringBuilder.append("Cantidad: ").append(cantidad).append("\t");
                stringBuilder.append("Precio: ").append(precio).append("\t");
                stringBuilder.append("\n");





            }
            textito = new JTextPane();
            textito.setLocation(40,65);
            textito.setSize(500,600);


            textito.setText(stringBuilder.toString());
            textito.setEditable(false);
            panel.add(textito);

            pM.add(panel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }






    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menu1) {
            verProductos();
        }
        if (e.getSource()== menu2) {
            crear.crearProducto();
        }
        if (e.getSource()== menu3){
            eliminar.eliminarProducto();
        }
    }
}
