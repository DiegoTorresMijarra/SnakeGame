package api.utils;

import java.awt.Color;
/**
 * es la clase abstracta de la que heredan todas las posiciones, como snake.CabezaS u muros.Obstaculo, aqii declaramos algunos metodos abstractos que seran desarrollados en las subsiguientes clases y declaramos el equals() que overrides el de Object y es usado para comparar posiciones.
 * @author Diego
 *
 */
public abstract class Posicion{
	public int x,y;
	
	
	public abstract Color getColor();
	
	/**
	 * devuelve coordenada x
	 * @return la coordenada
	 */
	public abstract int getX();
	/**
	 * devuelve coordenada y
	 * @return  la coordenada
	 */
	public abstract int getY();
	/**
	 * setea x
	 * @param x el valor a setear
	 */
	public abstract void setX(int x);
	/**
	 * setea y
	 * @param y el valor a setear
	 */
	public abstract void setY(int y);
	
	/**
	 * metodo para comparar objetos que hereden de popsicion, comparara las coordenadas x e y y 
	 * @return true, si x e y son iguales; false si no (o si alguno es nullo). 
	 */
	@Override
	public boolean equals (Object o) {
		if (this.x==((Posicion)o).x)
			if (this.y==((Posicion)o).y)
				return true;
		
		return false;
	}
	
}
