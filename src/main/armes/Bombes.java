package main.armes;

public class Bombes extends Arme{
	public Bombes() {
		super();
		this.degats.put("Infanterie",100.0);
		this.degats.put("Bazooka",100.0);
		this.degats.put("Tank",100.0);
		this.degats.put("DCA",70.0);
		this.degats.put("Helicoptere",0.0);
		this.degats.put("Bombardier",0.0);
		this.degats.put("Convoi",100.0);
		// Bonus mais implanté pour ne pas causé de bug
		this.degats.put("Artillerie",50.0);
	}
	@Override
	public String toString() {
		return "Type d'arme: "+ this.getClass() + "Inflige : [I:100;Ba:100;T:100;DCA:70;H:0;Bo:0;C:100]";
	}
}
