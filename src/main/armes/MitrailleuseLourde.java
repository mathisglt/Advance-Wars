package main.armes;

public class MitrailleuseLourde extends Arme {

	public MitrailleuseLourde() {
		super();
		this.degats.put("Infanterie",100.0);
		this.degats.put("Bazooka",80.0);
		this.degats.put("Tank",35.0);
		this.degats.put("DCA",30.0);
		this.degats.put("Helicoptere",110.0);
		this.degats.put("Bombardier",70.0);
		this.degats.put("Convoi",50.0);
		// Bonus mais implanté pour ne pas causé de bug
		this.degats.put("Artillerie",50.0);
	}

}
