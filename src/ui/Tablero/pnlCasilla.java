package ui.Tablero;

import bl.Construccion.Construccion;
import bl.Construccion.Recursos.IRecurso;
import bl.Construccion.Recursos.PowerUps.PowerUp;
import bl.Construccion.Tablero.Casilla;
import bl.Construccion.Tablero.Tablero;
import bl.Construccion.Tropa.Tropa;
import bl.Construccion.Tropa.TropaAtaque.Asesino;
import bl.Construccion.Tropa.TropaAtaque.Jinete;
import bl.Construccion.Tropa.TropaAtaque.TropaAtaque;
import ui.eConfiguracion;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static javax.swing.BorderFactory.createMatteBorder;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class pnlCasilla extends JPanel implements MouseListener {
	private pnlTablero tablero;
	public int i;
	public int j;
	private Color[] fondo = new Color[] { new Color(200, 200, 200, 200), new Color(200, 200, 200, 200) };
	private int[] casillaMarcada = new int[2]; //{ i, j }

	/**
	 * Create the panel.
	 */
	public pnlCasilla() {

	}

	public pnlCasilla(pnlTablero t) {
		this.tablero = t;
		this.setLayout(null);
		this.setOpaque(true);

		// Borde de la celda:
		this.setBorder(createMatteBorder(2, 2, 2, 2, new Color(80, 80, 80, 255)));

		// responde a clics:
		this.addMouseListener(this);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		GradientPaint gp = new GradientPaint(0, 0, getFondo()[0], 0, this.getHeight(), getFondo()[1]);
		g2.setPaint(gp);
		g2.fillRect(2, 2, this.getWidth() - 4, this.getHeight() - 4);

	}

	public void setFondo(Color[] fondo) {
		this.fondo = fondo;
	}

	public Color[] getFondo() {
		return this.fondo;
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {

		// Marcamos la casilla seleccionada.
		this.setCasillaMarcada(tablero.getCoordenadas((pnlCasilla) e.getComponent()));

		//Color[] colorVerde = new Color[] { new Color(20, 143, 119, 255), new Color(115, 198, 182, 255) };
		//this.tablero.pintarCasilla(this.getCasillaMarcada()[0], this.getCasillaMarcada()[1], colorVerde);

		JOptionPane.showMessageDialog(null,
				"Casilla seleccionada:\nI: " + i + ",  J: " + j);
		//Recursos
		Casilla casilla = tablero.getTableroLogica().obtenerCasilla(i,j);
		if (casilla.tieneRecurso()){
			recorgerRecurso(casilla);
		}

		Construccion construccion = tablero.getTableroLogica().obtenerPiezaCasilla(i,j);
		if(pnlTablero.getTropaSeleccionada() == null && construccion instanceof Tropa){
			Tropa tropa = (Tropa)construccion;
			pnlTablero.setTropaSeleccionada(tropa);
		}
		else if(pnlTablero.isAtaque) {
			atacar(construccion);
		}
	}

	public void mouseReleased(MouseEvent e) {
	}

	public int[] getCasillaMarcada() {
		return casillaMarcada;
	}

	public void setCasillaMarcada(int[] aCasillaMarcada) {
		casillaMarcada = aCasillaMarcada;
	}

	private int recogerPowerUp() {
		int opcion = 0;
		String cad = "Quiere Recoger Power Up:\n";
		String[] opciones = { "          Si          ", "          No          "};
		do {
			opcion = JOptionPane.showOptionDialog(null, cad, eConfiguracion.TITULO_APP,
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
		} while (opcion != 0 && opcion != 1);

		return opcion;
	}

	private int recogerOro() {
		int opcion = 0;
		String cad = "Quiere Recoger oro:\n";
		String[] opciones = { "          Si          ", "          No          "};
		do {
			opcion = JOptionPane.showOptionDialog(null, cad, eConfiguracion.TITULO_APP,
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
		} while (opcion != 0 && opcion != 1);

		return opcion;
	}

	private void atacar(Construccion construccion){
		pnlTablero.getTropaSeleccionada().atacar(construccion);
		pnlTablero.setTropaSeleccionada(null);
		pnlTablero.isAtaque = false;
	}

	private void recorgerRecurso(Casilla casilla){
		IRecurso recurso = casilla.getRecurso();
		Construccion construccion = tablero.getTableroLogica().obtenerPiezaCasilla(i,j);
		if(construccion instanceof TropaAtaque){
			TropaAtaque tropaAtaque = (TropaAtaque) construccion;
			if(recurso instanceof PowerUp){
				int opc = recogerPowerUp();
				if(opc == 0){
					tropaAtaque.recogerPowerUp(casilla);
					Color[] fondoCasilla = new Color[] { new Color(200, 200, 200, 200), new Color(200, 200, 200, 200) };
					this.tablero.pintarCasilla(this.getCasillaMarcada()[0], this.getCasillaMarcada()[1], fondoCasilla);
				}
			}
			else {
				int opc = recogerOro();
				if(opc == 0) {
					tropaAtaque.recogerOro(casilla);
					Color[] fondoCasilla = new Color[]{new Color(200, 200, 200, 200), new Color(200, 200, 200, 200)};
					this.tablero.pintarCasilla(this.getCasillaMarcada()[0], this.getCasillaMarcada()[1], fondoCasilla);
				}
			}
		}
	}
}
