package Domain;


public class Diesel extends Masina {
    private double consum;

    // creare constructor pentru masina de tip Diesel
    public Diesel(String model, double pret, double consum) {
        super(model, pret);
        this.consum = consum;
    }

    public double getConsum() {
        return this.consum;
    }

    public void setConsum(double consum) {
        this.consum = consum;
    }

    public String toString() {
        return "Model: " + this.getModel() + " Diesel;" + " Pret: " + this.getPret();
    }
}
