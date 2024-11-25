package main.Terrains;

import main.Jeu;
import main.Unite;

public abstract class ProprieteSpeciale extends Terrain{
	private int resistance ;
	public ProprieteSpeciale(String terrain,int prop,int x , int y) {
		super(terrain,x,y);
		this.prop = prop;
	}
	
	public abstract void action();
	protected abstract void verifResistance();
	/**
	 * Indique si une unité est sur cette Proprieté
	 * @return boolean
	 */
	public boolean uniteSurPropriete() {
		for(int i =0; i< Jeu.getCJ().getListUnite().size();i++) {
			Unite u = Jeu.getCJ().getListUnite().get(i);
			if(u.getPosition().getX()==this.x && u.getPosition().getY() == this.y && u.getProp() != prop){
				return true;
			}
		}
		return false;
	}
	/**
	 * Remet à zéro la resistance de la propriété si aucune unite est dessus
	 */
	public void verifCaptureProcessing() {
		if (!uniteSurPropriete()) {
			this.setResistance(20);
		}
	}
	/**
	 * Indique si l'unité se déplace à pied
	 * @return boolean
	 */
	public boolean estUneUniteAPied() {
        Unite unite = Jeu.getCJ().getListUnite().get(0);
        for(int i =0; i< Jeu.getCJ().getListUnite().size();i++) {
            Unite u = Jeu.getCJ().getListUnite().get(i);
            if(u.getPosition().getX()==this.x && u.getPosition().getY() == this.y && u.getProp() != prop){
                unite = u;
            }
        }
        if (unite.getType().getLoco().getMoyen().equals("Pied")) {
            return true;
        }
        return false;
    }
	
	// Liste des getters & setters
	
	public int getProp() {
		return prop;
	}
	public void setProp(int prop) {
		this.prop = prop;
	}
	public int getResistance() {
		return resistance;
	}
	public void setResistance(double d) {
		this.resistance = (int) d;
	}
}
