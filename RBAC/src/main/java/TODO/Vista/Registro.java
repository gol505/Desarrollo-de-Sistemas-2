package TODO.Vista;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static java.sql.DriverManager.getConnection;

public class Registro extends JFrame implements ActionListener {

    JTextField NomUsuario, NomPersonal, Apellido;
    JPasswordField contraseña;
    JLabel l1;
    JButton Registro;
    String Usu,Perso,Apell,contra;


    public Registro() {
        setName("Juguetería la chinita");
        setSize(600, 400);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension text = new Dimension(200, 40);
        Dimension btn = new Dimension(150, 40);
        Dimension Lbl = new Dimension(200, 40);

        l1 = new JLabel("Registro");
        l1.setFont(new Font("Arial", Font.CENTER_BASELINE, 30));

        JLabel Titulo = new JLabel("REGISTRO");
        Titulo.setLocation(230, 20);
        Titulo.setSize(Lbl);
        Titulo.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));


        NomUsuario = new JTextField();
        NomUsuario.setSize(text);
        NomUsuario.setLocation(245, 70);

        JLabel NomUsua = new JLabel("Nombre de usuario:");
        NomUsua.setLocation(100, 70);
        NomUsua.setSize(Lbl);

        NomPersonal = new JTextField();
        NomPersonal.setSize(text);
        NomPersonal.setLocation(245, 120);

        JLabel NomPerso = new JLabel("Nombre Personal");
        NomPerso.setLocation(100, 120);
        NomPerso.setSize(Lbl);

        Apellido = new JTextField();
        Apellido.setSize(text);
        Apellido.setLocation(245, 170);

        JLabel Ape = new JLabel("Apellido");
        Ape.setLocation(100, 170);
        Ape.setSize(Lbl);

        contraseña = new JPasswordField();
        contraseña.setSize(text);
        contraseña.setLocation(245, 220);

        JLabel Contra = new JLabel("Conrtaseña");
        Contra.setLocation(100, 220);
        Contra.setSize(Lbl);

        Registro = new JButton("Registrarse");
        Registro.setSize(btn);
        Registro.setLocation(350, 300);


        add(NomPersonal);
        add(NomUsua);
        add(NomUsuario);
        add(NomPerso);
        add(Apellido);
        add(Ape);
        add(contraseña);
        add(Contra);
        add(Registro);
        add(Titulo);
        Registro.addActionListener(this);

        setVisible(true);
    }

    public void registrarUsuario(String usuario, String nombre, String apellido, String contrasena) {
        try {
            String[] options = { "Cliente", "Empleado", "Admin" };
            int rolDefecto=1;
            var selection = JOptionPane.showOptionDialog(null, "Que rol desea tener?", "ROL",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (selection == 0) {
                rolDefecto=3;
            }
            if (selection == 1) {
                String pinCliente=JOptionPane.showInputDialog(null, "Ingresa el pin");
                String contra="gael";
                if (pinCliente.equals(contra)&& pinCliente != null){
                    rolDefecto=1;

                }
            }
            if (selection == 2) {
                String pinAdmin=JOptionPane.showInputDialog(null, "Ingresa el pin");
                String contra="luis";
                if (pinAdmin.equals(contra) && pinAdmin != null){
                    rolDefecto=2;
                }

            }
            String url = "jdbc:mysql://localhost:3306/Jugueteria";
            String username = "root";
            String password = "hola123";


            Connection conn = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO usuarios(Usuario, Nombre, Apellido, Contraseña, Rol) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, usuario);
            pstmt.setString(2, nombre);
            pstmt.setString(3, apellido);
            pstmt.setString(4, contrasena);
            pstmt.setInt(5,rolDefecto );


            int filasInsertadas = pstmt.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("Registro exitoso");
                Pantallini p =new Pantallini();
            } else {
                System.out.println("Error al registrar usuario");
            }

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Usuario o contraseña erroneos");
            e.printStackTrace();
        }
    }




    public String getUsu() {
        return Usu;
    }

    public void setUsu(String usu) {
        Usu = usu;
    }

    public String getPerso() {
        return Perso;
    }

    public void setPerso(String perso) {
        Perso = perso;
    }

    public String getApell() {
        return Apell;
    }

    public void setApell(String apell) {
        Apell = apell;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==NomUsuario){
            setUsu(NomUsuario.getText());
        }
        if(e.getSource()==NomPersonal){
            setPerso(NomPersonal.getText());
        }
        if(e.getSource()==Apellido){
            setApell(Apellido.getText());
        }
        if(e.getSource()==contraseña){
            setContra(String.valueOf(contraseña.getPassword()));
        }


        if(e.getSource()==Registro){

            registrarUsuario(NomUsuario.getText(),NomPersonal.getText(),Apellido.getText(),String.valueOf(contraseña.getPassword()));
        }
        setVisible(false);
        }




}