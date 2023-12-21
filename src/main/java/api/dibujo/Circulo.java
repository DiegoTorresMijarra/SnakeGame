package api.dibujo;

import api.utils.Posicion;

import java.awt.Graphics2D;
/**
 * Objeto, que sirve para pintar circulos en una posicion (pasarsela al metodo pintar).<br>
 * Para pintar circulos, es necesario dos parametros, por eso se expecifican dos radios, por si se quieren incluir variaciones de tamaño por estetica.<br>
 * incluye un Graphics2D que es el motor para pintar el circulo
 * @author Diego
 *
 */
public class Circulo{
	int radioY;
	int radioX;
	Graphics2D motorG;
	
	/**
	 * constructor que devuelve un objeto con ambos radios iguales a r y un motor grafico r
	 * @param r radio del circulo
	 * @param g motor grafico a usar
	 */
	public Circulo(int r, Graphics2D g) {
		this.radioX=r+2;
		this.radioY=r+2; 
		this.motorG=g;
	}
	/**
	 * Accion de pintar el circulo en la posicion p
	 * @param p coordenadas x e y donde pintar el circulo.
	 */
	public void pintar(Posicion p) {
		motorG.setColor(p.getColor());
		motorG.fillOval(((Posicion)p).getX(),((Posicion)p).getY(), radioX, radioY);
	}

}
