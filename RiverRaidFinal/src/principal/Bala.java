
package principal;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
/**
 * Esta clase define los metodos y atributos de las balas del juego.
 * @author Jose Lovera 
 * @author Ceballos Fernanda
 * @version 05/08/2022
 */
public class Bala {
    //Atributos de la clase
    private JFrame frame;
    private CargaImagenes cargaima;
    protected int x, y;
    protected JLabel lblBala;
    /**
     * Constructor de la clase Bala
     * @param frame Se le envia el JFrame como parametro para la carga del label.
     * Inicializa atributos
     */
    public Bala(JFrame frame) {
        cargaima = new CargaImagenes();
        this.y=-500;
        this.x=-500;
        this.frame = frame;
        lblBala=cargaima.cargarImagen(lblBala, this.frame, "../assets/bala.png", this.x, this.y, 5, 9);
        
    }
    /**
     * Método mover 
     * Establece la posición del label restando en Y
     */
    public void mover(){
        lblBala.setBounds(lblBala.getX(), lblBala.getY()-3, lblBala.getWidth(), lblBala.getHeight());
    }
    /**
     * Método mostrar 
     * Método que muestra la bala en la posición de los parámetros solicitados
     * @param x Posición en X.
     * @param y Posición en Y.
     */
    public void mostrar(int x, int y){
        this.x=x;
        this.y=y;
        this.lblBala.setBounds(x, y, 5, 9);
    }
    /**
     * Método quitarBala
     * Quita la bala de pentana estableciendo la posición del label.
     */
    public void quitarBala(){
        lblBala.setLocation(600, 600);
    }
}
