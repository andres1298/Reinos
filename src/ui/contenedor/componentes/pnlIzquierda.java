package ui.contenedor.componentes;

import javax.swing.JPanel;
import ui.eConfiguracion;

public class pnlIzquierda extends JPanel {
	private final int ANCHO = 90;

	public pnlIzquierda(int height) {
		this.setLayout(null);
		this.setSize(ANCHO, height);
		this.setBackground(eConfiguracion.COLOR_FONDO);
		this.setForeground(eConfiguracion.COLOR_LETRA);
	}

}