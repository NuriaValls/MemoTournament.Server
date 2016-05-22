package view;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Gestiona i mostra dialegs amb un missatge concret segons si son d'error o d'informacio.
 * @author nvall
 *
 */
public class Dialog extends JDialog{

	
	/**
	 * Crea un dialeg informatiu amb el missatge en format String que rep com a parametre.
	 * @param message
	 */
	public static void DialogOK(String message){
		JOptionPane.showMessageDialog(null,message, "Information message", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Crea un dialeg d'error amb el missatge en format String que rep com a parametre.
	 * @param message
	 */
	public static void DialogKO(String message){
		JOptionPane.showMessageDialog(null,message, "An Error Has Ocurred", JOptionPane.ERROR_MESSAGE);
	}
}
