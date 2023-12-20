package api;

import api.elementosJuego.ConstructorJuego;
import api.threads.MenuPrincipal;
import api.threads.ThreadJuegoSnake;

/**
 * Metodo main del programa
 * @author Diego
 *
 */
public class Main {
	public static boolean jugamos=false;
	/**
	 * ejecuta una vez el programa <br>
	 * los sleep son necesarios para que no crashee el juego, pero se puede variar el tiempo, para acelerar el procesamiento de la aplicacion
	 */
	public void ejecutar() {
		MenuPrincipal juego=new MenuPrincipal();
		Thread tJuego=new Thread(juego);
		tJuego.start();
		try {
			do {
				if(jugamos) {
					jugamos=false;
					final ConstructorJuego constructor=new ConstructorJuego(juego.escena.esce, juego.muros,
							juego.velocidadR, juego.ventanaG, juego.getNombre());
					ThreadJuegoSnake t=new ThreadJuegoSnake(constructor);
					t.setDaemon(false);
					t.start();
					t.join();
					juego.menuVisible();
					t.disposeThread();
				}
				Thread.sleep(100);
			}while(true);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * metodo main de la aplicacion
	 * @param args
	 */
	public static void main(String[] args) {
		Main app=new Main();
		app.ejecutar();
	}

}
