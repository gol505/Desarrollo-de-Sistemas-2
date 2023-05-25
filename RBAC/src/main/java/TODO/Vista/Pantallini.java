package TODO.Vista;

import TODO.Conexion.ConectorBD;
import TODO.Usuario.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Pantallini extends JFrame implements ActionListener {
    private Connection getConnection() throws SQLException {
        return ConectorBD.getInstance();
    }
    JTextField pU;
    JButton reg,OK;
    JPanel panel, panel1;
    JLabel user, pssd, logo;
    JPasswordField pP;
    String usu,contra;



    public Pantallini(){

        setLayout(new GridBagLayout());
        setTitle("Jugueteria La Chinita");
        setIconImage(new ImageIcon("E:/DesarrolloSistemas/La Chinita.jpg").getImage());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600,400);
        setLocationRelativeTo(null);

        reg=new JButton();
        logo= new JLabel();
        ImageIcon loguito= new ImageIcon("E:/DesarrolloSistemas/La Chinita.jpg");
        Image fotin= loguito.getImage().getScaledInstance(200,150, Image.SCALE_DEFAULT);
        logo.setIcon(new ImageIcon(fotin));


        panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.cyan);
        panel1= new JPanel(new GridBagLayout());

        //botones
        panel1= new JPanel(new GridBagLayout());
        reg= new JButton("Registrarse");
        OK= new JButton("Iniciar sesión");



        user = new JLabel("Usuario:");
        pssd = new JLabel("Contraseña:");


        pU= new JTextField( 10);
        pP= new JPasswordField(10);

        GridBagConstraints constraints= new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);

        panel.add(logo, constraints);



        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        panel.add(user, constraints);


        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(pU, constraints);


        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(pssd, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;



        panel.add(pP, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;

        panel.add(OK, constraints);

        getContentPane().add(panel);

        constraints.gridx = 0;
        constraints.gridy = 6;

        panel.add(reg, constraints);
        getContentPane().add(panel);

        getContentPane().add(panel);

        OK.addActionListener(this);

        reg.addActionListener(this);


        TextPrompt prompt= new TextPrompt("Nombre de usuario", pU);
        TextPrompt prompt1= new TextPrompt("Contraseña", pP);




        setVisible(true);

    }



    public void iniciarSesion(String usuario, String contra) {
        String url = "jdbc:mysql://localhost:3306/Jugueteria";
        String username = "root";
        String password = "hola123";
        String sql;
        sql="SELECT * FROM usuarios WHERE Usuario=? AND Contraseña=?";
        Usuario cliente=null;
        try{
            Connection conn = DriverManager.getConnection(url,username,password);
            Statement stmt=conn.createStatement();
            PreparedStatement Pstmt=conn.prepareStatement(sql);
            Pstmt.setString(1,getUsu());
            Pstmt.setString(2,getContra());
            ResultSet rs=Pstmt.executeQuery();

            if (rs.next()){
                cliente =new Usuario();
                cliente.setUsuario(rs.getString("Usuario"));
                cliente.setNombre(rs.getString("Nombre"));
                cliente.setApellido(rs.getString("Apellido"));
                cliente.setContrasena(rs.getString("Contraseña"));
                cliente.setRol(rs.getInt("Rol"));

                int rolsin= cliente.getRol();
            }
            cliente.setRol(rs.getInt("Rol"));

            int rolsin= cliente.getRol();



            assert cliente != null;
            if(cliente.getUsuario().equals(usuario) && cliente.getContrasena().equals(contra) ){
                if (cliente.getRol()==2){
                    Pantalla2 p2= new Pantalla2();
                    p2.setVisible(true);
                } else if (cliente.getRol()==3){
                    Pantalla3 p3= new Pantalla3();
                    p3.setVisible(true);
                }else if (cliente.getRol()==1){
                    Pantalla p= new Pantalla();
                    p.setVisible(true);
                }


            }
            else{
                JOptionPane.showMessageDialog(null,"Usuario o contraseña erroneos");
                Pantallini p=new Pantallini();
            }





        }
        catch (SQLException e) {


            JOptionPane.showMessageDialog(null,"Usuario o contraseña erroneos");
            Pantallini pi= new Pantallini();


        }/*
        catch(NullPointerException peto){
            JOptionPane.showMessageDialog(null,"Usuario o contraseña erroneos");
            peto.printStackTrace();

        }*/



    }

    public String getUsu() {
        return usu;
    }

    public void setUsu(String usu) {
        this.usu = usu;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==pU){
            setUsu(pU.getText());
        }
        if(e.getSource()==pP) {
            setContra(String.valueOf(pP.getPassword()));
        }
        if(e.getSource()==reg){
            Registro registro=new Registro();
            setVisible(false);
            //DISPOSE_ON_CLOSE();
        }
        if(e.getSource()==OK){
            setUsu(pU.getText());
            setContra(String.valueOf(pP.getPassword()));
            iniciarSesion(getUsu(),getContra());
            setVisible(false);


        }


    }
}
