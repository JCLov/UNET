package principal;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Nivel extends JFrame{
    private String nombre;
    private Timer timer;
    private Mapa mapa;
    private int faseMapa;
    private Enemigo[] enemigos;
    private Rectangle[] colEnemigos, bordes;
    private int sigEnemigo;
    
    Nivel(String nombre){
        super("River Raid");
        setLayout(null);
        setSize(500,500);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        this.nombre=nombre;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cargarTodo();
    }

    private void cargarTodo() {
        cargarColMapa();
        cargarEnemigos();
        moverTodo();
        mapa= new Mapa(this);
        faseMapa=1;
    }
    private void moverTodo() {
        timer=new Timer(25, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                for(int i=2; i<bordes.length;i++){
                        bordes[i].setBounds((int) bordes[i].getX(), (int) (bordes[i].getY()+1), (int) bordes[i].getWidth(), (int) bordes[i].getHeight());
                    }
                for(int i=0; i<enemigos.length;i++){
                                      
                   for (Rectangle borde : bordes) {
                        if (colEnemigos[i].intersects(borde)) {
                            enemigos[i].cambiarDir();
                        }
                    }
                    enemigos[i].moverEnemigo();
                    colEnemigos[i].setBounds((int) enemigos[i].getPosX(), (int)(enemigos[i].getPosY()),(int) colEnemigos[i].getWidth(), (int) colEnemigos[i].getHeight());
                 
                    if(mapa.getPosY()<-1100&&enemigos[i].getPosY()==80){ //Frecuencia de enemigos
                        //if(mapa.getPosY()==)
                       crearEnemigos(sigEnemigo);
                    }else if(mapa.getPosY()>-1100&& mapa.getPosY()<-800&&enemigos[i].getPosY()==300){
                        crearEnemigos(sigEnemigo);
                    }else if(mapa.getPosY()>-1100&&enemigos[i].getPosY()==80){
                        crearEnemigos(sigEnemigo);
                    }
                    if(enemigos[i].getPosY()>460 || enemigos[i].getPosY()<-18){ //eliminar cuando salen de pantalla
                       sigEnemigo=i;
                     }            
                }
                if(!(mapa.getPosY()==0))
                    mapa.moverMapa();
                //if(Si AvionY==5){reiniciarMapa();} 
            }
        });
        timer.start();
    }
    
    //------------------------- ENEMIGOS ------------------
    
    private void cargarEnemigos(){
        enemigos=new Enemigo[7];
        colEnemigos=new Rectangle[7];
        sigEnemigo=0;
        for(int i=0; i<enemigos.length; i++){
            enemigos[i]= new Enemigo(this);
            enemigos[i].prepararEnemigo();
            colEnemigos[i]=new Rectangle(enemigos[i].getPosX(),enemigos[i].getPosY(),enemigos[i].getAncho(),18);
        }
        int x=(int)Math.floor(Math.random()*(289)+129);
        enemigos[0].mostrarEnemigo(x);
    }
    private void crearEnemigos(int sigEnemigo) {
        
        boolean pos=true; //para saber si no está en el pasto
        int x;
        int posArray=sigEnemigo;
        
        if(mapa.getPosY()<-1100){
            x=(int)Math.floor(Math.random()*(289-129)+129);
            enemigos[posArray].mostrarEnemigo(x);
        }else if((mapa.getPosY()>-970 && mapa.getPosY()<-800) || (mapa.getPosY()>-570 && mapa.getPosY()<-380) || (mapa.getPosY()>-134 && mapa.getPosY()<-60)){
            x=(int)Math.floor(Math.random()*(260-169)+169);
            enemigos[posArray].mostrarEnemigo(x);
        }else if(mapa.getPosY()>-770 && mapa.getPosY()<-570){
            if((int)Math.floor(Math.random()*2+1)==1){
                x=(int)Math.floor(Math.random()*(164-129)+129);//ladoIzq
                enemigos[posArray].mostrarEnemigo(x);
            }else{
                x=(int)Math.floor(Math.random()*(289-257)+257);//ladoDer
                enemigos[posArray].mostrarEnemigo(x);
            }  
        }else if(mapa.getPosY()>-380 && mapa.getPosY()<-152){
            if((int)Math.floor(Math.random()*2+1)==1){
                x=129;//ladoIzq
                enemigos[posArray].mostrarEnemigo(x);
            }else{
                x=298;//ladoDer
                enemigos[posArray].mostrarEnemigo(x);
            }  
        }
    }
    
    //------------------------ MAPA ----------------------------
    
    private void cargarColMapa() {
        bordes=new Rectangle[14];
        //Bordes
        bordes[0]=new Rectangle(120, 0, 8, 500);//lado izq
        bordes[1]=new Rectangle(353, 0, 8, 500);//lado der
        //Bloques en medio del mapa
        bordes[2]=new Rectangle(225, 604-1635, 31, 164);//BloqueMedPeque
        bordes[3]=new Rectangle(184, 152-1635, 113, 226);//BloqueMed
        //Entradas
        bordes[4]=new Rectangle(129, 2072-1635, 79, 28);//AbajoIzq
        bordes[5]=new Rectangle(273, 2072-1635, 79, 28);//AbajoDer
        bordes[6]=new Rectangle(129, 986-1635, 79, 95);//MedioIzq
        bordes[7]=new Rectangle(273, 986-1635, 79, 95);//MedioDer
        bordes[8]=new Rectangle(129, 0-1635, 79, 57);//ArribaIzq
        bordes[9]=new Rectangle(273, 0-1635, 79, 57);//ArribaDer
        //Entradas 2 parte
        bordes[10]=new Rectangle(129, 474-1635, 39, 95);//AbajoIzq
        bordes[11]=new Rectangle(129, 794-1635, 39, 191);//ArribaIzq
        bordes[12]=new Rectangle(313, 474-1635, 39, 95);//AbajoDer
        bordes[13]=new Rectangle(313, 794-1635, 39, 191);//ArribaDer
    }
}
