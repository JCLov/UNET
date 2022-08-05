
package principal;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * Esta clase se encarga de establEcer los métodos y atributos de lo que será jugador
 * @author Jose Lovera 
 * @author Ceballos Fernanda
 * @version 05/08/2022
 */
public class Jugador {
    //Atributos de la clase
    private JFrame frame;
    private CargaImagenes cargaima;
    protected int x, y;
    protected int direccion;
    protected JLabel lblJugador;
    /**
     * Inicializo los atributos de jugador, junto a su direccion 
     * igual a 0(hacias arriba), por ultimo cargo la imagen de jugador 
     * mediante la funcion cargarImagen de la clase CargaImagenes
     * @param frame paso como parámetro la clase de la ventana
     */
    public Jugador(JFrame frame) {
        cargaima = new CargaImagenes();
        this.y=380;
        this.x=234;
        this.direccion=0;
        this.frame = frame;
        
        lblJugador=cargaima.cargarImagen( lblJugador, this.frame, "../assets/avion.png", this.x, this.y, 19, 20);
        
    }
    /**
     * Método que simulara moviento en las imagenes de Jugador. dependiendo de la dirección (0=arriba,-1=izquierda,1=derecha) se establecerás 
     * una imagen diferente para simultar una sensación de movimiento.
     */
    public void mover()
    {
        if(direccion==-1)
        {
            this.lblJugador.setIcon(new ImageIcon(getClass().getResource("../assets/avionIzq.png")));
        }
        else if(direccion==1)
        {
            this.lblJugador.setIcon(new ImageIcon(getClass().getResource("../assets/avionDer.png")));
        }   
        else
        {
            this.lblJugador.setIcon(new ImageIcon(getClass().getResource("../assets/avion.png")));
        }
    }
    /**
     * Este método es usado cuando el jugador pierde una vida en la clase nivel, se
     * llama para reiniciar la posición del jugador y su imagen a la del principio.
     */
    public void reiniciarJugador(){
        this.lblJugador.setIcon(new ImageIcon(getClass().getResource("../assets/avion.png")));
        this.x=234;
        this.y=380;
        this.lblJugador.setLocation(234, 380);
    }
    /**
     * Se ubica en los puntos "X" y "y" actuales
     */
    public void mostrar()
    {
        this.lblJugador.setLocation(x, y);
    }
    /**
     * Se elimina a jugador de la visibilidad de la ventana
     */
    public void quitarJugador(){
        this.lblJugador.setLocation(600, 600);
    }

}
