package api.elementosJuego;

import api.records.Record;
import api.threads.ThreadMenuFinal;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * clase que referencie a un menu final del juego, tiene el record del jugador, el record historico, labels para mostrar estos datos y dos botones para comunicar al programa si el usuario quiere jugar o no de nuevo.Usa un tipo de JLabel que hemos seteado previamente para agilizar el codigo de esta clase. hereda de Jpanel pero se puede trabajar para que sea un Jframe en un futuro.  <br>
 * el trabajo con esta clase debe enfocarse en las rutas y el trabajo con el fichero, para permitir que se guarden distintos records y mostrarlos en una tabla.
 * @author Diego
 *
 */
@SuppressWarnings("serial")
public class MenuFinal extends JPanel {
	Record personal;
	Record historico;
	
	JLabel labelRecord;
	JLabel labNombHistorico;
	JLabel labPuntHistorico;	
	
//	C:\\Users\\Diego\\Desktop\\Grado Superior\\Programación\\WorkspaceEclipse22\\TrabajoSnakeF\\src\\main\\resources\\PuntuacionGuardada.txt
	
	/**
	 * puede dar problemas la ruta, ya que en mi ordenador no funcionan bien las rutas 
	 */
	static File doc=new File(".\\src\\main\\resources\\PuntuacionGuardada.txt");
	
	/**
	 * boolean que bloquea el thread hasta que se responde.
	 */
	static boolean respondido=false;
	
	/**
	 * constructor que recibe el record (puntuacion y nombre) del usuario, lo usa el metodo pantallaFinal de elementosJuego.JuegoSnake
	 * @param personal
	 */
	public MenuFinal(Record personal) {
		super();
		super.setBackground(JuegoSnake.colorFondo);
		this.personal=personal;
		historico=this.getRecord();
	}
	
	/**
	 * 
	 */
	public void setearPanel() {
		GridBagLayout dispo=new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		super.setLayout(dispo);
		c.fill=GridBagConstraints.BOTH;
		c.gridy=0;
		c.weightx=1;
		c.gridwidth=2;
		c.ipady=2;
		c.insets=new Insets(5, 5, 5, 5);
		
		JLabelMenuFinal labelFinJuego=new JLabelMenuFinal("):  G A M E   O V E R :)");
		super.add(labelFinJuego,c);
		seteandoRecord(c);
		setBotones(c);
	}
	/**
	 * 
	 * @param c
	 */
	void setBotones(GridBagConstraints c) {
		c.gridy+=1;
		c.gridwidth=1;
		JButton menuPrincipal=new JButton("Menu Principal");
		super.add(menuPrincipal,c);
		menuPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThreadMenuFinal.seguirJugando="no";
				respondido=true;
			}
		});
		
		JButton reiniciarJuego=new JButton("Volver a Jugar");
		super.add(reiniciarJuego,c);
		reiniciarJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThreadMenuFinal.seguirJugando="si";
				respondido=true;
			}
		});
	}

	/**
	 * seteara el record si es mayor o igual q el record anterior, dejo el case 0: por si se quisiera hacer algo distinto si son iguales, pero en principio no.
	 */
	void seteandoRecord(GridBagConstraints c) {
		int comparado=personal.comparando(historico);
		
		switch (comparado) {
			case -1: noRecord(c);break;
			case 0: //noRecord();break; tb puedo ponerlo asi
			case 1: nuevoRecord(c); break;
		}
	}
	
	/**
	 *metodo que setea un nuevo record, para ello lo guarda en el file y setea la pantalla para mostrar un nuevo record 
	 * @param c las GridBagConstraints del JPanel (Objeto) q contendra lo que se elabore dentro del metodo
	 */
	public void nuevoRecord(GridBagConstraints c) {
		setRecord();
		
		//añadidos a la patalla
		c.gridy+=1;
		JLabelMenuFinal labelEtiqueta=new JLabelMenuFinal("Nuevo records.Record!!");
		super.add(labelEtiqueta,c);
		
		c.gridy+=1;
		
		JLabelMenuFinal labelJugador=new JLabelMenuFinal(personal.nombre+" : "+personal.getStringPuntuacion());
		super.add(labelJugador,c);
	}
	
	/**
	 * metodo que no setea el record y prepara la pantalla para mostrar el record de la persona y el historico 
	 * @param c  las GridBagConstraints del JPanel (Objeto) q contendra lo que se elabore dentro del metodo
	 */
	public void noRecord(GridBagConstraints c) {
		
		//añadidos a la patalla
		c.gridy+=1;
		JLabelMenuFinal labelEtiqueta=new JLabelMenuFinal("Uy!!, Casi lo Consigues!!");
		super.add(labelEtiqueta,c);
		c.gridy+=1;
		
		JLabelMenuFinal labelCabeceraRecord=new JLabelMenuFinal("records.Record Actual: ");
		super.add(labelCabeceraRecord,c);
		
		c.gridy+=1;
		JLabelMenuFinal labelRecord=new JLabelMenuFinal(historico.nombre+" : "+historico.getStringPuntuacion());
		super.add(labelRecord,c);
		
		c.gridy+=1;
		JLabelMenuFinal labelCabeceraPersonal=new JLabelMenuFinal("Tu Puntuacion: ");
		super.add(labelCabeceraPersonal,c);
		
		c.gridy+=1;
		JLabelMenuFinal labelJugador=new JLabelMenuFinal(personal.nombre+" : "+personal.getStringPuntuacion());
		super.add(labelJugador,c);
	}

	/**
	 * devuelve el records.Record del file, si hay algun problema lanzara un objeto Recurd ={ nombre="Campeon",punt="0"};
	 * 
	 * @return
	 */
	public Record getRecord() {
		String nombre;
		String punt;
		try {
			BufferedReader a=new BufferedReader(new FileReader(doc));
			nombre=a.readLine();
			punt=a.readLine();
			a.close();
			
			if(nombre==null)
				throw new Exception("Nombre nulo");
			
			if(punt==null)
				throw new Exception("Puntuacion nulo");
			
			return new Record(nombre,punt);
			
		}catch (Exception e) { 
			System.out.print("Controlada: ");
			e.printStackTrace();
			return new Record("Campeon","0");
		} 
		
	}
	
	/**
	 * setea el record del file, para ello resetea el doc y pone el record actual
	 */
	public void setRecord() {
		resetearDoc();
		try {
			BufferedWriter a=new BufferedWriter(new FileWriter(doc));
			a.write(personal.nombre);
			a.newLine();
			a.write(personal.getStringPuntuacion());
			a.close();
		}catch (Exception e) { 
			e.printStackTrace();
		} 
	}
	
	/**
	 * intenta resetear el archivo, si no lo consigue lo mantiene igual
	 */
	public void resetearDoc() {
		File aux=doc;
		try {
			boolean borrado=doc.delete();
			if(borrado) {
				File nuevo=new File(aux.getAbsolutePath());
				boolean creado=nuevo.createNewFile();
				
				if(!creado)
					doc=aux;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * bucle bloqueante, que espera hasta que se pulse un boton del objeto. Una vez hecho esto pone respondido=false; para que la proxima vez que llegue a este metodo tb bloquee el menu. <br>
	 * NOTA: el thread.sleep, es al igual que en el main y en diversos metodos, para rtrasar la ejecucion del programa y evitar que este crasee. se puede reducir el tiempo de ejecucion para acelerarlo, pero hay q tener en cuenta lo dicho.
	 */
	public void esperarRespuesta() {
		while(!respondido) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		respondido=false;
	}
}
