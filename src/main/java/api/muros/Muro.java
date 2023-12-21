package api.muros;

import api.dibujo.Cuadrado;
import api.elementosJuego.JuegoSnake;
import api.utils.Posicion;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * objeto que contiene los distintos recursos necesarios para generar un muro(arraylist de obstaculos)
 * 
 * seguro q se puede hacer más fino, segurisimo pq esta bastante feo el codigo
 * @author Diego
 *
 */
public class Muro {
	
	static double maxT=5000; //tiempo que duraran los muros 
	public double tiempo; //cuando expirara el muro
	
	public static double ancho;
	
	//distancia deberia setearla de alguna forma, tal vez todos hereden de una interfaz q tenga ella o algo asi 
	public static int distancia;
	
	static int random;
	
	DireccionMuros dir;

	public ArrayList<Posicion> ocupado;
	
	public ArrayList<Obstaculo> ladrillos;
	
	/**
	 * constructor de un muro en la posicion -speed, -speed, con tiempo maximo, es decir un muro que no aparece en pantalla y q permanecera todo el tiempo q se este ejecutando la aplicacion. se usa cuando no queremos q aparezcan muros en nuestro juego, o cuando se ha intentado ejecutar un numero de veces seguidas el siguiente constructor y no se ha conseguido generar un muro. en este caso tal vez nos interesaria q se pudiera actualizar, pero como este caso se debe a un estado de juego muy avanzado, es mejor que se genere este muro en vez de otro, que vuelva a generar el problema anterior.
	 */
	public Muro() {
		this.ladrillos=constructor0();
		this.tiempo=Double.MAX_VALUE;
		Obstaculo.tamano=Muro.distancia;
	}
	
	
	/**
	 * constructor de un muro, a raiz de las posiciones q no puede ocupar. llama a constructor en funcion del numero de casillas aleatorias del muro. si falla ese constructor (devuelve null por max intentos) genera un muro de muros.Muro();<br>
	 * el tiempo de existencia de los muros es el actual+ maxT y el tamaño viene determinado por la distancia
	 * @param ocupad
	 */
	public Muro(ArrayList <Posicion> ocupad) {
		this.ocupado=ocupad;
		random=(int)(Math.random()*3)+1;
		this.ladrillos=constructor(random);
		
		if (this.ladrillos==null) 
			this.ladrillos=constructor0();
		
		this.tiempo=System.currentTimeMillis()+maxT;
		Obstaculo.tamano=Muro.distancia;
	}
	/**
	 * genera el Arraylist de obstaculos en funcion de cuantas casiilas queremos q tnega el muro
	 * @param x numero de casillas
	 * @return arraylist obstaculo
	 */
	ArrayList<Obstaculo> constructor(int x){
		switch(x) {
			case 3: return constructor3();
			case 2: return constructor2();
			default: return constructor1();
		}
	}
	/**
	 * genera el arraylist obst de -dist -dist
	 * @return
	 */
	public ArrayList<Obstaculo> constructor0() {
		ArrayList<Obstaculo> construido=new ArrayList<Obstaculo>();
		construido.add(new Obstaculo(-distancia,-distancia));
		return construido;
	}
	
	
	/**
	 * constructor de un obstaculo 1x1
	 * 
	 * @return
	 */
	public ArrayList<Obstaculo> constructor1() {
		
		ArrayList<Obstaculo> zonaS=this.zonaSegura(0);
		if (zonaS!=null) {
			ArrayList<Obstaculo> construido=new ArrayList<Obstaculo>();
			
			construido.add(zonaS.get(0));
			
			return construido;
		}
		else return null;
	}
	/**
	 * metodo q llama al constructor de un obstaculo 2x2, luego selecciona las propias de la direccion escogida
	 * @return
	 */
	public ArrayList<Obstaculo> constructor2() {
		
		this.dir=DireccionMuros.aleatorio(true);
		
		return this.constructor2(dir);
	}
	
	/**
	*constructor de un arraylist de obstaculos de 2x2, contiene las 4 posibles
	*@return 
	*/
	ArrayList<Obstaculo>constructor2(DireccionMuros dm){
		ArrayList<Obstaculo> zonaS=this.zonaSegura(0);
		if (zonaS!=null) {
			//los q me voy a quedar
			int ob1;
			int ob2;
			
			switch(dm) {
				default:
				case Horizontal:ob1=0;ob2=1; break;
				case Vertical: ob1=0;ob2=2; break;
				case Oblicuo:ob1=0;ob2=3; break;
				case H2:ob1=2;ob2=3; break;
				case V2:ob1=1;ob2=3; break;
				case O2:ob1=2;ob2=1; break;
			}
			ArrayList<Obstaculo> construido=new ArrayList<Obstaculo>();
			construido.add(zonaS.get(ob1));
			construido.add(zonaS.get(ob2));
			return construido;
		}
		else return null;
	}
	/**
	 * metodo q llama al constructor de un obstaculo 3x3, luego selecciona las propias de la direccion escogida
	 * @return
	 */
	public ArrayList<Obstaculo> constructor3() {
		this.dir=DireccionMuros.aleatorio(false);
		
		return this.constructor3(dir);
	}
	/**
	*constructor de un arraylist de obstaculos de 2x2, contiene las 4 posibles
	*@return
	*/
	ArrayList<Obstaculo> constructor3(DireccionMuros dm){
		ArrayList<Obstaculo> zonaS=this.zonaSegura(0);
		if (zonaS!=null) {
			//los q me voy a quedar
			int ob1;
			int ob2;
			int ob3;
			
			switch(dm) {
				default:
				case Horizontal:ob1=0;ob2=1;ob3=2; break;
				case Vertical: ob1=0;ob2=3;ob3=6; break;
				case Oblicuo:ob1=0;ob2=4;ob3=8; break;
				case H2:ob1=3;ob2=4;ob3=5; break;
				case V2:ob1=1;ob2=4;ob3=7; break;
				case O2:ob1=6;ob2=4;ob3=2; break;
				case H3:ob1=6;ob2=7;ob3=8; break;
				case V3:ob1=2;ob2=5;ob3=8; break;
			}
			ArrayList<Obstaculo> construido=new ArrayList<Obstaculo>();
			
			construido.add(zonaS.get(ob1));
			construido.add(zonaS.get(ob2));
			construido.add(zonaS.get(ob3));
			
			return construido;
		}
		else return null;
		
	}
	
	/**
	 * me devuelve una zona segura (en la que podemos construir nuestro muro) del tamaño determinado por random, usa recursividad de tal forma, q si no cosigue encontrar una zona segura en 10 intentos, devuelve null
	 * @return una zona segura en la que se puede construir un muro, o un null si no consigue encontrarlo
	 */
	ArrayList<Obstaculo> zonaSegura(int n){
		n++;
		if(n>=10)
			return null;
		else {
			ArrayList<Obstaculo> zs=new ArrayList<Obstaculo>();
			
			int casillas=(int) (ancho/distancia)-3;//calculamos q entren los 3 obstaculos 
			int x=(int)(Math.random()*casillas)*distancia;
			int y=(int)(Math.random()*(casillas- JuegoSnake.salvaMarcador))*distancia; //y salvamos más para el que no choque con el marcador
			
			for(int i=0;i<random;i++) {
				for (int e=0;e<random;e++) {
					zs.add(new Obstaculo(x+i*distancia,y+e*distancia));
				}
			}
			if (this.ocupado(zs)) {
				zs=this.zonaSegura(n);
			}
			return zs;
		}
	}
	/**
	 * devuelve true si algun valor del arraylist se encuentra en this.ocupado
	 * @param zs zona segura a comprobar si coincide con alguna posicion ocupada
	 * @return
	 */
	boolean ocupado(ArrayList<Obstaculo> zs) {
		boolean b=false;
		for(int i=0; i<ocupado.size();i++) {
			b=b|zs.contains((Posicion)ocupado.get(i));
		}
		return b;
	}
	/**
	 * pinta las posiciones de un muro, para ello usa un motor grafico dado g y crea un cuadrado en el metodo
	 * @param g
	 */
	public void pintarMuro(Graphics2D g) {
		Cuadrado c=new Cuadrado(Obstaculo.tamano,g);
		for(int i=0;i<ladrillos.size();i++) {
			c.pintar(ladrillos.get(i));
		}
	}
	
}
