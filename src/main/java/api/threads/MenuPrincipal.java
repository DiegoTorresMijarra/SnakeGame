package api.threads;

import api.elementosJuego.Escenarios;
import api.Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


/**
 * clase que referencia un menuPrincipal del juego. este extiende de Jframe e implementa actionListener y Runnable. Esto es porque queremos que sea una ventana con una serie de elementos que nos genere un constructor y que pase los parametros al juego. la interfaz runnable es para poder construir un thread que contenga este objeto sin necesidad de crear una clase especifica. actionlistener, es para que el propio objeto escuche los eventos que interfieran con sus botones.<br>
 * a destacar, el trabajo de mejora con esta clase debe enfocarse en refactorizar el constructor, para separar la construccion de cada objeto de la clase.
 *
 * @author Diego
 *
 */
@SuppressWarnings("serial")
public class MenuPrincipal extends JFrame implements ActionListener, Runnable{
	//deberia iniciarlos con metodos para liberar el constructor
	JPanel panel;
	ArrayList<JButton> botonIzq; //arraylistBotones seleccion izq
	ArrayList<JLabel> sel; //arraylist label de seleccion
	ArrayList<JButton> botonDer; //arraylistBotones seleccion der
	ArrayList<JLabel> label;  //arraylist label de titulo
	JTextField nombre;// JTextField q recoge el nombre del usuario
	JButton aJugar; //boton que genera el juego

	//parametros seleccionables para el juego
	public Escenarios escena=new Escenarios();
	public boolean muros=false;
	public boolean velocidadR=false;
	public boolean ventanaG=false;

	/**
	 * constructor que setea los componentes del Jframe, los botones y demás.
	 */
	public MenuPrincipal(){
		super("MenuSeleccionSnake");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0, 500, 600);

		//logo
		JLabel logo=new JLabel("");
		logo.setBounds(0,0,486,100);
		logo.setOpaque(true);
		logo.setBackground(new Color(101,236,120));
		logo.setIcon(createImageIcon("/LogoSnake.png","Saludo"));
		logo.setHorizontalAlignment(SwingConstants.HORIZONTAL);
		logo.setBorder(BorderFactory.createLineBorder(Color.black,5));
		super.add(logo);

		//panel donde estan los botones
		panel=new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBorder(BorderFactory.createLineBorder(Color.black,5));

		GridBagLayout dispo=new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		panel.setLayout(dispo);
		c.fill=GridBagConstraints.BOTH;

		panel.setBackground(new Color(182,255,120));
		super.add(panel);

		//arraylistBotones seleccion izq
		botonIzq=new ArrayList<JButton>();
		for(int i=1; i<=7;i+=2) {
			c.gridy=i;
			JButton btn=new JButton("<-");
			btn.addActionListener(this);
			btn.setOpaque(true);
			btn.setBackground(Color.white);
			panel.add(btn,c);
			botonIzq.add(btn);
		}

		//arraylist Jlabel de las casillas de seleccion
		sel=new ArrayList<JLabel>();
		c.gridwidth=3;
		for(int i=1; i<=7;i+=2) {
			c.gridy=i;
			JLabel lbl=new JLabel("");
			lbl.setBorder(BorderFactory.createLineBorder(Color.black,1));
			lbl.setOpaque(true);
			lbl.setBackground(Color.orange);
			lbl.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(lbl,c);
			sel.add(lbl);
		}

		//arraylistBotones seleccion
		c.gridwidth=1;
		c.gridx=5;
	    botonDer=new ArrayList<JButton>();
		for(int i=1; i<=7;i+=2) {
			c.gridy=i;
			JButton btn=new JButton("->");
			btn.addActionListener(this);
			btn.setOpaque(true);
			btn.setBackground(Color.white);
			panel.add(btn,c);
			botonDer.add(btn);
		}

		//arraylist Jlabel de los titulos de seleccion
		label=new ArrayList<JLabel>();
		c.gridx=0;
		c.gridwidth=7;
		c.insets=new Insets(10,0,3,0);
		for(int i=0; i<9;i+=2) {
			c.gridy=i;
			JLabel lbl=new JLabel("");
			lbl.setBorder(BorderFactory.createLineBorder(Color.black,3));
			lbl.setHorizontalAlignment(SwingConstants.CENTER);
			lbl.setOpaque(true);
			panel.add(lbl,c);
			label.add(lbl);
		}

		label.get(0).setText("ESCENARIO");
		label.get(1).setText("OBSTACULOS");
		label.get(2).setText("TAMAÑO VENTANA");
		label.get(3).setText("VELOCIDAD");
		label.get(4).setText("INTRODUCE TU NOMBRE");

		//recoge el nombre del usuario
		c.insets=new Insets(0,0,15,0);
		c.gridy=10;
		nombre=new JTextField();
		nombre.setOpaque(true);
		nombre.setBackground(Color.orange);
		nombre.setHorizontalAlignment(JTextField.CENTER);
		nombre.setBorder(BorderFactory.createLineBorder(Color.black,1));
		panel.add(nombre,c);

		// boton a jugar
		c.gridy=11;
		aJugar=new JButton("¡¡ A JUGAR !!");
		aJugar.setOpaque(true);
		aJugar.setBackground(Color.pink);
		aJugar.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
		aJugar.setBorder(BorderFactory.createLineBorder(Color.white,3));
		panel.add(aJugar,c);

		aJugar.addActionListener(new ActionListener() {//no le añado el this, porque es un boton especial
			public void actionPerformed(ActionEvent e) {
				crearJuego(getNombre());

			}
		});

		//setea los botenes a la posicion por defecto
		defecto();
	}

	/**
	 * Crea una ImageIcon,a raiz del path y una descripcion. Lo busca dentro de resources del maven.
	 *
	 * @param path
	 * @param description
	 * @return
	 */
	public ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);

		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		}
		else {
			System.err.println("No se ha encontrado el file: " + path);//deberia meterlo con exceptios probablemente
			return null;
		}
	}

	/**
	 * setea los colores colores de los botones de cada fila. para ello  recibe un int con con el valor de la posicion de los botones a cambiar y un boolean en funcion de la opcion que se ha seleccionado.
	 * @param n
	 * @param b
	 */
	public void setColorBotones(int n, boolean b){
		if (n!=0) {
			if (b) {
				botonIzq.get(n).setBackground(Color.white);
				botonDer.get(n).setBackground(Color.gray);
			}
			else {
				botonIzq.get(n).setBackground(Color.gray);
				botonDer.get(n).setBackground(Color.white);
			}
		}
	}
	/**
	 * metodo implementado de la interfaz de la que extiende la clase. obtiene el recurso (int del valor del boton que recibe el evento) y se lo pasa a setear. despues llama al boton actualizar. primero lo busca en el arraylist de los botones de la izq y si no lo encuentra ahi, lo busca en los de la derecha.
	 */
	public void actionPerformed(ActionEvent e) {
		int valor=botonIzq.indexOf(e.getSource());

		if (valor!=-1) {
			setear(valor,false);
		}
		else {
			valor=botonDer.indexOf(e.getSource());
			setear(valor,true);
		}
		actualizar(valor);
	}

	/**
	 * actualizamos lo q se ha modificado del  de los label del boton que los que lo llama
	 * @param v
	 */
	public void actualizar(int v) {
		switch(v) {
			case 0:labelEscena();break;
			case 1:labelMuros(); break;
			case 2:labelVentana();break;
			case 3:labelVelocidad();break;
		}
	}
	/**
	 *  setea los valores del objeto, en funcion de lo q se haya modificado y de si se pasa true o false.
	 * @param v el valor del boton que llama a este metodo
	 * @param b true o false, para setear el valor q corresponde y llama al setEscenarios(boolean b) de la clase elementosJuego.Escenarios
	 */
	public void setear(int v,boolean b) {

		switch(v) {
			case 0:escena.setEscenarios(b);break;
			case 1:muros=b; break;
			case 2:ventanaG=b;break;
			case 3:velocidadR=b;break;
		}
		setColorBotones(v,b);
	}

	/**
	 * setea el label de la escena en funcion del estilo de escena q seleccionemos
	 */
	public void labelEscena() {
		String texto=escena.esce.toString();
		sel.get(0).setText(texto.toUpperCase());

		switch(escena.esce) {
			case Bosque:sel.get(0).setBackground(Color.green); break;
			case Pradera:sel.get(0).setBackground(Color.yellow);break;
			case Oceano:sel.get(0).setBackground(Color.blue); break;
			case Volcan:sel.get(0).setBackground(Color.red);break;
			default:sel.get(0).setBackground(Color.white);
		}
	}
	/**
	 * actualiza el label de los muros.
	 */
	public void labelMuros() {
		if (muros) {
			sel.get(1).setText("O N");
		}
		else {
			sel.get(1).setText("O F F");
		}
	}
	/**
	 * actualiza el label de la velocidad
	 */
	public void labelVelocidad() {
		if (velocidadR) {
			sel.get(3).setText(" RAPIDO ");
		}
		else {
			sel.get(3).setText(" LENTO ");
		}
	}
	/**
	 * actualiza el label de la ventana
	 */
	public void labelVentana() {
		if (ventanaG) {
			sel.get(2).setText("  GRANDE  ");
		}
		else {
			sel.get(2).setText(" PEQUEÑA ");
		}
	}
	/**
	 * setea todos los valores a false y la escena por defecto del constructor. luego realiza como si el usuario hubiera seleccionado estas opciones
	 */
	public void defecto() {
		escena=new Escenarios();
		muros=false;
		velocidadR=false;
		ventanaG=false;
		for(int i=0;i<botonIzq.size();i++)
			setColorBotones(i,false);

		labelEscena();
		labelMuros();
		labelVentana();
		labelVelocidad();
	}
	/**
	 * obtiene el nombre de la ventana de texto nombreJugador, si es un String vacio, le devuelve "Jugador1"
	 * @return el nombre del de la ventana de texto nombreJugador, si es un String vacio, le devuelve "Jugador1"
	 */
	public String getNombre() {
		String nombreJugador=nombre.getText();
		if (nombreJugador.isEmpty())
			nombreJugador="Jugador1";

		return nombreJugador;
	}

	/**
	 * establece el boolean jugamos y setea la visibilidad del Jframe a false, para que no se vea. Podriamos cerrar la ventana para que no consuma recursos, pero facilita la ejecucion del main
	 * @param nombreJugador
	 */
	void crearJuego(final String nombreJugador) {
		this.setVisible(false);
		Main.jugamos=true;
	}

	/**
	 * setea la visibilidad del Jframe del objeto a true, creamos un metodo especifico para que se pueda llamar con más facilidad, aunq tb se podria utiliza el metodo del super
	 */
	public void menuVisible() {
		this.setVisible(true);
	}
	/**
	 * para comenzar el runnable, setea este como visible.
	 */
	public void run() {
		menuVisible();
	}
}
