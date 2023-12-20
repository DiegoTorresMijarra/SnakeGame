package api.dibujo;

import api.utils.Posicion;

import java.awt.Color;
import java.awt.Graphics2D;
/**
 *  Objeto, que sirve para pintar cuadrados en una posicion (pasarsela al metodo pintar).<br>
 * Para pintar cuadrados, es necesario dos parametros, pero nos interesa que sean cuadrados a si q solo pasaremos uno<br>
 * incluye un Graphics2D que es el motor para pintar el cuadrado
 * @author Diego
 *
 */
public class Cuadrado {
	int lado;
	Graphics2D motorG;
	/**
	 * constructor que devuelve un objeto con lados iguales a l y un motor grafico r
	 * @param l lado del cuadrado
	 * @param g motor grafico a usar
	 */
	public Cuadrado(int l, Graphics2D g) {
		this.lado=l;
		this.motorG=g;
	}
	/**
	 * Accion de pintar el cuadrado en la posicion p
	 * @param p utils.Posicion con las coordenadas x e y donde pintar el circulo.
	 */
	public void pintar(Posicion p) {
		motorG.setColor(p.getColor());
		motorG.fillRect(((Posicion)p).getX(),((Posicion)p).getY(), lado, lado);
	}
	/**
	 * Accion de pintar el cuadrado en la posicion p, con un color especifico
	 * @param p utils.Posicion con las coordenadas x e y donde pintar el circulo.
	 * @param c Color del dibujo.Cuadrado
	 */
	public void pintar(Posicion p, Color c) {
		motorG.setColor(c);
		motorG.fillRect(((Posicion)p).getX(),((Posicion)p).getY(), lado, lado);
	}

}
