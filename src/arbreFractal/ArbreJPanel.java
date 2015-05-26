package arbreFractal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ArbreJPanel extends JPanel {
	
	public boolean peutCommencerADessiner = false;

//	public ArbreJPanel(Color bgColor, Dimension size) {
//		this.setBackground(bgColor);
//		this.setPreferredSize(size);
//	}
	
	public ArbreJPanel() { }
	
	public void paintComponent(Graphics g) {
		if (Fenetre.arbre.peutCommencerADessiner == true) {
			int xInit, yInit, xFinal, yFinal;
			Tige tige;
			g.setColor(Color.black);
			for (int i = 0; i < Tige.compteurTigesFaites; i++) {
				tige = Tige.collTige.get(i);
				xInit = (int) tige.get_xInit();
				yInit = (int) tige.get_yInit();
				xFinal = (int) tige.get_xFinal();
				yFinal = (int) tige.get_yFinal();
				g.drawLine(xInit, yInit, xFinal, yFinal);
//				tige.tracerTige(g);
			}
			Fenetre.controlMain.setVisible(true);
		}
	}
	
	public void forcerRepaint() {
		if (this.peutCommencerADessiner == false) {
			this.peutCommencerADessiner = true;
		}
		this.repaint();
		this.peutCommencerADessiner = false;
	}
}
