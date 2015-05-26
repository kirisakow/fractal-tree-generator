package arbreFractal;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Tige {

	private double _xInit;
	public double get_xInit() {return _xInit;}
	private double _yInit;
	public double get_yInit() {return _yInit;}
	private double _xFinal;
	public double get_xFinal() {return _xFinal;}
	private double _yFinal;
	public double get_yFinal() {return _yFinal;}
	private double longueur;
	public double getLongueur() {return longueur;}
	private int rotationDeg;
	public int getRotationDeg() {return rotationDeg;}
	private int numero;
	public int getNumero() {return numero;}
	public int numeroRelAuParent;
	public static ArrayList<Double> collXInit = new ArrayList<Double>();
	public static ArrayList<Double> collYInit = new ArrayList<Double>();
	public static ArrayList<Tige> collTige = new ArrayList<Tige>();
//	* * * * * * * * * * * * * * * * * *
	public static int compteurTigesFaites = 0;
	public static double LONGUEUR_COEFF_REDUC = 0.70; // pour info: 1/(nombre d'or) = 1/1.6180339887 = 6180339887  
	public static int LONGUEUR_INIT_TRONC = 100;
	public static int ROTATION_DEG_TRONC = 0;
	public static int ANGLE_ENF_G = -60;
	public static int ANGLE_ENF_D = +10;
	public static int PROFONDEUR_MAX = 4;
	public static final int SLEEP_TIME_MS = 100;
	public static boolean isAnimated = false;

	public Tige (int rotationDeg) { 
		LONGUEUR_INIT_TRONC = Integer.valueOf(Fenetre.t1.getText());
		LONGUEUR_COEFF_REDUC = Double.valueOf(Fenetre.t2.getText());
//		ROTATION_DEG_TRONC = Integer.valueOf(Fenetre.t3.getText());
//		déjà fait dans Fenetre.planterArbre()
		ANGLE_ENF_G = Integer.valueOf(Fenetre.t4.getText());
		ANGLE_ENF_D = Integer.valueOf(Fenetre.t5.getText());
//		PROFONDEUR_MAX = Integer.valueOf(Fenetre.t6.getText());
//		déjà fait dans Fenetre.resetArbreCompteurEtCollections()
//	* * * * * * * * * * * * * * * * * *		
		Tige.compteurTigesFaites++;
		init(rotationDeg);
		calculerCoordsFinales();
		enregXInitPourEnfants();
		enregYInitPourEnfants();
	}

	private void init(int rotationDeg) {
		this.rotationDeg = rotationDeg;
		this.numero = Tige.compteurTigesFaites - 1;
		if (this.numero == 0) {
			this._xInit = Tige.collXInit.get(numero);
			this._yInit = Tige.collYInit.get(numero);
			this.longueur = LONGUEUR_INIT_TRONC;
		} else {
			this.numeroRelAuParent = (this.numero % 2 == 0) ? 2 : 1;
			int numDuParent = (this.numero - this.numeroRelAuParent)/2;
			this._xInit = Tige.collXInit.get(numDuParent + 1);
			this._yInit = Tige.collYInit.get(numDuParent + 1);
//			this.longueur = LONGUEUR_MAX_INIT * Math.pow(LONGUEUR_COEFF_REDUC, numDuParent+1);
			this.longueur = Tige.collTige.get(numDuParent).getLongueur() * LONGUEUR_COEFF_REDUC;
		}
	}

	private void calculerCoordsFinales() {
		this._xFinal = this._xInit
				+ this.longueur
				* Math.sin(this.rotationDeg / 180. * Math.PI);
		this._yFinal = this._yInit
				- this.longueur
				* Math.cos(this.rotationDeg / 180. * Math.PI);
	}

	private void enregXInitPourEnfants() {
//		if (this.numero % 10 == 0)
//			Tige.augmCapaciteCollection(Tige.collXInit, Tige.collXInit.size()+10);
//		Tige.collXInit.set(this.numero+1, this._xFinal);
		Tige.collXInit.add(this._xFinal);
	}
	
	private void enregYInitPourEnfants() {
//		if (this.numero % 10 == 0)
//			Tige.augmCapaciteCollection(Tige.collYInit, Tige.collYInit.size()+10);
//		Tige.collYInit.set(this.numero+1, this._yFinal);
		Tige.collYInit.add(this._yFinal);
	}
	
	private void enregEnfant(Tige t) {
//		if (position % 10 == 0)
//			Tige.augmCapaciteCollection(Tige.collTige, Tige.collTige.size()+10);
//		Tige.collTige.set(position, t);
		Tige.collTige.add(t);
	}
	
	public void faireEnfant(int rotationDeg, int position) {
		Tige t = new Tige(rotationDeg);
		enregEnfant(t);
	}

	public void tracerTige(Graphics g) {
		int xInit = (int)this._xInit;
		int yInit = (int)this._yInit;
		int xFinal = (int)this._xFinal;
		int yFinal = (int)this._yFinal;
		int deltaX = xFinal - xInit;
		int deltaY = yFinal - yInit;
		g.drawLine(xInit, yInit, xFinal, yFinal);
		
//		new Thread(new Runnable() {
//			public void run() {
//				if (Tige.isAnimated) {
//					g.drawLine(xInit, yInit, xFinal, yFinal);
//				} else {
//					g.drawLine(xInit, yInit, xFinal, yFinal);
//					// try {
//					// Thread.sleep(200);
//					// } catch (InterruptedException e) {
//					// // e.printStackTrace();
//					// }
//				}
//			}
//		}).start();
		
//			do {    } while ((____) < this.longueur);
		
	}
	
	public static void augmCapaciteCollection(ArrayList<?> list, int size) {
	    // Prevent excessive copying while we're adding
	    list.ensureCapacity(size);
	    while (list.size() < size) {
	        list.add(null);
	    }
	    System.out.println(list.size());
	}

}
