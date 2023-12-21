package api.snake;

import api.elementosJuego.JuegoSnake;
import api.utils.Posicion;

import java.awt.Color;
import java.util.ArrayList;
/**
 * Objeto que referencia a la cabeza de la snake.Snake, es una posicion con Color, velocidades con respecto a los ejes x e y (xv,xy), modulo de la velocidad, y distanciaPuntos que especifica el tamaño del objeto y la separacion con otros. <br>
 * <u>NOTA</u>: velocidades de la coordenada x e y, solo podra ser +-1 o 0, lo usaremos para multiplicar no para determinar cuanto avanza. Para variarla, setear el modulo.
 * @author Diego
 * 
 *
 */
public class CabezaS extends Posicion {
	public int vx,vy; 
	
	public static Color color=Color.black;
	static int moduloV=1;
	
	static int distanciaPuntos;
	/**
	 * constructor en el que pasas cada parametro de la snake.CabezaS, salvo los static
	 * @param x coordenada en x
	 * @param y coordenada en y
	 * @param vx velocidad inicial eje x
	 * @param vy velocidad inicial eje y
	 */
	public CabezaS(int x, int y, int vx, int vy) {
		super();
		setX(x);
		setY(y);
		this.setVx(vx);
		this.setVy(vy);
		
	}
	/**
	 * constructor en el que pasas la posicion inicial del objeto, setea la velocidad inicial a la derecha.
	 * @param x coordenada en x
	 * @param y coordenada en y

	 */
	public CabezaS(int x, int y) {
		super();
		setX(x);
		setY(y);
		this.moverDer(); //por defecto se mueve a la der
		
	}
	
	@Override
	public Color getColor() {
		return color;
	}
	
	
	
	/**
	 * devuelve vy
	 * @return
	 */
	public int getVy() {
		return vy;
	}
	
	/**
	 * escribe vy
	 * @return
	 */
	public void setVy(int vy) {
		this.vy = vy;
	}
	
	/**
	 * devuelve vx
	 * @return
	 */
	public int getVx() {
		return vx;
	}
	
	/**
	 * escribe vx
	 * @return
	 */
	public void setVx(int vx) {
		this.vx = vx;
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
	 * variacion de la velocidad, la setea hacia Arriba
	 */
	public void moverA() {
		this.setVx(0);
		this.setVy(-moduloV);
	}
	
	/**
	 * variacion de la velocidad, la setea hacia Abajo
	 */
	public void moverAb() {
		this.setVx(0);
		this.setVy(moduloV);
	}
	
	/**
	 * variacion de la velocidad, la setea hacia Arriba
	 */
	public void moverDer() {
		this.setVx(moduloV);
		this.setVy(0);
	}
	
	/**
	 * variacion de la velocidad, la setea hacia la izquierda
	 */
	public void moverIzq() {
		this.setVx(-moduloV);
		this.setVy(0);
	}
	/**
	 * variacion de la velocidad, la setea a 0,0, no la he usado en el programa, pero me parece relevante mantenerla
	 */
	public void moverParado() {
		this.setVx(0);
		this.setVy(0);
	}
	
	/**
	 * avanza la cabeza en funcion de la velocidad que tiene
	 */
	public void avanza() {
		//es un movimiento lineal simple xd
		
		int nx=this.getX()+(distanciaPuntos*this.getVx());
		int ny=this.getY()+(distanciaPuntos*this.getVy());
		
		this.setX(nx);
		
		this.setY(ny);
		
	}
	/**
	 * boolean que devuelve si la cabeza toca los limites del tablero de juego
	 * @param ancho pantalla
	 * @param alto pantalla
	 * @param distancia interna del juego
	 * @return true si toca, false si no lo hace
	 */
	public boolean tocaLado(int ancho, int alto, int distancia) {
		
		if (this.x>=ancho-distancia||this.x<0)
			return true;
		if (this.y>=alto-distancia* JuegoSnake.salvaMarcador||this.y<0)
			return true;
		return false;
	}
	
	/**
	 * metodo que calcula y devuelve las posiciones adyacentes a una cabeza de serpiente, incluida esta
	 * @return un ArrayList de Posiciones q contiene lo anterior dicho
	 */
	public ArrayList<Posicion> calculoHitBox() {
		int x=this.getX()-distanciaPuntos;
		int y=this.getY()-distanciaPuntos;
		ArrayList<Posicion> hb=new ArrayList<Posicion>();
		for(int i=0;i<3;i++)
			for(int e=0;e<3;e++) {
				hb.add((Posicion)new Cuerpo(x+distanciaPuntos*i,y+distanciaPuntos*e));
			}
				
		return hb;
	}
	
	
}
