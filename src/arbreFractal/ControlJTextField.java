package arbreFractal;

import java.awt.event.*;
import javax.swing.*;

public class ControlJTextField extends JTextField {
	public ControlJTextField(String s) {
		super(s);
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Fenetre.planterArbreAuMilieu();
			}

			private void requestFocusInWindow() {
				this.requestFocusInWindow();
			}
		});
	}
	
	public void setAngleOppose(ControlJTextField t4) {
		this.setText(String.valueOf((-1 * Integer.valueOf(t4.getText())) % 180));
	}

	public void setAngleAligned(ControlJTextField t4) {
		this.setText(String.valueOf((180 + Integer.valueOf(t4.getText())) % 180));		
	}
}
