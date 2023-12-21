package api.elementosJuego;

import api.snake.CabezaS;
import api.comida.Comida;
import api.muros.Muro;
import api.muros.Obstaculo;
import api.snake.Cuerpo;

import java.awt.Color;
/**
 * Clase de COnstructor de juego, sirve para setear los valores de la ventana de juego, no solo los de la serpiente, OJO.
 * @author Diego
 *
 */
public class ConstructorJuego {

	Escenario escena;
	boolean muros=false;
	boolean velocidadR=false;
	boolean ventanaG=false;
	String nombreJugador;
	
	/**
	 * constructor para generar un constructor de juegos con los parametros a pasar
	 * @param escena enumeracion tipo elementosJuego.Escenario
	 * @param muros true si spawnean muros, false si no
	 * @param velocidadR melocidad rapida o lenta(false)
	 * @param ventanaG tamaño grande o pequeño(false)
	 * @param nombreJugador nombre del jugador
	 */
	public ConstructorJuego(Escenario escena, boolean muros, boolean velocidadR, boolean ventanaG, String nombreJugador) {
		this.escena = escena;
		this.muros = muros;
		this.velocidadR = velocidadR;
		this.ventanaG = ventanaG;
		this.nombreJugador=nombreJugador;
		
		JuegoSnake.bMuros=muros;
		Muro.distancia=JuegoSnake.speed;
		setVelocidad();
		setVentana();
		setEscena();
	}
	/**
	 * llama a los set de cada opcion de la enumeracion
	 */
	void setEscena() {
		switch(escena) {
			case Bosque:setBosque();break;
			case Pradera:setPradera();break;
			case Oceano:setOceano(); break;
			case Volcan:setVolcan();break;
		}
	}
	/**
	 * devuelve el tamaño de la ventana
	 * @return int tamaño ventana
	 */
	int tamanoV() {
		if (ventanaG)
			return 705;
		else
			return 505;
	}
	/**
	 * devuelve la velocidad de refresco de la ventana de juego
	 * @return int velocidad refres ventana, tb pueden ponerse las otras velocidades q estan comentadas
	 */
	long velocidad() {
		if (velocidadR)
			return 300;
			//return 500;
		else
			return 500;
			//return 1000;
	}
	/**
	 * setea los valores static de elementosJuego.JuegoSnake, snake.CabezaS, snake.Cuerpo, comida.Comida, muros.Obstaculo; para el estilo:<b> bosque</b>
	 */
	void setBosque() {
		JuegoSnake.colorFondo=new Color(82, 190, 128);
		CabezaS.color=new Color(142, 68, 173);
		Cuerpo.color=new Color(195, 155, 211);
		Comida.color=new Color(255,0,0);
		Obstaculo.color=new Color(135, 54, 0);
	}
	/**
	 * setea los valores static de elementosJuego.JuegoSnake, snake.CabezaS, snake.Cuerpo, comida.Comida, muros.Obstaculo; para el estilo:<b> pradera</b>
	 */
	void setPradera() {
		JuegoSnake.colorFondo=new Color(218, 247, 166);
		CabezaS.color=new Color(220, 118, 51);
		Cuerpo.color=new Color(245, 176, 65);
		Comida.color=new Color(244, 143, 177);
		Obstaculo.color=new Color(27, 94, 32);
	}
	/**
	 * setea los valores static de elementosJuego.JuegoSnake, snake.CabezaS, snake.Cuerpo, comida.Comida, muros.Obstaculo; para el estilo:<b> oceano</b>
	 */
	void setOceano() {
		JuegoSnake.colorFondo=new Color(128, 222, 234);
		CabezaS.color=new Color(26, 35, 126);
		Cuerpo.color=new Color(2, 119, 189);
		Comida.color=new Color(255, 235, 59);
		Obstaculo.color=new Color(102, 0, 102);
	}
	/**
	 * setea los valores static de elementosJuego.JuegoSnake, snake.CabezaS, snake.Cuerpo, comida.Comida, muros.Obstaculo; para el estilo:<b> volcan</b>
	 */
	void setVolcan() {
		JuegoSnake.colorFondo=new Color(229, 115, 115);
		CabezaS.color=new Color(33, 33, 33);
		Cuerpo.color=new Color(183, 28, 28);
		Comida.color=new Color(211, 84, 0);
		Obstaculo.color=new Color(66, 73, 73);
	}
	/**
	 * setea los valores static de muros.Muro, elementosJuego.Contenedor y elementosJuego.JuegoSnake en funcion del tamanoV()
	 */
	void setVentana() {
		int tamano=tamanoV();
		Muro.ancho=tamano;
		Contenedor.tamano=tamano;
		JuegoSnake.ancho=tamano;
	}
	/**
	 * setea la velocidad static del elementosJuego.JuegoSnake en funcion de velocidad()
	 */
	public void setVelocidad() {
		JuegoSnake.velocidadJuego=velocidad();
	}
}
