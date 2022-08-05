package principal;

import java.applet.*;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;
import java.net.URL;

public class MenuPrincipal extends JFrame implements MouseListener{

    private JLabel labelFondo,labelInfo,labelPlay, labelVolver,labelNombre, labelSalir, labelPagInfo;
    private JTextField txtnombre;
    private CargaImagenes cargaima;
    private String nombre;
    private int bandInfo;
    private URL myURL;
    private AudioClip fondo,boton;

    /**
     * Constructor de la clase que forma la ventana y sus componentes.
     */
    public MenuPrincipal() {
        super("River Raid");
        cargaima = new CargaImagenes();
        setLayout(null);
        setSize(500,500);
        setLocationRelativeTo(null);
        definirVentana();
        inciarListener();
        setVisible(true);
        setResizable(false);
        nombre="";
        iniciarSonidos();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
    }//Constructor

    /**
     * Instancia los componentes de la ventana, les da una posicion y los agrega.
     */
    private void definirVentana(){
        bandInfo=0;
        labelVolver = cargaima.cargarImagen(labelVolver, this, "../assets/volver.png", 900, 900, 136, 21);
        labelPagInfo = cargaima.cargarImagen(labelPagInfo, this, "../assets/creditos.png", 900, 900, 500, 500);
        labelPlay = cargaima.cargarImagen(labelPlay, this, "../assets/jugar.png", 200, 330, 89, 17);
        labelInfo = cargaima.cargarImagen(labelInfo, this, "../assets/info.png", 150, 370, 193, 17);
        labelSalir = cargaima.cargarImagen(labelSalir, this, "../assets/salir.png", 200, 410, 89, 17);
        labelNombre = cargaima.cargarTexto(labelNombre, this, "Nombre: ", Color.WHITE, 130, 270, 120, 60);
        txtnombre = new JTextField(nombre, 20);
        txtnombre.setBounds(185, 290, 120, 17);
        getContentPane().add(txtnombre);
        labelFondo = cargaima.cargarImagen(labelFondo, this, "../assets/menu.png", 0, 0, 500, 500);
    }//DefinirVentana

    /**
      * Inicializa los sonidos.
      */
    private void iniciarSonidos(){
        myURL = this.getClass().getResource("../sonidos/fondo.wav");
        fondo = Applet.newAudioClip(myURL);
        fondo.loop();
        myURL = this.getClass().getResource("../sonidos/boton.wav");
        boton = Applet.newAudioClip(myURL);
    }//iniciarSonidos
    
    /**
      * Inicializa el listener.
      */
    private void inciarListener(){
        labelInfo.addMouseListener(this);
        labelPlay.addMouseListener(this);
        labelSalir.addMouseListener(this);
        labelVolver.addMouseListener(this);
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        nombre = txtnombre.getText();
        if(e.getSource()==labelPlay){
            if(bandInfo==0){
                if(txtnombre.getText().compareTo("")!=0){
                    boton.play();
                    fondo.stop();
                    Nivel obj = new Nivel(nombre);
                    dispose();
                }else{
                    JOptionPane.showConfirmDialog(this, "Ingresa tu nombre!!","Error", JOptionPane.CLOSED_OPTION);
                }
            }
        }
        if(e.getSource()==labelInfo){
            boton.play();
            fondo.loop();
            labelPagInfo.setBounds(0, 0, labelPagInfo.getWidth(), labelPagInfo.getHeight());
            labelVolver.setBounds(20, 50, labelVolver.getWidth(), labelVolver.getHeight());
            bandInfo=1;
            txtnombre.setVisible(false);
        }
        if(e.getSource()==labelVolver){
            boton.play();
            fondo.loop();
            labelPagInfo.setBounds(900, 900, labelPagInfo.getWidth(), labelPagInfo.getHeight());
            labelVolver.setBounds(900, 900, labelVolver.getWidth(), labelVolver.getHeight());
            bandInfo=0;
            txtnombre.setVisible(true);
        }
        if(e.getSource()==labelSalir){
            boton.play();
            JOptionPane.showMessageDialog(null, "Hasta Luego");
            System.exit(0);
        }
    }//mousePressed

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}//Menu Principal
