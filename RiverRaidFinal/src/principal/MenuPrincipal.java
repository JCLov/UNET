package principal;

import java.applet.*;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;
import java.net.URL;
/**
 * Clase que encargará de todo lo relativo al menu de inicio del juego.
 * @author Jose Lovera 
 * @author Ceballos Fernanda
 * @version 05/08/2022
 */
public class MenuPrincipal extends JFrame implements MouseListener{
    //ATRIBUTOS
    private JLabel labelFondo,labelInfo,labelPlay, labelVolver,labelNombre, labelSalir, labelPagInfo;
    private JTextField txtnombre;
    private CargaImagenes cargaima;
    private String nombre;
    private int bandInfo;
    private URL myURL;
    private AudioClip fondo,boton;

    /**
     * Constructor de la clase MenuPrincipal que forma la ventana y sus componentes.
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
     * Instancia los componentes de la ventana, les da una posición y los agrega.
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
      * Inicializa los sonidos de la pantalla. 
      */
    private void iniciarSonidos(){
        myURL = this.getClass().getResource("../sonidos/fondo.wav");
        fondo = Applet.newAudioClip(myURL);
        fondo.loop();
        myURL = this.getClass().getResource("../sonidos/boton.wav");
        boton = Applet.newAudioClip(myURL);
    }//iniciarSonidos
    
    /**
      * Inicializa el listener de mouse.
      */
    private void inciarListener(){
        labelInfo.addMouseListener(this);
        labelPlay.addMouseListener(this);
        labelSalir.addMouseListener(this);
        labelVolver.addMouseListener(this);
    }
    /**
     * Evento que se usa para el momento en que el usuario presione el objeto.
     * @param e Objeto de los eventos de mouse.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        nombre = txtnombre.getText();
        if(e.getSource()==labelPlay){
            if(bandInfo==0){
                if(txtnombre.getText().compareTo("")!=0){
                    if(txtnombre.getText().length()<8){
                    boton.play();
                        fondo.stop();
                        Nivel obj = new Nivel(nombre);
                        dispose();
                    }else{
                        JOptionPane.showConfirmDialog(this, "Nombre demasiado largo!!","Error", JOptionPane.CLOSED_OPTION);
                    }
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
    /**
     * Evento que se usa para el momento en que el usuario presiona y suelta el objeto.
     * @param e Objeto de los eventos de mouse.
     */
    @Override
    public void mouseClicked(MouseEvent e) {}
    /**
     * Evento que se usa para el momento en que el usuario suelta el mouse.
     * @param e Objeto de los eventos de mouse.
     */
    @Override
    public void mouseReleased(MouseEvent e) {}
    /**
     * Evento para indicar que el puntero del mouse entro al area del objeto.
     * @param e 
     */
    @Override
    public void mouseEntered(MouseEvent e) {}
    /**
     * Evento para indicar que el puntero del mouse salio del area del objeto.
     * @param e 
     */
    @Override
    public void mouseExited(MouseEvent e) {}
}//Menu Principal
