package main.Case;

public class Case {
	
	private int Coordx;
	private int Coordy;
	public int getCoordx() {
		return Coordx;
	}
	public void setCoordx(int coordx) {
		Coordx = coordx;
	}
	public int getCoordy() {
		return Coordy;
	}
	public void setCoordy(int coordy) {
		Coordy = coordy;
	}
	public Case(int x, int y) {
		Coordx= x;
		Coordy = y;
	}
}
