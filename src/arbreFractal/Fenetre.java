package arbreFractal;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Fenetre extends JFrame {

	public static JFrame f;
	public static ArbreJPanel arbre;
	public static JPanel mainContainer, controlInputLabels, controlInputFields, angleCheckBoxes, controlMain;
	public static JButton bouton;
	public static ControlJTextField t1, t2, t3, t4, t5, t6;
	public static JCheckBox isAngleEnfSymm, isAngleAligned, isAnimated;
	
	public static void main(String[] args) {
		mainContainer = new JPanel(new BorderLayout());
		controlInputLabels = new JPanel(new GridLayout(1,8));
		controlInputFields = new JPanel(new GridLayout(1,8));
		controlMain = new JPanel(new GridLayout(3,1));
		arbre = new ArbreJPanel();
		f = new Fenetre();
		Fenetre.planterArbreAuMilieu();
	}

	public Fenetre() {
		this.setControlInputLabels();
		this.setControlInputComponents();
		this.setContainersAdjustAndMakeVisible();
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	Fenetre.planterArbreAuMilieu();
            }
        });
	}
	
	private void setContainersAdjustAndMakeVisible() {		
		controlMain.add(controlInputLabels);
		controlMain.add(controlInputFields);
		controlMain.add(bouton);
//		container.setBackground(Color.white);
		mainContainer.add(arbre, BorderLayout.CENTER);
//		bouton.setEnabled(false);
		mainContainer.add(controlMain, BorderLayout.SOUTH);
		this.setContentPane(mainContainer);
		this.setTitle("Arbre fractale");
		this.setSize(new Dimension(700,600));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);		
	}

	private void setControlInputComponents() {
		bouton = new ControlJButton("Redessiner");
		controlInputFields.add(t1 = new ControlJTextField(String.valueOf(Tige.LONGUEUR_INIT_TRONC)));
		controlInputFields.add(t2 = new ControlJTextField(String.valueOf(Tige.LONGUEUR_COEFF_REDUC)));
		controlInputFields.add(t3 = new ControlJTextField(String.valueOf(Tige.ROTATION_DEG_TRONC)));
		controlInputFields.add(t4 = new ControlJTextField(String.valueOf(Tige.ANGLE_ENF_G)));
		t4.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent keyEvent) { }
			public void keyReleased(KeyEvent keyEvent) {
				if (isAngleEnfSymm.isSelected() && isAngleAligned.isSelected()) {
					isAngleEnfSymm.setSelected(false);
					isAngleAligned.setSelected(false);
				}
				if (isAngleEnfSymm.isSelected()) {
					try {
						t5.setAngleOppose(Fenetre.t4);
					} catch (Exception e) { }
				}
				if (isAngleAligned.isSelected()) {
					try {
						t5.setAngleAligned(Fenetre.t4);
					} catch (Exception e) { }
				}
			}
			public void keyTyped(KeyEvent keyEvent) {
				if (isAngleEnfSymm.isSelected() && isAngleAligned.isSelected()) {
					isAngleEnfSymm.setSelected(false);
					isAngleAligned.setSelected(false);
				}
				if (isAngleEnfSymm.isSelected()) {
					try {
						t5.setAngleOppose(Fenetre.t4);
					} catch (Exception e) { }
				}
				if (isAngleAligned.isSelected()) {
					try {
						t5.setAngleAligned(Fenetre.t4);
					} catch (Exception e) { }
				}
			}
		});
		JPanel angleCheckBoxes = new JPanel();
		angleCheckBoxes.add(isAngleEnfSymm = new JCheckBox("", false));
		angleCheckBoxes.add(isAngleAligned = new JCheckBox("", false));
		controlInputFields.add(angleCheckBoxes);
		isAngleEnfSymm.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			    if (e.getStateChange() == ItemEvent.SELECTED) {
			    	Fenetre.t5.setEditable(false);
			    	if(isAngleAligned.isSelected()) {
			    		Fenetre.t4.setText("-90");
			    		Fenetre.t5.setText("90");
			    	} else {
				    	Fenetre.t5.setAngleOppose(Fenetre.t4);			    		
			    	}
					Fenetre.planterArbreAuMilieu();
			    } else {
			    	Fenetre.t5.setEditable(true);
			    }
			}
		});
		isAngleAligned.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			    if (e.getStateChange() == ItemEvent.SELECTED) {
			    	Fenetre.t5.setEditable(false);
			    	if(isAngleEnfSymm.isSelected()) {
			    		Fenetre.t4.setText("-90");
			    		Fenetre.t5.setText("90");			    		
			    	} else {
				    	t5.setAngleAligned(Fenetre.t4);			    		
			    	}
					Fenetre.planterArbreAuMilieu();
			    } else {
			    	Fenetre.t5.setEditable(true);
			    }
			}
		});
		controlInputFields.add(t5 = new ControlJTextField(String.valueOf(Tige.ANGLE_ENF_D)));
		controlInputFields.add(t6 = new ControlJTextField(String.valueOf(Tige.PROFONDEUR_MAX)));
//		controlInputFields.add(t7 = new ControlJTextField(String.valueOf(Tige.SLEEP_TIME_MS)));
		controlInputFields.add(isAnimated = new JCheckBox("", false));
		isAnimated.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			    if (e.getStateChange() == ItemEvent.SELECTED) {
			    	if (!Tige.isAnimated) {
			    		Tige.isAnimated = true;
			    		Fenetre.planterArbreAuMilieu();
			    	}
			    } else {
			    	if (Tige.isAnimated) {
			    		Tige.isAnimated = false;
			    	}
			    }
			}
		});
	}

	private void setControlInputLabels() {
		JPanel label1 = new JPanel(new GridLayout(1,2));
		label1.add(new JLabel("Longueur"));
		label1.add(new JLabel("tronc"));
		controlInputLabels.add(label1);
		JPanel label2 = new JPanel(new GridLayout(1,2));
		label2.add(new JLabel("Coefficient"));
		label2.add(new JLabel("réducteur"));
		controlInputLabels.add(label2);
		JPanel label3 = new JPanel(new GridLayout(1,2));
		label3.add(new JLabel("Rotation(°)"));
		label3.add(new JLabel("tronc"));
		controlInputLabels.add(label3);
		JPanel label4 = new JPanel(new GridLayout(1,2));
		label4.add(new JLabel("Rotation(°)"));
		label4.add(new JLabel("enfant gauche"));
		controlInputLabels.add(label4);
		JPanel label5 = new JPanel(new GridLayout(1,2));
		label5.add(new JLabel("Symmétrie"));
		label5.add(new JLabel("| Aligner ?"));
		controlInputLabels.add(label5);
		JPanel label6 = new JPanel(new GridLayout(1,2));
		label6.add(new JLabel("Rotation(°)"));
		label6.add(new JLabel("enfant droite"));
		controlInputLabels.add(label6);
		controlInputLabels.add(new JLabel("Profondeur"));
		controlInputLabels.add(new JLabel("Animer ?"));
	}

	public static void planterArbreAuMilieu() {
		Fenetre.planterArbre((f.getWidth()/2), 
				(f.getHeight()/2 + Integer.valueOf(t1.getText())));
	}
		
	private static void planterArbre(double xInit, double yInit) {
		Fenetre.resetArbreCompteurEtCollections();
		int i = 0;
//		Tige.collXInit.set(0, xInit); // plus tard, pour multithreading
		Tige.collXInit.add(xInit);
		Tige.collYInit.add(yInit);
		Tige.collTige.add(new Tige(Tige.ROTATION_DEG_TRONC = 
				Integer.valueOf(Fenetre.t3.getText())));
		Tige tParent;
		do {
			tParent = Tige.collTige.get(i);
			tParent.faireEnfant(tParent.getRotationDeg()
					+ Tige.ANGLE_ENF_G, i+1);
			tParent.faireEnfant(tParent.getRotationDeg()
					+ Tige.ANGLE_ENF_D, i+1);
			i++;
		} while ((1 + Tige.compteurTigesFaites) < Math.pow(2, Tige.PROFONDEUR_MAX));
		

//		int i = 0;
//		do {
//			Tige tParent = Tige.collTige.get(i);
//			new Thread(new Runnable() {
//				public void run() {
//					tParent.faireEnfantDeGauche(tParent.getRotationDeg() + Tige.ANGLE_ENF_G);
//				}
//			}).start();
//			new Thread(new Runnable() {
//				public void run() {
//					tParent.faireEnfantDeDroite(tParent.getRotationDeg() + Tige.ANGLE_ENF_D);
//				}
//			}).start();			
//			i++;
//		} while ((1 + Tige.compteurTigesFaites) < Math.pow(2, Tige.PROFONDEUR_MAX));			
		
		Fenetre.dessinerArbre();
	}
	
	private static void dessinerArbre() {
		controlMain.setVisible(false);
		arbre.peutCommencerADessiner = true;
	}
	
	private static void resetArbreCompteurEtCollections() {
		int profMax = Tige.PROFONDEUR_MAX = Integer.valueOf(Fenetre.t6.getText());
		int nbTigesMax = 12;
//		nbTigesMax = (int) Math.pow(2, profMax);
//		if (nbTigesMax <= (2^20)) {
//			Tige.collXInit = new ArrayList<Double>(nbTigesMax);
//			Tige.collYInit = new ArrayList<Double>(nbTigesMax);
//			Tige.collTige = new ArrayList<Tige>(nbTigesMax);
//		} else {
//			Tige.collXInit = new ArrayList<Double>(2^20);
//			Tige.collYInit = new ArrayList<Double>(2^20);
//			Tige.collTige = new ArrayList<Tige>(2^20);
//		}
		
//		Tige.augmCapaciteCollection(Tige.collXInit = new ArrayList<Double>(), nbTigesMax);
//		Tige.augmCapaciteCollection(Tige.collYInit = new ArrayList<Double>(), nbTigesMax);
//		Tige.augmCapaciteCollection(Tige.collTige = new ArrayList<Tige>(), nbTigesMax);
		
		Tige.collXInit = new ArrayList<Double>();
		Tige.collYInit = new ArrayList<Double>();
		Tige.collTige = new ArrayList<Tige>();
		Tige.compteurTigesFaites = 0;
		arbre.forcerRepaint();
	}

}
