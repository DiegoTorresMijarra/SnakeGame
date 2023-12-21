package api.elementosJuego;

import javax.swing.JFrame;
/**
 * JFrame que contendra al elementosJuego.JuegoSnake, tiene un tamano  static que asigna el ancho y el alto de la ventana
 * @author Diego
 *
 */
@SuppressWarnings("serial")
public class Contenedor extends JFrame{
	static int tamano;
	/**
	 * constructor que le pasa como parametro el nombre de la ventana del Jframe
	 * @param name 
	 */
	public Contenedor(String name){
		super(name);
		super.setSize(tamano, tamano);
		super.setResizable(false);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);//me da problemas porq no lo pone visible ni con super ni con this (Se q es el mismo ), pero no se porq
	}

}
