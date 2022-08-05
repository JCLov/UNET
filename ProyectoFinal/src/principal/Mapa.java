package principal;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Mapa {
    private Timer timer;
    private CargaImagenes cargaima;
    private JLabel lblMapa;
    private JFrame frame;

    
    Mapa(JFrame frame){
        this.frame=frame;
        cargarMapa();
        moverMapa();
    }
    private void cargarMapa() {
        cargaima = new CargaImagenes();
        lblMapa = cargaima.cargarImagen(lblMapa, this.frame, "../assets/mapa.png", 0, -1635, 500, 2100);
    }
    public void moverMapa() {
       //Mover Sprite  
       lblMapa.setBounds(0, lblMapa.getY()+1, lblMapa.getWidth(), lblMapa.getHeight());
       //lblMapa = cargaima.cargarImagen(lblMapa, this.frame, "../assets/mapa.png", 0, lblMapa.getY(), 500, 2100);
    }
    public void reinciarMapa() {
       //Poner al principio   
       lblMapa.setBounds(0, -1635, lblMapa.getWidth(), lblMapa.getHeight());     
    }
    public int getPosY(){
        return lblMapa.getY();
    }
}
