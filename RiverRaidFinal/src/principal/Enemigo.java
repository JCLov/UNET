package principal;

import javax.swing.*;
import java.awt.*;
/**
 * Clase que encargará de todo lo relativo al componente de enemigo.
 * @author Jose Lovera 
 * @author Ceballos Fernanda
 * @version 05/08/2022
 */
public class Enemigo {
    //ATRIBUTOS
    private int x, y, tipoEnemigo;
    protected JLabel lblEnemigo;
    private CargaImagenes cargaima;
    private JFrame frame;
    private boolean Dir;
    /**
     * Constructor de la clase enemigo, donde establece el tipo de enemigo de forma aleatoria y se carga su
     * imagen.
     * @param frame Se le envia el JFrame como parametro para la carga del label.
     * Inicializa atributos
     */
    Enemigo(JFrame frame){
        cargaima = new CargaImagenes();
        this.y=-900;
        this.x=0;
        this.frame=frame;
        tipoEnemigo=(int)Math.floor(Math.random()*2+1);
    }
    /**
     * Ségun el tipo de enemigo, escogido de forma aleatoria por el costructor,
     * se carga la imagen apropiada para el enemigo según una dirección aleatoria que
     * este mismo calcula. 
     */
    public void prepararEnemigo(){
        this.x=x;
        switch(tipoEnemigo){
            case 1://Heli
                if((int)Math.floor(Math.random()*2+1)==1){
                    lblEnemigo=cargaima.cargarImagen( lblEnemigo, this.frame, "../assets/heliDer.png", this.x, this.y, 32, 18);
                    Dir=true;
                }else{
                    lblEnemigo=cargaima.cargarImagen( lblEnemigo, this.frame, "../assets/heli.png", this.x, this.y, 32, 18);
                    Dir=false;
                }
                break;
            case 2: //bote
                if((int)Math.floor(Math.random()*2+1)==1){
                    lblEnemigo=cargaima.cargarImagen( lblEnemigo, this.frame, "../assets/boteDer.png", this.x, this.y, 64, 18);
                    Dir=true;
                }else{
                    lblEnemigo=cargaima.cargarImagen( lblEnemigo, this.frame, "../assets/bote.png", this.x, this.y, 64, 18);
                    Dir=false;
                }   
                break;
        }
    }
    /**
     * Mueve la label según el tipo de enemigo en una velocidad diferente y 
     * según la dirección del propio enemigo.
     */
    public void moverEnemigo(){
        switch(tipoEnemigo){
            case 1://Heli
                if(Dir==true){
                    lblEnemigo.setBounds(lblEnemigo.getX()+2, lblEnemigo.getY(), lblEnemigo.getWidth(), lblEnemigo.getHeight());
                }else{
                    lblEnemigo.setBounds(lblEnemigo.getX()-2, lblEnemigo.getY(), lblEnemigo.getWidth(), lblEnemigo.getHeight());
                }
                break;
            case 2: //bote
                if(Dir==true){
                    lblEnemigo.setBounds(lblEnemigo.getX()+1, lblEnemigo.getY(), lblEnemigo.getWidth(), lblEnemigo.getHeight());
                }else{
                    lblEnemigo.setBounds(lblEnemigo.getX()-1, lblEnemigo.getY(), lblEnemigo.getWidth(), lblEnemigo.getHeight());
                }
                break;
        }
    }
    /**
     * Método que permite mver al enemigo en su posición de Y, esto estableciendo los atributos de la label.
     */
    public void moverEnemigoY(){
        lblEnemigo.setBounds(lblEnemigo.getX(), lblEnemigo.getY()+1, lblEnemigo.getWidth(), lblEnemigo.getHeight());
    }
    /**
     * Este método se usa para que dependiendo del enemigo, 
     */
    public void cambiarDir(){
        switch(tipoEnemigo){
            case 1://Heli
                if(Dir==true){
                    lblEnemigo.setIcon(new ImageIcon(this.getClass().getResource("../assets/heli.png")));
                    Dir=false;
                }else{
                    lblEnemigo.setIcon(new ImageIcon(this.getClass().getResource("../assets/heliDer.png")));
                    Dir=true;
                }
                break;
            case 2: //bote
                if(Dir==true){
                    lblEnemigo.setIcon(new ImageIcon(this.getClass().getResource("../assets/bote.png")));
                    Dir=false;
                }else{
                    lblEnemigo.setIcon(new ImageIcon(this.getClass().getResource("../assets/boteDer.png")));
                    Dir=true;
                }
                break;
        }
    }
    /**
     * Método que permite mostrar a un enemigo con la posición de la label.
     * @param x Parametro para jugar con las posiciones en X de enemigo.
     */
    public void mostrarEnemigo(int x){
        this.x=x;
        lblEnemigo.setBounds(this.x, -18, lblEnemigo.getWidth(), lblEnemigo.getHeight());
    }
    /**
     * Método que estable el enemigo fuera de pantalla mediante el 
     * establecimeinto de la label según parámetros.
     * @param x Posición en X
     * @param y Posición en Y
     */
    public void quitarEnemigo(int x, int y){
        this.x=x;
        this.y=y;
        lblEnemigo.setBounds(this.x, this.y, lblEnemigo.getWidth(), lblEnemigo.getHeight());
    }
    /**
     * Retorna la posición en Y de la label enemigo.
     * @return Posición en Y.
     */
    public int getPosY(){
        return lblEnemigo.getY();
    }
    /**
     * Retorna la posición en X de la label enemigo.
     * @return Posición en X.
     */
    public int getPosX(){
        return lblEnemigo.getX();
    }
    /**
     * Retorna el ancho de la label enemigo.
     * @return Ancho de label.
     */
    public int getAncho(){
        return lblEnemigo.getWidth();
    }
    /**
     * Retorna la label de enemigo.
     * @return JLabel de enemigo.
     */
    public JLabel getEnemigo()
    {
        return this.lblEnemigo;
    }
    /**
     * Método para establecer una posición en y de enemigo.
     * @param y Paramétro que establecerá la posición en Y.
     */
    public void setY(int y)
    {
        this.y=y;
    }
}
