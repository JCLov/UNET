
package principal;

import java.awt.*;
import javax.swing.*;

public class CargaImagenes {

    /**
     * Carga las imagenes les da un tamano y una posicion y las agrega al JFrame.
     * @param label Es un JLabel al cual se le va a asignar un ImageIcon el cual se le dara tamano posicion y se agregara al JFrame.
     * @param frame Es un JFrame al cual se le agregara el label.
     * @param x Coordenadas en X donde se colocara el label.
     * @param y Coordenadas en Y donde se colocara el label.
     * @param width Dimensiones del label.
     * @param height Dimensiones del label.
     * @return Retorna el label.
     */
     public JLabel cargarImagen(JLabel label, JFrame frame, String nom, int x, int y, int width, int height){
        label = new JLabel(new ImageIcon(this.getClass().getResource(nom)));
        label.setBounds(x, y, width, height);
        frame.getContentPane().add(label);
        return label;
    }//cargarImagen

     /**
      * Carga un label con testo.
      * @param label Es un JLabel al cual se le va a asignar texto el cual se le dara tamano posicion y se agregara al JFrame.
      * @param frame Es un JFrame al cual se le agregara el label.
      * @param texto Es el texto que va a llevar el label.
      * @param color Es el color que va a llevar el texto.
      * @param x Coordenadas en X donde se colocara el label.
      * @param y Coordenadas en Y donde se colocara el label.
      * @param width Dimensiones del label.
      * @param height Dimensiones del label.
      * @return Retorna el label.
      */
     public JLabel cargarTexto(JLabel label, JFrame frame, String texto,Color color, int x, int y, int width, int height){
         label = new JLabel();
         label.setText(texto);
         label.setBounds(x, y, width, height);
         label.setForeground(color);
         frame.getContentPane().add(label);
         return label;
     }//cargarTexto
}//CargaImagenes
