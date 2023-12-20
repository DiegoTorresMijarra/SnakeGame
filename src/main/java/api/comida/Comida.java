package api.comida;

import api.dibujo.Circulo;
import api.snake.Snake;
import api.utils.Posicion;

import java.awt.Color;
import java.awt.Graphics2D;
/**
 * clase de posicion que referencia a una comida, tiene un tamaño y un color.
 * @author Diego
 *
 */
public class Comida  extends Posicion {
	public static Color color=Color.red;
	public static int tamano;
	/**
	 * constructor que determina las coordenadas de la posicion, 
	 * @param x coordenada en x
	 * @param y coordenadas en y
	 */
	public Comida(int x, int y) {
		super();
		setX(x);
		setY(y);
	}
	/**
	 * constructor que genera una comida en las coordenadas (0,0) con un tamano dado
	 * @param tamano de la comida
	 */
	public Comida(int tamano) {
		super();
		setX(0);
		setY(0);
		Comida.tamano=tamano;
	}
	
	/**
	 * escribe coordenada x
	 * @return
	 */
	@Override
	public void setX(int nx) {
		super.x=nx;
	}
	
	/**
	 * escribe coordenada y
	 * @return
	 */
	@Override
	public void setY(int ny) {
		super.y=ny;
	}
	
	/**
	 * devuelve x
	 * @return
	 */
	@Override
	public int getX() {
		return super.x;
	}
	
	/**
	 * devuelve Y
	 * @return
	 */
	@Override
	public int getY() {
		return super.y;
	}
	/**
	 * devuelve el Color de la comida
	 */
	@Override
	public Color getColor() {
		return color;
	}
	/**
	 * Pinta la comida. <br>
	 * crea otra comida por ajustes esteticos, pero no es muy inteligente, porq creo uno por cada vez q lo ejecuto, tal vez sea mejor cambiarlo en circulo, no se
	 * @param g motor q pinta la comida
	 */
	public void pintar(Graphics2D g) {
		Comida pintada=new Comida(this.x,this.y);
		int dif=(Snake.tamano-Comida.tamano)/2;
		pintada.x+=dif;
		pintada.y+=dif;
		Circulo c=new Circulo(tamano,g);
		c.pintar(pintada);
	}

}
