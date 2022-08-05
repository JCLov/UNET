package principal;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
/**
 * Clase que encargará de todo lo relativo al componente de Mapa.
 * @author Jose Lovera 
 * @author Ceballos Fernanda
 * @version 05/08/2022
 */
public class Mapa {
    //ATRIBUTOS
    private Timer timer;
    private CargaImagenes cargaima;
    private JLabel lblMapa, lblFinal;
    private JFrame frame;
    /**
     * Método constructor que mediante cargarMapa() y moverMapa() permiten ingresar 
     * el mapa a ventana.
     * @param frame e le envia el JFrame como parametro para la carga del label.
     * Inicializa atributos.
     */
    Mapa(JFrame frame){
        this.frame=frame;
        cargarMapa();
        moverMapa();
    }
    /**
     * Método que carga las label de mapa. Esto mediante el uso de la clase cargaima
     * y su método cargarImagen.
     */
    private void cargarMapa() {
        cargaima = new CargaImagenes();
        lblMapa = cargaima.cargarImagen(lblMapa, this.frame, "../assets/mapa.png", 0, -1635, 500, 2100);
        lblFinal = cargaima.cargarImagen(lblMapa, this.frame, "../assets/finalmapa.png", 0, -2085, 500, 450);
    }
    /**
     * Método que mueve el mapa estableciendo su label con un aumento en Y.
     */
    public void moverMapa() {
       //Mover Sprite  
       lblMapa.setBounds(0, lblMapa.getY()+1, lblMapa.getWidth(), lblMapa.getHeight());
       lblFinal.setBounds(0, lblFinal.getY()+1, lblFinal.getWidth(), lblFinal.getHeight());
    }
    /**
     * Método que reinicia la posición del label de mapa.
     */
    public void reinciarMapa() {
       //Poner al principio   
       lblMapa.setBounds(0, -1635, lblMapa.getWidth(), lblMapa.getHeight());
       lblFinal.setBounds(0, -2085, lblFinal.getWidth(), lblFinal.getHeight());
    }
    /**
     * Método que retorna la posición en Y de mapa.
     * @return Retorna Y.
     */
    public int getPosY(){
        return lblMapa.getY();
    }
    /**
     * Método que retorna la posicio´n en Y de la imagen "FinalMapa"
     * @return 
     */
    public int getPosFin(){
        return lblFinal.getY();
    }
    /**
     * Saca el mapa de ventana mediante las posciones de las imagenes mapa.
     */
    public void quitarMapa(){
        lblMapa.setBounds(600, 600, lblMapa.getWidth(), lblMapa.getHeight());
        lblFinal.setBounds(600, 600, lblFinal.getWidth(), lblFinal.getHeight());
    }
}
