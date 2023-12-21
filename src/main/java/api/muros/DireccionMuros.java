package api.muros;

/**
 * enumeracion de la direcciones posibles de los muros
 * @author Diego
 *
 */
public enum DireccionMuros {

	Horizontal,
	Vertical,
	Oblicuo,
	H2, //para cuando quiero q sea la siguiente posicion a la der
	V2,//para cuando quiero q sea la siguiente posicion bajo esta
	O2,//oblicuo ascendente
	H3,//para cuando quiero q sea la segunda posicion a la der
	V3;//para cuando quiero q sea la segunda posicion bajo esta
	
	
	/**
	 * nos devuelve una direccion aleatoria entre las posibles para un 2x2 con b=true o un 3x3 con b=false 
	 * @param b
	 * @return
	 */
	static DireccionMuros aleatorio(boolean b) {
		if (b) {
			int num=(int)(Math.random()*6);
			switch(num) {
				case 0: return DireccionMuros.Horizontal;
				case 1: return DireccionMuros.Vertical;
				case 2: return DireccionMuros.Oblicuo;
				case 3: return DireccionMuros.H2;
				case 4: return DireccionMuros.V2;
				case 5: return DireccionMuros.O2;
				default:return null;
			}
		}
		else {
			int num=(int)(Math.random()*8);
			switch(num) {
			case 0: return DireccionMuros.Horizontal;
			case 1: return DireccionMuros.Vertical;
			case 2: return DireccionMuros.Oblicuo;
			case 3: return DireccionMuros.H2;
			case 4: return DireccionMuros.V2;
			case 5: return DireccionMuros.O2;
			case 6: return DireccionMuros.H3;
			case 7: return DireccionMuros.V3;
			default:return null;
			}
			
		}
	}
	
}

