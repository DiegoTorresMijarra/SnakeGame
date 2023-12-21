package api.snake;

import api.dibujo.Circulo;
import api.controlador.Movimiento;
import api.utils.Posicion;


import java.awt.Graphics2D;
import java.util.ArrayList;
/**
 * clase que contiene una serpiente, es decir una cabeza y su cuerpo. el cuerpo esta contenido en un ArrayList, pero se podrian usar otro tipo de colecciones. He usado esta porque es la que mas controlo.
 * @author Diego
 *
 */
public class Snake {
	public CabezaS cabeza;
	public ArrayList<Cuerpo> cuerpo;
	
	static int velocidad;
	
	public static int tamano;
	
	/**
	 * constructor de una serpiente dado los valores:
	 * @param cabeza snake.CabezaS del snake.Snake
	 * @param c cuerpo[] del snake.Snake
	 * @param distancia entre objetos
	 */
	public Snake(CabezaS cabeza, Cuerpo[] c, int distancia) {
		this.cabeza = cabeza;
		this.cuerpo=new ArrayList<Cuerpo>(c.length);
		for(int i=0; i<c.length;i++) {
			cuerpo.add(c[i]);
		}
		Snake.velocidad=distancia;
		Snake.tamano=distancia;
		CabezaS.distanciaPuntos=Snake.velocidad;
	}
	/**
	 * constructor de una serpiente en funcion de una posicion posible del juego, por eso usamos la variable distancia
	 * @param distancia
	 */
	public Snake(int distancia) {
		Cuerpo p=new Cuerpo(distancia,distancia);
		Cuerpo p1=new Cuerpo(distancia*2,distancia);
		Cuerpo p2=new Cuerpo(distancia*3,distancia);
		Cuerpo pos[]= {p2,p1,p};
		CabezaS c=new CabezaS(distancia*4,distancia);
		
		this.cabeza = c;
		this.cuerpo=new ArrayList<Cuerpo>(pos.length);
		for(int i=0; i<pos.length;i++) 
			cuerpo.add(pos[i]);
		
		Snake.velocidad=distancia;
		Snake.tamano=distancia;
		CabezaS.distanciaPuntos=Snake.velocidad;
	}
	/**
	 * mueve la serpiente, para ello avanza la cabeza de la serpiente con su metodo, y luego asigna los valores de las posiciones a la posicion q abandona la siguiente
	 */
	public void moverS(){
		
		for(int i=cuerpo.size()-1; i>0; i--) {
			cuerpo.get(i).setX(cuerpo.get(i-1).getX());
			cuerpo.get(i).setY(cuerpo.get(i-1).getY());
		}
		
		cuerpo.get(0).setX(cabeza.getX());
		cuerpo.get(0).setY(cabeza.getY());
		
		cabeza.avanza();
	}
	
	
	/**
	 * cambia la direccion de la cabeza de la serpiente en funcion de la enumeracion tipo controlador.Movimiento recibida, y llama segun cada cual al metodo de la cabeza apropiado
	 * @param s
	 */
	public void cambiarD(Movimiento s) {
		if (s.compareTo(Movimiento.arriba)==0)
			cabeza.moverA();
		if (s.compareTo(Movimiento.abajo)==0)
			cabeza.moverAb();
		if (s.compareTo(Movimiento.izquierda)==0)
			cabeza.moverIzq();
		if (s.compareTo(Movimiento.derecha)==0)
			cabeza.moverDer();
		if (s.compareTo(Movimiento.parado)==0)
			cabeza.moverParado();
	}

	
	/**
	 * devuelve si la cabeza toca el cuerpo
	 * @deprecated usar el zocupada de juegoSimple
	 * @return
	 */
	public boolean tocaCuerpo() {
		boolean toca=false;
		
		for(int i=0;i<cuerpo.size();i++) {
			toca=toca|((Posicion)this.cabeza).equals(cuerpo.get(i));
		}
		return toca;
	}
	
	/**
	 * añade al cuerpo una posicion igual a la ultima posicion, de tal forma que cuando se mueva la serpiente, al haber dos en la ultima posicion, esta ultima permanece ahi hasta que se mueve de nuevo.
	 */
	public void anadirCuerpo () {
		Cuerpo aux=new Cuerpo(cuerpo.get(cuerpo.size()-1).getX(),cuerpo.get(cuerpo.size()-1).getY());
		boolean b=cuerpo.add(aux);
		if (b) {}//la idea era lanzar alguna excepcion si no lo añadia. Pero no se ha implementado 
	}
	
	/**
	 * devuelve la zona ocupada por la serpiente
	 * 
	 * @param b determina si incluimos o no la cabeza de la serpiente true si, false no; sirve para distintos calculos
	 * @return 
	 */
	public ArrayList<Posicion> zonaOcu(boolean b){
		ArrayList<Posicion> zOcupada=this.cabeza.calculoHitBox();
		
		for (int i=0; i<this.cuerpo.size();i++)
			zOcupada.add((Posicion)this.cuerpo.get(i));
		
		if (!b)
			zOcupada.remove((Posicion)this.cabeza);
		
		return zOcupada;
	}
	
	/**
	 * metodo para pintar la snake.Snake con circulos
	 * @param g
	 */
	public void pintarSnake(Graphics2D g) {
		Circulo c=new Circulo(tamano,g);
		
		c.pintar(cabeza);
		
		for(int i=0;i<cuerpo.size();i++) {
			c.pintar(cuerpo.get(i));
		}
	}
}
