package mostwanted.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "race_entries")
public class RaceEntry extends BaseEntity{

    private boolean hasFinished;
    private double finishTime;
    private Car car;
    private Racer racer;
    private Race race;

    @Column
    public boolean isHasFinished() {
        return hasFinished;
    }

    public void setHasFinished(boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    @Column
    public double getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(double finishTime) {
        this.finishTime = finishTime;
    }

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @ManyToOne
    @JoinColumn(name = "racer_id", referencedColumnName = "id")
    public Racer getRacer() {
        return racer;
    }

    public void setRacer(Racer racer) {
        this.racer = racer;
    }

    @ManyToOne
    @JoinColumn(name = "race_id", referencedColumnName = "id")
    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }
}
