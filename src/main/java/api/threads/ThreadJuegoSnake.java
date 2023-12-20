package api.threads;

import api.elementosJuego.ConstructorJuego;
import api.elementosJuego.Contenedor;
import api.elementosJuego.JuegoSnake;

/**
 * clase que referencia a un Thread que ejecuta el juegoSnake
 * @author Diego
 *
 */
public class ThreadJuegoSnake extends Thread{
	
	final ConstructorJuego constructor;
	JuegoSnake game;
	Contenedor contenedor;;
	/**
	 * construye un objeto a raiz de un constructorJuego
	 * @param constructor
	 */
	public ThreadJuegoSnake(final ConstructorJuego constructor) {
		super();
		this.constructor = constructor;
		this.game =new JuegoSnake(this.constructor);
		
	}
	/**
	 * inicia el Juego, la primera vez que llame a jugarONoJugar() recibira threads.ThreadMenuFinal.seguirJugando="si", como si el juego ya se hubiera jugado, pero no influye en el desarrollo, facilita la primera llamada
	 */
	public void start(){
		ThreadMenuFinal.seguirJugando="si";
		jugarONoJugar();
	}
	/**
	 * metodo que bloquea llamara a volver a jugar cuando se setee threads.ThreadMenuFinal.seguirJugando a si, y una vez hecho esto lo pondra a null, esperando de nuevo a recibir un valor distinto de null (lo setean los botones del menu final) para finalizar este bucle.
	 */
	public void jugarONoJugar() {
		do {
			if (ThreadMenuFinal.seguirJugando!=null) {
				if (ThreadMenuFinal.seguirJugando.equalsIgnoreCase("si")) {
					ThreadMenuFinal.seguirJugando=null;
					volverAJugar();
				}
			}
		}while(!ThreadMenuFinal.seguirJugando.equalsIgnoreCase("no"));
	}
	/**
	 * ejecuta una vez el juego, y luego ejecuta el Thread del menu final.<br>
	 * primero destruye el contenedor si este es distinto a nullo y luego crea otro nuevo, de tal forma que no se acumulen en el mismo Jframe distintos juegos una vez se acaben estos.<br>
	 * NOTA: bloquea el Thread, ya que game.jugar() es bloqueante.
	 */
	public void volverAJugar() {
		if(contenedor!=null)
			contenedor.dispose();
		contenedor = new Contenedor("snake.Snake.DTM");
		this.constructor.setVelocidad();
		this.game=new JuegoSnake(this.constructor);
		contenedor.addKeyListener(game.mando);
		contenedor.add(game);
		contenedor.setVisible(true);
		game.jugar();
		
		ejecutarMenuFinal();
	}
	
	/**
	 * destruye el contenido del contenedor, he creado un metodo, para que se pueda llamar desde fuera, cuando lo llamamos desde otros metodos, asi evitamos el tener que acceder al objeto etc.
	 * 
	 */
	public void disposeThread() {
		contenedor.dispose();
	}
	/**
	 * ejecuta el threads.ThreadMenuFinal a partir del metodo pantallaFinal de nuestro game. este metodo bloquea el thread, ya que el start siguiente tb bloquea el thread.
	 */
	public void ejecutarMenuFinal() {
		Thread threadMF=new ThreadMenuFinal(game.pantallaFinal());
		threadMF.setDaemon(false);
		threadMF.start();
	}
}
