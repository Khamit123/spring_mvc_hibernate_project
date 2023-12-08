package bdapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "maintenance", schema = "pen_factory", catalog = "")
public class Maintenance {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "maintenance_id", nullable = false)
    private int maintenanceId;
    @NotEmpty
    @Column(name = "date_of_maintenance", nullable = false)
    private String dateOfMaintenance;
    @NotNull
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "staff_id")
    private Staff staffId;
    @NotNull
    @NotEmpty
    @Column(name = "what_done", nullable = true, length = -1)
    private String whatDone;
    @OneToMany(mappedBy = "maintenanceId",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.LAZY)
    List<Machinery> machineryList;
    public int getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(int maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    public String getDateOfMaintenance() {
        if(dateOfMaintenance==null){
            return "";
        }

        return dateOfMaintenance.toString();
    }

    public void setDateOfMaintenance(String dateOfMaintenance) {
        this.dateOfMaintenance = dateOfMaintenance;
    }


    public String getWhatDone() {
        return whatDone;
    }

    public void setWhatDone(String whatDone) {
        this.whatDone = whatDone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Maintenance that = (Maintenance) o;

        if (maintenanceId != that.maintenanceId) return false;
        if (dateOfMaintenance != null ? !dateOfMaintenance.equals(that.dateOfMaintenance) : that.dateOfMaintenance != null)
            return false;
        if (staffId != null ? !staffId.equals(that.staffId) : that.staffId != null) return false;
        if (whatDone != null ? !whatDone.equals(that.whatDone) : that.whatDone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = maintenanceId;
        result = 31 * result + (dateOfMaintenance != null ? dateOfMaintenance.hashCode() : 0);
        result = 31 * result + (staffId != null ? staffId.hashCode() : 0);
        result = 31 * result + (whatDone != null ? whatDone.hashCode() : 0);
        return result;
    }
}
