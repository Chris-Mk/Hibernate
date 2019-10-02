package mostwanted.domain.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "races")
public class Race extends BaseEntity{

    private int laps;
    private District district;
    private List<RaceEntry> entries;

    @Column(nullable = false)
    public int getLaps() {
        return laps;
    }

    public void setLaps(int laps) {
        this.laps = laps;
    }

    @ManyToOne
    @JoinColumn(name = "district_id", referencedColumnName = "id")
    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    @OneToMany(mappedBy = "race")
    public List<RaceEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<RaceEntry> entries) {
        this.entries = entries;
    }
}
