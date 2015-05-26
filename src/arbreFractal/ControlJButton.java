package arbreFractal;

import java.awt.event.*;
import javax.swing.*;

public final class ControlJButton extends JButton {
	public ControlJButton(String s) {
		super(s);
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Fenetre.planterArbreAuMilieu();
			}
		});
	}
}
