package Domain;

public class Electrica extends Masina {

    double autonomie;

    public Electrica(String model, double pret, double autonomie) {
        super ( model, pret );
        this.autonomie = autonomie;
    }
    public double getAutonomie() {
        return autonomie;
    }

    public void setAutonomie(double autonomie) {
        this.autonomie = autonomie;
    }

    public String toString() {
        return "Model:" + this.getModel() +"Electrica; " + "Pret :" + this.getPret ();
    }


}
