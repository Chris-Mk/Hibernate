package spaceStation.models.astronauts;

import spaceStation.models.bags.Backpack;
import spaceStation.models.bags.Bag;

import java.text.DecimalFormat;

import static spaceStation.common.ExceptionMessages.*;

public abstract class BaseAstronaut implements Astronaut {
    private static final int DEFAULT_OXYGEN_USAGE = 10;

    private String name;
    private double oxygen;
    private Bag bag;

    protected BaseAstronaut(String name, double oxygen) {
        this.setName(name);
        this.setOxygen(oxygen);
        this.bag = new Backpack();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getOxygen() {
        return this.oxygen;
    }

    @Override
    public boolean canBreath() {
        return this.getOxygen() > 0;
    }

    @Override
    public Bag getBag() {
        return this.bag;
    }

    @Override
    public void breath() {
        double oxygenUsed = this.getOxygen() - DEFAULT_OXYGEN_USAGE;
        this.setOxygen(Math.max(0, oxygenUsed));
    }

    @Override
    public String toString() {
        return "Name: " + this.getName()
                + System.lineSeparator()
                + "Oxygen: " + new DecimalFormat().format(this.getOxygen())
                + System.lineSeparator()
                + "Bag items: " + (this.getBag().getItems().isEmpty() ? "none" :
                        String.join(", ", this.getBag().getItems()));

    }

    protected void setOxygen(double oxygen) {
        if (oxygen < 0) {
            throw new IllegalArgumentException(ASTRONAUT_OXYGEN_LESS_THAN_ZERO);
        }
        this.oxygen = oxygen;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ASTRONAUT_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }
}
