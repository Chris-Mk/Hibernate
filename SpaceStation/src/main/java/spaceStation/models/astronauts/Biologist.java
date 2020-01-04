package spaceStation.models.astronauts;

public class Biologist extends BaseAstronaut {
    private static final int INITIAL_OXYGEN_UNITS = 70;
    private static final int DEFAULT_OXYGEN_USAGE = 5;

    public Biologist(String name) {
        super(name, INITIAL_OXYGEN_UNITS);
    }

    @Override
    public void breath() {
        double oxygenUsed = this.getOxygen() - DEFAULT_OXYGEN_USAGE;
        this.setOxygen(oxygenUsed);
    }
}
