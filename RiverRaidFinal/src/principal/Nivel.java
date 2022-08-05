package principal;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;
/**
 * Esta clase maneja todos los componentes del juego.
 * @author Jose Lovera 
 * @author Ceballos Fernanda
 * @version 05/08/2022
 */
public class Nivel extends JFrame implements KeyListener,MouseListener{
    //Atributos de la clase
    private String nombre;
    private Timer timer, timer1;
    private Mapa mapa;
    private Enemigo[] enemigos;
    private Rectangle[] bordes;
    private int sigEnemigo, xBala, yBala;
    private Jugador jugador;
    private Bala[] balas;
    private Fuel[] comb;
    private URL myURL;
    private AudioClip disp,exp,perder,motor;
    private ContadorFuel contF;
    private static boolean acelerar;
    private static int puntaje=0, vidas=3;
    private JLabel puntos, vida, vidaC;
    /**
     * Método Constructor de Nivel
     * Este método Contructor configura las propiedades básicas: tamaño,
     * visivilidad, que no va a ser redimensionable. Además se
     * encarga de llamar al método cargarTodo()
     * @param nombre Nombre del usuario en pantalla
     */
    Nivel(String nombre){
        super("River Raid - "+nombre);
        setLayout(null);
        setSize(500,500);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        this.nombre=nombre;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cargarTodo();
    }
    /**
     * Método cargarTodo 
     * En esta parte se añaden los listeners al programa, se
     * crean los objetos de tipo jugador, el medidor de gasolina 
     * que se mostrar en pantalla, el mapa a recorrer... Además 
     * llama a metodos de los otros componentes de pantalla: 
     * enemigos, balas, etc.
     */
    private void cargarTodo() {
        this.addKeyListener(this);
        this.addMouseListener(this);  
        jugador =new Jugador(this);
        contF=new ContadorFuel(this);
        iniciarSonidos();
        texto();
        crearFuel();
        cargarBalas();    
        cargarColMapa();
        cargarEnemigos();
        inicioTimer();
        mapa= new Mapa(this);
    }
    /**
     * Método texto
     * Método para agregar los componentes de texto 
     */
    public void texto()
    {
        puntos=new JLabel(puntaje+"");
        puntos.setBounds(80, 400, 100, 30);
        puntos.setForeground(Color.yellow);
        this.add(puntos);
        vida=new JLabel("Vidas");
        vida.setBounds(350, 400, 100, 30);
        vida.setForeground(Color.yellow);
        this.add(vida);
        vidaC=new JLabel(vidas+"");
        vidaC.setBounds(360, 430, 100, 30);
        vidaC.setForeground(Color.yellow);
        this.add(vidaC);
    }
    /**
     * Método inicioTimer
     * Este método moverá todo los componentes mediante el uso de dos
     * temporizadores, uno para el mapa, enemigos y balas. Y otro para
     * el jugador
     */
    private void inicioTimer() {
        timer=new Timer(20, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                puntos.setText(null);
                puntos.setText(puntaje+"");
                vidaC.setText(null);
                vidaC.setText(vidas+"");
                colision();     
                
                for(int i=0; i<enemigos.length;i++){                        
                   for (Rectangle borde : bordes) {
                        if (enemigos[i].lblEnemigo.getBounds().intersects(borde)) {
                            enemigos[i].cambiarDir();
                        }
                    }
                    enemigos[i].moverEnemigo();

                    if(mapa.getPosY()<-1100&&enemigos[i].getPosY()==80){ //Frecuencia de enemigos
                       crearEnemigos(sigEnemigo);
                    }else if(mapa.getPosY()>-1100&& mapa.getPosY()<-800&&enemigos[i].getPosY()==300){
                        crearEnemigos(sigEnemigo);
                    }else if(mapa.getPosY()>-800&&enemigos[i].getPosY()==80){
                        crearEnemigos(sigEnemigo);
                    }
                    if(enemigos[i].getPosY()>460 || enemigos[i].getPosY()<-19){ //eliminar cuando salen de pantalla
                       sigEnemigo=i;
                     }            
                }
                for (Bala bala : balas) {
                    if (bala.y > -11) {
                        bala.mover();
                    }
                }
                
                if(!(mapa.getPosFin()==0)){
                    if(acelerar==false){
                        moverTodoY();
                    }else{
                        moverTodoY();
                        moverTodoY();
                    }                  
                }else{
                    if((mapa.getPosY()-25)==jugador.y){
                        reiniciar();
                    }
                } 
                acelerar=false;
            }
        });
        timer.start();
        timer1=new Timer(1, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jugador.mostrar();
                jugador.mover();
            }
        });
        timer1.start();
    }
    
    //------------------------- ENEMIGOS ------------------
    /**
     * En este método se cargan los enemigos del juego, incluido la posición 
     * aleatoria en pantalla se calcula aquí.
     */
    private void cargarEnemigos(){
        enemigos=new Enemigo[7];
        sigEnemigo=0;
        for(int i=0; i<enemigos.length; i++){
            enemigos[i]= new Enemigo(this);
            enemigos[i].prepararEnemigo();
        }
        int x=(int)Math.floor(Math.random()*(285-129)+129);
        enemigos[0].mostrarEnemigo(x);
    }
    /**
     * Método crearEnemigos
     * Muestra los enemigos por pantalla
     * @param sigEnemigo Indica la posicion de un enemigo fuera de pantalla para posicionarlo
     */
    private void crearEnemigos(int sigEnemigo) {
        
        boolean pos=true; //para saber si no está en el pasto
        int x;
        int posArray=sigEnemigo;
        
        if(mapa.getPosY()<-1100){
            x=(int)Math.floor(Math.random()*(285-129)+129);
            enemigos[posArray].mostrarEnemigo(x);
        }else if((mapa.getPosY()>-970 && mapa.getPosY()<-800) || (mapa.getPosY()>-570 && mapa.getPosY()<-380) || (mapa.getPosY()>-134 && mapa.getPosY()<-60)){
            x=(int)Math.floor(Math.random()*(250-170)+170);
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
    /**
     * Método cargarColMapa
     * En este método se cargan los coliders del mapa
    */
    private void cargarColMapa() {
        bordes=new Rectangle[14];
        //Bordes
        bordes[0]=new Rectangle(120, 0, 8, 500);//lado izq
        bordes[1]=new Rectangle(354, 0, 50, 500);//lado der
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
    //--------------------FUEL-----------------
    /**
     * Método crearFuel
     * En este método se crear los objetos Fuel(combustible) que aparecerán en pantalla
     */
    public void crearFuel(){
        comb=new Fuel[8];
        comb[0]=new Fuel(this,294,230);
        comb[1]=new Fuel(this,176,-506);
        comb[2]=new Fuel(this,297,-780);
        comb[3]=new Fuel(this,200,-950);
        comb[4]=new Fuel(this,250,-1150);
        comb[5]=new Fuel(this,180,-1500);
        comb[6]=new Fuel(this,247,-1618);
        comb[7]=new Fuel(this,311,-2002);
    }
    //--------------------BALAS-----------------
    /**
     * Método cargarBalas
     * 
     * En este método se cargan los objetos balas
     */
    public void cargarBalas(){
        balas=new Bala[20];
        for(int i=0; i<balas.length; i++){
            balas[i]= new Bala(this);
        }
    }
    /**
     * Método dispararBalas
     * @param x Posición en x para disparar
     * @param y Posición en y para disparar
     */
    public void dispararBalas(int x, int y){
        xBala=x;
        yBala=y;
        boolean disparo=false;
        int i=0;
        do{
            if(balas[i].y<-10){
                balas[i].mostrar(xBala, yBala);
                disparo=true;
            }    
            i++;    
        }while(disparo==false);
    }
    
    //--------------------FUNCIONES GENERALES-----------------
    /**
     * Método reiniciar
     * LLama a los métodos de "reinicio" de cada objeto, con el fin de quitarlos
     * y volver a cagar todo.
     */
   public void reiniciar(){
        mapa.quitarMapa();
        jugador.quitarJugador();
        contF.quitarContadorFuel();
        puntos.setLocation(600, 600);
        vida.setLocation(600, 600);
        vidaC.setLocation(600, 600);
        for (Fuel comb1 : comb) {
            comb1.quitarFuel();
        }
        for (Bala bala : balas) {
            bala.quitarBala();
        }
        for (Enemigo enemigo : enemigos) {
            enemigo.quitarEnemigo(600, 600);
        }
        cargarTodo();
   }
   /**
    * Método morir
    * Método que evalua las condiciones para morir y detiene el timer de cumplirse
    * la condición, que la vida sean igual a 0. Si es mayor aun no muere,por lo 
    * que la condición cumple la función de parar el motor del juego y reiniciarlo.
    */
    public void morir(String mensaje){
        JOptionPane.showMessageDialog(null, this.nombre+" "+mensaje);
        if(vidas>1){
            motor.stop();
            perder.play();
            vidas--;
            timer.stop();
            timer1.stop();
            reiniciar();
        }else{
            JOptionPane.showMessageDialog(null, this.nombre+" Has perdido");
            timer.stop();
            timer1.stop();
            System.exit(0);
        }
    }
    /**
     * Método Colisión
     * Este método busca las posibles colisiones entre los componentes del juego;
     * entre jugador y orilla del río, entre balas u fuel, etc. y dependiendo de 
     * lo que se genere llama a otros metodos como morir.
     */
    public void colision(){
        for(int i=0;i<this.bordes.length;i++)
        {
            if(this.jugador.lblJugador.getBounds().intersects(bordes[i]))
            {
                morir("Cuidado con la orilla");
                puntaje-=50;
            }
            for(int j=0; j<this.balas.length; j++){
                if(this.bordes[i].intersects(this.balas[j].lblBala.getBounds())){
                    this.balas[j].mostrar(-500, -500);
                }
            }
        }
        for(int i=0;i<this.enemigos.length;i++)
        {
            for(int j=0; j<this.balas.length; j++){
                if(this.enemigos[i].lblEnemigo.getBounds().intersects(this.balas[j].lblBala.getBounds())){
                    exp.play();
                    this.enemigos[i].quitarEnemigo(0, -500);
                    this.balas[j].mostrar(-500, -500);
                    puntaje+=50;
                }
            }
            if(this.jugador.lblJugador.getBounds().intersects(this.enemigos[i].lblEnemigo.getBounds()))
            {
                morir("No choques con los enemigos");
                puntaje-=50;
            }    
        }
        for(int i=0;i<this.comb.length;i++)
        {
            if(this.jugador.lblJugador.getBounds().intersects(this.comb[i].lblFuel.getBounds())&&this.comb[i].recargable){
                contF.recargarFuel();
                comb[i].quitarFuel();
            }else
                contF.actualizarFuel();
            for(int j=0; j<this.balas.length; j++){
                if(this.comb[i].lblFuel.getBounds().intersects(this.balas[j].lblBala.getBounds())){
                    comb[i].quitarFuel();
                    puntaje-=5;
                }
            }
        }
        if(contF.lblFuelBar.getX()==contF.lblMedidor.getX()){
            morir("No te olvides de la gasolina");
        }
    }
    /**
     * Método moverTodoY
     * Método que mueve componenetes en Y.
     */
    public void moverTodoY(){
        for(int i=2; i<bordes.length;i++){
            bordes[i].setBounds((int) bordes[i].getX(), (int) (bordes[i].getY()+1), (int) bordes[i].getWidth(), (int) bordes[i].getHeight());
        }
        for (Fuel comb1 : comb) {
            comb1.mover();
            comb1.mostrar();
        }
        for(Enemigo en:enemigos){
            en.moverEnemigoY();
        }
        mapa.moverMapa();
    }
    /**
     * Método iniciarSonidos
     * Inicializa los componentes de sonido del juego.
     */
    private void iniciarSonidos(){
        myURL = this.getClass().getResource("../sonidos/motor.wav");
        motor = Applet.newAudioClip(myURL);
        motor.loop();
        myURL = this.getClass().getResource("../sonidos/disparo.wav");
        disp = Applet.newAudioClip(myURL);
        myURL = this.getClass().getResource("../sonidos/explosion.wav");
        exp = Applet.newAudioClip(myURL);
        myURL = this.getClass().getResource("../sonidos/apagar.wav");
        perder = Applet.newAudioClip(myURL);
    }
    /**
     * Esta función evalua lo que presiona el usuario y ayuda a manejar el
     * objeto de la clase Jugador, esto mediante un acceso al atributo "X" 
     * donde se sumará si se dirije a la derecha y se restará si se dirije 
     * a la izquierda. Además estblce los valores de dirección del objeto 
     * jugador.
     * @param e Contiene los eventos de presionar teclas.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getExtendedKeyCode()==KeyEvent.VK_UP||e.getKeyCode()==KeyEvent.VK_W)
        {
            this.jugador.direccion=0;
            acelerar=true;
        }    
        if(e.getExtendedKeyCode()==KeyEvent.VK_DOWN||e.getKeyCode()==KeyEvent.VK_S)
        {
            this.jugador.direccion=0;
            acelerar=false;
        }    
        if(e.getExtendedKeyCode()==KeyEvent.VK_LEFT||e.getKeyCode()==KeyEvent.VK_A)
        {
            this.jugador.direccion=-1;
            this.jugador.x--;
        }   
        if(e.getExtendedKeyCode()==KeyEvent.VK_RIGHT||e.getKeyCode()==KeyEvent.VK_D)
        {
            this.jugador.direccion=1;
            this.jugador.x++;
        }  
        if(e.getExtendedKeyCode()==KeyEvent.VK_SPACE)
        {
            disp.play();
            dispararBalas(this.jugador.x+9, this.jugador.y);
        }
    
    }
    /**
     * Evento del mouse para cuando se presiona una tecla.
     * @param e Contiene el evento de presionar mouse
     */
    @Override
    public void mousePressed(MouseEvent e) {
        disp.play();
        dispararBalas(this.jugador.x, this.jugador.y);
    }
    /**
     * Evento del mouse para cuando se presiona y suelta una tecla.
     * @param e Contiene el evento de presionar mouse 
     */
    @Override
    public void keyTyped(KeyEvent e) {}
    /**
     * Evento del mouse para cuando suelta una tecla.
     * * @param e Contiene el evento de presionar mouse
     */
    @Override
    public void keyReleased(KeyEvent e) {}
    /**
     * Evento que se usa para el momento en que el usuario presiona y suelta el objeto.
     * @param e Objeto de los eventos de mouse.
     */
    @Override
    public void mouseClicked(MouseEvent e) {}
    /**
     * Evento que se usa para el momento en que el usuario suelta el mouse.
     * @param e Objeto de los eventos de mouse.
     */
    @Override
    public void mouseReleased(MouseEvent e) {}
    /**
     * Evento para indicar que el puntero del mouse entro al area del objeto.
     * @param e 
     */
    @Override
    public void mouseEntered(MouseEvent e) {}
    /**
     * Evento para indicar que el puntero del mouse salio del area del objeto.
     * @param e 
     */
    @Override
    public void mouseExited(MouseEvent e) {}
}
