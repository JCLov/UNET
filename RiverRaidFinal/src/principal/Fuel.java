
package principal;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * Clase que encargará de todo lo relativo al componente de fuel o combustible.
 * @author Jose Lovera 
 * @author Ceballos Fernanda
 * @version 05/08/2022
 */
public class Fuel {
    //atributos
    private JFrame frame;
    private CargaImagenes cargaima;
    protected int x, y;
    protected JLabel lblFuel;
    protected boolean recargable;
    /**
     * Constructor de la clase fuel, donde se establece los atributos del mismo y 
     * son cargados en la ventana. Define su posicion, abel, y si por el momento 
     * permite recargar o no el fuel.
     * @param frame Se le envia el JFrame como parametro para la carga del label.
     * @param x Posición en X.
     * @param y Posición en Y.
     * Inicializa atributos
     */
    public Fuel(JFrame frame, int x, int y) {
        cargaima = new CargaImagenes();
        this.y=y;
        this.x=x;
        this.frame = frame;
        this.recargable=true;
        lblFuel=cargaima.cargarImagen(lblFuel, this.frame, "../assets/fuel.png", this.x, this.y, 5, 19);
    }
    /**
     * Método mover que aumenta las posiciones en Y para simular movimiento.
     */
    public void mover(){
        this.y++;
    }
    /**
     * Método mostrar posiciona la imagen en la unicacion de X y Y actual. Este método
     * también cambia la imagen de fuel si esta en un estado de no recargar.
     */
    public void mostrar(){
        if(!recargable)
            lblFuel.setIcon(new ImageIcon(getClass().getResource("../assets/Expl.png")));
        this.lblFuel.setLocation(x, y);
    }
    /**
     * Método quitarFuel establece el atributo de ser recargable en falso para el 
     * cambio de imagen posterior y cumple la función de sacar el elemento de la 
     * ventana.
     */
    public void quitarFuel(){
        this.recargable=false;
        this.lblFuel.setLocation(600, 600);
    }
    
}
