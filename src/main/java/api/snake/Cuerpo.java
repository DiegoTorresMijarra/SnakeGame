package api.snake;

import api.utils.Posicion;

import java.awt.Color;
/**
 * Objeto que referencia a un elemento cuerpo de la snake.Snake, es una posicion con Color especifico
 * @author Diego
 *
 */
public class Cuerpo extends Posicion {
	
	public static Color color=Color.green;
	/**
	 * constructor que pasa los parametros x e y a la posicion super de este objeto
	 * @param x
	 * @param y
	 */
	public Cuerpo(int x, int y) {
		super();
		setX(x);
		setY(y);
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
	 * devuelve el color del cuerpo
	 */
	@Override
	public Color getColor() {
		
		return color;
	}
	
	

}
