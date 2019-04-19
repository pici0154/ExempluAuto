package Domain;

public class Masina {
    private String model;
    private double pret;
// creare constructor pentru clasa Masina
    public Masina(String model, double pret) {
        this.model = model;
        this.pret = pret;
    }

    public String getModel() {
        return this.model;
    }

    public double getPret() {
        return this.pret;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public String toString() {
        return "Model:" + this.getModel() + "Pret de baza:" + this.pret;
    }
}
