package principal;

import javax.swing.*;
import java.awt.*;

public class Enemigo {
    private int x, y, tipoEnemigo;
    private JLabel lblEnemigo;
    private CargaImagenes cargaima;
    private JFrame frame;
    private boolean Dir;
    
    Enemigo(JFrame frame){
        cargaima = new CargaImagenes();
        this.y=-900;
        this.x=0;
        this.frame=frame;
        tipoEnemigo=(int)Math.floor(Math.random()*2+1);
    }
    public void prepararEnemigo(){
        this.x=x;
        switch(tipoEnemigo){
            case 1://Heli
                if(this.x>250){
                    lblEnemigo=cargaima.cargarImagen( lblEnemigo, this.frame, "../assets/heliDer.png", this.x, this.y, 32, 18);
                    Dir=true;
                }else{
                    lblEnemigo=cargaima.cargarImagen( lblEnemigo, this.frame, "../assets/heli.png", this.x, this.y, 32, 18);
                    Dir=false;
                }
                break;
            case 2: //bote
                if(this.x>250){
                    lblEnemigo=cargaima.cargarImagen( lblEnemigo, this.frame, "../assets/boteDer.png", this.x, this.y, 64, 18);
                    Dir=true;
                }else{
                    lblEnemigo=cargaima.cargarImagen( lblEnemigo, this.frame, "../assets/bote.png", this.x, this.y, 64, 18);
                    Dir=false;
                }   
                break;
        }
    }
    public void moverEnemigo(){
        if(Dir==true){
            lblEnemigo.setBounds(lblEnemigo.getX()+1, lblEnemigo.getY()+1, lblEnemigo.getWidth(), lblEnemigo.getHeight());
        }else{
            lblEnemigo.setBounds(lblEnemigo.getX()-1, lblEnemigo.getY()+1, lblEnemigo.getWidth(), lblEnemigo.getHeight());
        }
    }
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
    public void mostrarEnemigo(int x){
        this.x=x;
        lblEnemigo.setBounds(x, -18, lblEnemigo.getWidth(), lblEnemigo.getHeight());
    }
    public int getPosY(){
        return lblEnemigo.getY();
    }
    public int getPosX(){
        return lblEnemigo.getX();
    }
    public int getAncho(){
        return lblEnemigo.getWidth();
    }
}
