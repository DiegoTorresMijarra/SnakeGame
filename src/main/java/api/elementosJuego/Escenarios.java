package api.elementosJuego;

/**
 * clase que contiene una enumeracion tipo elementosJuego.Escenario y un contador, que hace referecia al estilo. Se usa para la seleccion de estilos de forma circular
 * @author Diego
 *
 */
public class Escenarios {
	public Escenario esce;
	int cont;
	/**
	 * genera un escenario en cont=0 y escenario=bosque
	 */
	public Escenarios(){
		cont=0;
		esce=Escenario.Bosque;
	}
	
	/**
	 * setea el escenario en funcion de un boolean que hara que sumemos o restemos uno al contador, de tal forma que la seleccion sea circular.
	 * @param b
	 */
	public void setEscenarios(boolean b){
		if (b)
			cont+=1;
		else 
			cont-=1;
		
		int resto=cont%4;
		
		switch (Math.abs(resto)) {
			case 0: this.esce=Escenario.Bosque; break;
			case 1: this.esce=Escenario.Pradera; break;
			case 2:this.esce=Escenario.Oceano; break;
			case 3:this.esce=Escenario.Volcan; break;
		}
	}
}
