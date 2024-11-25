package main.armes;

public class Mortier extends Arme{

    public Mortier() {
        super();
        this.degats.put("Infanterie",40.0);
        this.degats.put("Bazooka",50.0);
        this.degats.put("Tank",70.0);
        this.degats.put("DCA",70.0);
        this.degats.put("Helicoptere",0.0);
        this.degats.put("Bombardier",0.0);
        this.degats.put("Convoi",70.0);
        // Bonus mais implanté pour ne pas causé de bug
        this.degats.put("Artillerie",70.0);

    }

}