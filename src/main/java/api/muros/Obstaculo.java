package api.muros;

import api.dibujo.Cuadrado;
import api.utils.Posicion;

import java.awt.Color;
import java.awt.Graphics2D;
/**
 * Objeto que referencia a la posicion de un obstaculo, tiene un color static propio y un tamano del lado del cuadrado para pintarlo
 * @author Diego
 *
 */
public class Obstaculo extends Posicion {
	
	public static Color color=Color.DARK_GRAY;
	
	static int tamano;
	
	public Obstaculo(int x, int y) {
		super();
		setX(x);
		setY(y);
	}
	/**
	 * devuelve el Color del objeto
	 */
	@Override
	public Color getColor() {
		return color;
	}

	/**
	 * escribe coordenada x
	 * @return
	 */
	public void setX(int nx) {
		super.x=nx;
	}
	
	/**
	 * escribe coordenada y
	 * @return
	 */
	
	public void setY(int ny) {
		super.y=ny;
	}
	
	/**
	 * devuelve x
	 * @return
	 */
	public int getX() {
		return super.x;
	}
	
	/**
	 * devuelve Y
	 * @return
	 */
	public int getY() {
		return super.y;
	}
	/**
	 * pinta el obstaculo usando un motor g y un cuadrado creado en el metodo
	 * @param g
	 */
	public void pintar(Graphics2D g) {
		Cuadrado c=new Cuadrado(tamano,g);
		c.pintar(this);
	}

}
