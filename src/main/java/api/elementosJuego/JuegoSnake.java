package api.elementosJuego;

import api.comida.Comida;
import api.dibujo.Cuadrado;
import api.muros.Muro;
import api.records.Record;
import api.snake.Snake;
import api.utils.Posicion;
import api.controlador.Control;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Objeto que referencia un elementosJuego.JuegoSnake. este contiene entre otros elementos: la serpiente, la comida( solo es una, pero podira implementarse el que genere más, las posiciones ocupadas del tablero, y las label del crono y la puntuacion. <br>
 * de las cosas más importantes de esta clase es trabajar este con las posiciones que pueden ser ocupadas. estas las calcularemos con la variable static speed.<br>
 * El tablero va a ser cuadrado siempre para agilizar todo,
 * @author Diego
 *
 */
@SuppressWarnings("serial")
public class JuegoSnake extends JPanel{
	
	static Color colorFondo;
	static boolean bMuros;
	
	Snake serpiente;
	
	Comida comida=new Comida(0,0);
	
	ArrayList<Muro> muros=new ArrayList<Muro>();
	
	ArrayList<Posicion> ocupado=new ArrayList<Posicion>();
	
	public Control mando=new Control();
	//label
	JLabel marcador;
	JLabel crono;
	
	//no hace falta q sean static ?
	static double t0;//tiempo inicial cuando se inicia la app
	static double tiempo; //tiempo actual
	double tJuego;
	
	//marcadores
	String jugador;
	int puntuacion=0;
	int contadorVueltas=0;
	public static int salvaMarcador=4;
	//ancho contenedor
	static int ancho;
	
	static long velocidadJuego=300;//le pongo un valor por si lo llamo sin constructor, pero el constructor la setea
	
	//speed
	final static int speed=30;
	
	/**
	 * constructor de juego dado una serpiente y una comida, no se suele usar.
	 * @param serpiente
	 * @param comida
	 */
	public JuegoSnake(Snake serpiente, Comida comida) {
		super();
		super.setSize(ancho,ancho);
		t0=System.currentTimeMillis();
		
		super.setBackground(colorFondo);
		
		JuegoSnake.tiempo=System.currentTimeMillis();
		this.serpiente = serpiente;
		this.comida = comida;
		
		this.anadirMuro();
	}
	/**
	 * consstructor que genera un juego base en funcion de solo un parametro, del constructor: el nombre. ya que el resto de valores los setea el constructor porque son static. Entonces para que pasarle todo? para evitar que eclipse diga que el constructor no es usado :). 
	 * @param constructor
	 */
	public JuegoSnake(ConstructorJuego constructor) {
		super();
		t0=System.currentTimeMillis();
		
		jugador=constructor.nombreJugador;
		
		super.setBackground(colorFondo);
		
		JuegoSnake.tiempo=System.currentTimeMillis();
		
		this.serpiente = new Snake(speed);
		
		Comida.tamano=speed-10;
		
		this.comida=new Comida(speed-10);
		this.moverCom(); //lo pone en el 0,0 porq cuando inicio la primera vez el ancho de la pantalla es 0
		
		this.anadirMarcadorCrono();
		
		this.anadirMuro();
	}
	
	/**
	 * metodo pintar del juego, la serpiente, la comida , los muros...
	 * es importante que sea overrides ya que el programa usa repaint() que llama a este metodo lo antes que se pueda.<br>
	 * 
	 * NOTA: la parte comentada es para pruebas. pinta la zona de las hitbox del juego.
	 */
	@Override
	public void paint(Graphics g) {
		
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
		
		this.serpiente.pintarSnake(g2d);	
		this.comida.pintar(g2d);
		for(int i=0;i<muros.size();i++)
			this.muros.get(i).pintarMuro(g2d);
		
		this.setBorder(BorderFactory.createLineBorder(Color.black)); 
		this.printBorder(g2d);
		
//		this.pintarHB(g2d);
	}
	
	/**
	 * Pinta la hitbox de la serpiente, para pruebas
	 * 
	 * @param g2d
	 */
	void pintarHB(Graphics2D g2d) {
		Cuadrado c=new Cuadrado(speed-5, g2d);
		for(int i=0;i<ocupado.size();i++)
			c.pintar(ocupado.get(i), Color.blue);
	}
	
	
	/**
	 * mira si la serpiente come, y si lo hace mueve la posicion de esta y hace crecer el cuerpo, además llama a aumentar la puntuacion
	 * 
	 */
	public void comer() {
		if(((Posicion)this.serpiente.cabeza).equals(this.comida)) {
			this.serpiente.anadirCuerpo();
			this.moverCom();
			this.aumentarPuntuacion();
		}
	}
	/**
	 * aumenta la puntuacion del juego en +5 
	 */
	public void aumentarPuntuacion() {
		this.puntuacion+=5;
		this.marcador.setText("  Marcador: "+puntuacion);
	}
	
	/**
	 * boolean que determina si se ha acabado el juego, para ello mira si la cabeza es igual a una zona ocupada(la actualiza primero), y luego si la cabeza toca algun limite del juego.
	 * @return true si el juego ha acabado, false si sigue
	 */
	public boolean finDelJuego() {
		this.actualizarZonaOcupada();
		if(ocupado.contains(this.serpiente.cabeza)||this.serpiente.cabeza.tocaLado(Contenedor.tamano, Contenedor.tamano, speed))
			return true;
		
		else return false;
	}
	
	/**
	 * mueve la comida a una posicion aleatoria de entre las posibles, para ello calcula casillas.
	 */
	void moverCom() {
		int casillas=ancho/speed;
		
		comida.setX((int)(Math.random()*casillas)*speed);
		comida.setY((int)(Math.random()*(casillas-salvaMarcador))*speed); //salvamos el marco
	}
	
	/**
	 * reasigna la zona ocupada del tablero, esta esta formada por la serpiente (sin la cabeza), los muros y la comida. no crashea al coincidir la cabeza con la comida, por el orden de llamada del metodi jugar.<br> 
	 * tal vez deberia asignar el borde del tablero, no se todavia; no lo he implementado.
	 */
	void actualizarZonaOcupada() {
		ArrayList<Posicion> aux=new ArrayList<Posicion>();
		//añade la comida
		aux.add((Posicion)comida);
		//añade  la serpiente sin la cabeza para metodo fin
		aux.addAll(this.serpiente.zonaOcu(false));
		//añade muros
		for(int i=0;i<muros.size();i++)
			aux.addAll(muros.get(i).ladrillos);
		
		//reasignamos
		this.ocupado=aux;
	}
	/**
	 * añade un muro nuevo si el boolean de los muros es true o uno del muros.Muro() (muro infinito q no se ve en la pantalla) si este falso
	 */
	void anadirMuro() {
		if (bMuros) {
			this.ocupado.add((Posicion)this.serpiente.cabeza);
			this.muros.add(new Muro(ocupado));
		}
		else
			this.muros.add(new Muro());
		
	}
	/**
	 * actualiza los muros, es decir, comprueba si se ha pasado el tiempo de cada uno y si es asi lo anota en una arraylist de integer, y luego borra todos los muros de las posiciones de este array. aunq el programa solo genere un muro, esta pensado para poder borrar distintos muros. 
	 */
	void actualizarMuros() {
		boolean b=false;
		ArrayList<Integer> borrar=new ArrayList<Integer>();
		for(int i=0;i<muros.size();i++) {
			if(muros.get(i).tiempo<tiempo) {
				borrar.add(i);
				b=true;
			}
		}
		for(int i=0;i<borrar.size();i++)
			muros.remove(borrar.get(i).intValue());
		
		if(b)
			anadirMuro();
	}
	/**
	 * actualiza el tiempo de juego y lo pinta en el reloj del juego
	 */
	void actualizarTiempo() {
		tiempo=System.currentTimeMillis();
		tJuego=tiempo-t0;
		this.crono.setText("  Tiempo: "+((int)tJuego/1000)+" s");
	}
	
	/**
	 * aumenta la velocidad de ejecucion del juego, para ello reduce el parametro elementosJuego.JuegoSnake.velocidadJuego, que se pasara al thread sleep. a menor valor, mas rapido se ejecuta el juego. he limitado este valor para que sea representativo en tiempo humano y evitar que sea negativo. además, la funcion que va reduciendo este valor lo hace de tal forma que sea notorio al principio del juego, pero que sea menor al final de este. estos parametros se podrian variar para mejorar la exp de juego.<br>
	 * NOTA: el juego se actualiza segun las ejecuciones de este, no por tiempo. esto se hace para poder habilitar en algun momento opciones como pausa etc. que podrian interferir con el tiempo de juego.
	 */
	void aumentarV() {
		this.contadorVueltas++;
		this.actualizarTiempo();
		if(JuegoSnake.velocidadJuego>100) {
			if(this.contadorVueltas%5==0) {
				JuegoSnake.velocidadJuego=JuegoSnake.velocidadJuego-(3+100/this.contadorVueltas);
//				System.out.println("V Actual "+elementosJuego.JuegoSnake.velocidadJuego);
			}
		}
	}
	/**
	 * ejecuta el juego, es un bucle bloqueante(no se acabara hasta q lo haga el juego), que ejecuta uno a uno los metodos necesarios del juego. una vez realizado esto, pausa la ejecucion del juego el tiempo que le toque. 
	 */
	public void jugar() {
		boolean b=false;
		while(!b) {
				this.aumentarV();
				this.serpiente.cambiarD(this.mando.direccion);
				this.serpiente.moverS();
				this.comer();
				b=this.finDelJuego();
				this.actualizarMuros();
				this.repaint();
				
				try {
					Thread.sleep(JuegoSnake.velocidadJuego);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
	}
	/**
	 * añade un marcador y el reloj, con los parametros apropiados para estos. 
	 */
	void anadirMarcadorCrono(){
		Font letraChula=new Font(Font.SANS_SERIF, Font.BOLD+Font.ITALIC+Font.PLAIN, 25);
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weighty = 1.0;   //request any extra vertical space	
		c.weightx=1.0;
		c.anchor = GridBagConstraints.LAST_LINE_START; //bottom of space
		c.fill=GridBagConstraints.HORIZONTAL;
		c.ipady=15;
		
		
		marcador=new JLabel("  Marcador: 0");
		marcador.setFont(letraChula);
		marcador.setOpaque(true);
		marcador.setBackground(Color.white);
		marcador.setBorder(BorderFactory.createLineBorder(Color.black,3));
		this.add(marcador,c);
		
		crono=new JLabel("  Tiempo: ");
		crono.setFont(letraChula);
		crono.setOpaque(true);
		crono.setBackground(Color.white);
		crono.setBorder(BorderFactory.createLineBorder(Color.black,3));
		this.add(crono,c);
	}
	/**
	 * reinicia los marcadores, del juego, no es usado por el programa pero puede estar bien hacerlo.
	 */
	public void reiniciarMarcadores() {
		this.marcador.setText("  Marcador: 0");
		this.crono.setText("  Tiempo: ");
	}
	
	public MenuFinal pantallaFinal(){
		return new MenuFinal(new Record(this.jugador,this.puntuacion));
	}
}
