package bdapp.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "process_stage", schema = "pen_factory", catalog = "")
public class ProcessStage {
    @Basic
    @Column(name = "process_id", nullable = true)
    private Integer processId;
    @Basic
    @Column(name = "machine_type_id", nullable = true)
    private Integer machineTypeId;
    @Basic
    @Column(name = "machine_quantity", nullable = true)
    private Integer machineQuantity;

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public Integer getMachineTypeId() {
        return machineTypeId;
    }

    public void setMachineTypeId(Integer machineTypeId) {
        this.machineTypeId = machineTypeId;
    }

    public Integer getMachineQuantity() {
        return machineQuantity;
    }

    public void setMachineQuantity(Integer machineQuantity) {
        this.machineQuantity = machineQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProcessStage that = (ProcessStage) o;

        if (processId != null ? !processId.equals(that.processId) : that.processId != null) return false;
        if (machineTypeId != null ? !machineTypeId.equals(that.machineTypeId) : that.machineTypeId != null)
            return false;
        if (machineQuantity != null ? !machineQuantity.equals(that.machineQuantity) : that.machineQuantity != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = processId != null ? processId.hashCode() : 0;
        result = 31 * result + (machineTypeId != null ? machineTypeId.hashCode() : 0);
        result = 31 * result + (machineQuantity != null ? machineQuantity.hashCode() : 0);
        return result;
    }
}
