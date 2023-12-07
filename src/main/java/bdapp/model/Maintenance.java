package bdapp.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Maintenance {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "maintenance_id", nullable = false)
    private int maintenanceId;
    @Basic
    @Column(name = "date_of_maintenance", nullable = false)
    private Date dateOfMaintenance;
    @Basic
    @Column(name = "staff_id", nullable = true)
    private Integer staffId;
    @Basic
    @Column(name = "what_done", nullable = true, length = -1)
    private String whatDone;

    public int getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(int maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    public Date getDateOfMaintenance() {
        return dateOfMaintenance;
    }

    public void setDateOfMaintenance(Date dateOfMaintenance) {
        this.dateOfMaintenance = dateOfMaintenance;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
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
