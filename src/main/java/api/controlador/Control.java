package api.controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Controlador del programa, es un KeyListener, con una direccion (Enumeracion rtipo controlador.Movimiento) que se usara para mover la cabezaS <br>
 * se controla con las teclas asdw; ya que me han dado problemas las teclas up down... además esto facilita que lo puedan usar otros teclados q no incluyan estas teclas. (es un triple xd).
 * @author Diego
 *
 */
public class Control implements KeyListener {
	
	public Movimiento direccion=Movimiento.derecha;
	/**
	 * se ejecuta cuando una tecla es pulsada y soltada a continuacion, incluyo la posibilidad de introducir paradas, pero no se ha implementado al final
	 */
	public void keyTyped(KeyEvent k) {
		char c=k.getKeyChar();
		c=Character.toLowerCase(c);
		if(c=='s') {
			direccion=Movimiento.abajo;
		}
		if(c=='a') {
			direccion=Movimiento.izquierda;
		}
		if(c=='d') {
			direccion=Movimiento.derecha;
		}
		if(c=='w') {
			direccion=Movimiento.arriba;
		}
		//estaba pensado para poder meter una pausa, pero no lo he hecho al final
//		if(c==KeyEvent.VK_SPACE) {
//			System.out.print("ESPACIO");
//			direccion=controlador.Movimiento.parado;
//		}
	
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

}
