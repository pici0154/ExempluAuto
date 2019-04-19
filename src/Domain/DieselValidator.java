package Domain;

//Implementeaza IValidator de tip Diesel ( face validari pt obiecte diesel)
public class DieselValidator implements  IValidator<Diesel> {

    public DieselValidator() {
    }

    public void validate(Diesel diesel) {
        String errors = "";
        if (diesel.getConsum () < 0) {
            errors = errors + "The consum must be > 0!\n";
        }

        if (diesel.getPret() < 0) {
            errors = errors + "The price must be > 0!\n";
        }
    }


}
