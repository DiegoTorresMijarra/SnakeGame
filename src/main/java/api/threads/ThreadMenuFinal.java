package api.threads;

import api.elementosJuego.MenuFinal;

import javax.swing.JFrame;
/**
 * objeto que referencia a un Thread de un Jframe con un menufinal dentro, tenemos una variable statica seguirJugando que nos sirve para determinar si queremos reiniciar el juego( lo hace en el thread del juego) o si queremos volver al menu principal.<br> Este string se deberia transformar a una enumeracion, pero como solo he incluido dos opciones, no es necesario; si se añadieran más es recomendable hacerlo.
 * @author Diego
 *
 */
public class ThreadMenuFinal extends Thread{
	JFrame frame=new JFrame("elementosJuego.MenuFinal");
	MenuFinal menuFinal;
	public static String seguirJugando;
	
	/**
	 * constructor de un thread con un jframe de tamaño fijo; no lo he modificado en ningun momento, pero podria hacerlo. 
	 * @param menu recibe el menufinal propio del juego acabado
	 */
	public ThreadMenuFinal(MenuFinal menu) {
		super();
		this.menuFinal = menu;
		frame.add(menuFinal);
		frame.setVisible(true);
		frame.setSize(300,300);
		frame.setResizable(false);
		menuFinal.setearPanel();
	}
	/**
	 * ejecuta el thread, y lo bloquea hasta que este acaba, ya que esperar respuesta es bloqueante; una vez acabado cierra la ventana.
	 */
	@Override
	public void start(){
		menuFinal.esperarRespuesta();
		frame.dispose();
	}
}
