package api.records;

/**
 * clase que referencia un objeto con un String con un nombre y un int con una puntuacion.
 * @author Diego
 *
 */
public class Record {
	public String nombre;
	int puntuacion;
	
	/**
	 * constructor con un string y un int
	 * @param nombre
	 * @param puntuacion
	 */
	public Record(String nombre, int puntuacion) {
		super();
		this.nombre = nombre;
		this.puntuacion = puntuacion;
	}
	/**
	 * constructor con dos strings, si hay algun error a la hora de procesar el string de la puntuacion, seteara esta a 0
	 * @param nombre
	 * @param puntuacion
	 */
	public Record(String nombre, String puntuacion) {
		super();
		this.nombre = nombre;
		try {
			this.puntuacion =Integer.valueOf(puntuacion);
		} catch (Exception e) {
			System.out.print("Controlada: ");
			e.printStackTrace();
			this.puntuacion=0;
		}
	}
	/**
	 * compara la puntuacion de dos records, el que llama y el que recibe.
	 * @param r el record a comparar
	 * @return un int con los valores{ -1 si el llamando es menor que el recibiendo, 0 si son iguales, 1 si llamando es mayor que recibiendo o -99 si ha habido algun error(nulos etc, por lo general no es probable que devuelva esto)}
	 */
	public int comparando(Record r) {
		int res=-99;//errores / alguno nulo
		if (this.puntuacion==r.puntuacion)
			res=0;	
		if (this.puntuacion<r.puntuacion)
			res=-1;
		if (this.puntuacion>r.puntuacion)
			res=+1;
		
		return res;
	}
	/**
	 * devuelve un String con el valor de la puntuacion o 0 si hay algun problema.
	 * @return
	 */
	public String getStringPuntuacion() {
		String record;
		try {
			record=Integer.toString(puntuacion);
		} catch (Exception e) {
			e.printStackTrace();
			record="0";
		}
		return record;
	}
}
