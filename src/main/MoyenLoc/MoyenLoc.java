package main.MoyenLoc;

import java.util.List;

import main.Coordonnees;
import main.Unite;

public abstract class MoyenLoc {
    private String moyen;

    public abstract boolean traversable(String terrain,Coordonnees casesuivante, List<Unite> listUnite,int nous,Unite unitemoving);

    public String getMoyen() {
        return moyen;
    }
    public void setMoyen(String string) {
        this.moyen = string;
    }
}