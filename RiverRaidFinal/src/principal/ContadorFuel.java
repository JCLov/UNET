
package principal;

import javax.swing.*;
/**
 * Clase que carga el contador de Combustible(fuel).
 * @author Jose Lovera 
 * @author Ceballos Fernanda
 * @version 05/08/2022
 */
public class ContadorFuel {
    //ATRIBUTOS
    private JFrame frame;
    private CargaImagenes cargaima;
    protected static int cont=75;
    protected int y=420;
    protected JLabel lblFuelBar, lblMedidor;
    /**
     * Constructor que carga las imagenes en el frame que se le pasa por paramétro.
     * @param frame Se le envia el JFrame como parametro para la carga del label.
     * Inicializa atributos.
     */
    ContadorFuel(JFrame frame){
        cargaima = new CargaImagenes();
        this.frame = frame;
        lblFuelBar=cargaima.cargarImagen(lblFuelBar, this.frame, "../assets/fuelBar.png", 185, y, 112, 21);
        lblMedidor=cargaima.cargarImagen(lblMedidor, this.frame, "../assets/medidor.png", 285, y, 10, 21);
    }
    /**
     * Método que se encarga de posicion el label medidor de fuel(barra de combustible) a tope
     */
    public void recargarFuel(){
        lblMedidor.setLocation(285, y);
    }
    /**
     * Método que se encarga de actualizar las propiedades del fuel, actualiza el label que irá 
     * disminuyendo según el contador.
     */
    public void actualizarFuel(){
        if(cont!=0){
            cont--;
        }else{
            lblMedidor.setLocation(lblMedidor.getX()-1, y);
            cont=75;
        }
    }
    /**
     * Método que quita el contador de Fuel, esto mediante el establecimienro del label
     * suera de pantalla.
     */
    public void quitarContadorFuel(){
        lblMedidor.setLocation(600, 600);
        lblFuelBar.setLocation(600, 600);
    }
}
