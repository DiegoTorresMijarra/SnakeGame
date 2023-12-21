package api.elementosJuego;

import api.snake.Cuerpo;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
/**
 * Clase de JLabel con unos parametros concretos,
 * @author Diego
 *
 */
@SuppressWarnings("serial")
public class JLabelMenuFinal extends JLabel{
	/**
	 * constructor que genera un elementosJuego.JLabelMenuFinal, con el texto en el JLabel
	 * @param texto
	 */
	 JLabelMenuFinal(String texto){
		super(texto);
		super.setHorizontalAlignment(SwingConstants.CENTER);
		super.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
		super.setBorder(BorderFactory.createLineBorder(Color.black,3));
		super.setBackground(Cuerpo.color);
		super.setOpaque(true);
	 }
}
