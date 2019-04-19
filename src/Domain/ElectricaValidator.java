package Domain;

public class ElectricaValidator implements  IValidator<Electrica> {
    @Override
    public void validate(Electrica var1) {
        String errors = "";
        if (var1.getAutonomie () < 0) {
            errors = errors + "The autonomie must be > 0!\n";
        }

        if (var1.getPret() < 0) {
            errors = errors + "The price must be > 0!\n";
        }
    }
}
