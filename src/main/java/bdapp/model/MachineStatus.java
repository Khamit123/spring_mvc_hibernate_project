package bdapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Entity
@Table(name = "machine_status", schema = "pen_factory", catalog = "")
public class MachineStatus {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "machine_status_id", nullable = false)
    private int machineStatusId;

    @NotEmpty
    @Column(name = "name", nullable = true, length = 30)
    private String name;

    @NotEmpty
    @Column(name = "description", nullable = true, length = -1)
    private String description;

    public int getMachineStatusId() {
        return machineStatusId;
    }

    public void setMachineStatusId(int machineStatusId) {
        this.machineStatusId = machineStatusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MachineStatus that = (MachineStatus) o;

        if (machineStatusId != that.machineStatusId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = machineStatusId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
