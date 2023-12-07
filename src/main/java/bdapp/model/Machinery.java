package bdapp.model;

import jakarta.persistence.*;

@Entity
public class Machinery {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "machine_id", nullable = false)
    private int machineId;
    @Basic
    @Column(name = "machine_type_id", nullable = true)
    private Integer machineTypeId;
    @Basic
    @Column(name = "maintenance_id", nullable = true)
    private Integer maintenanceId;
    @Basic
    @Column(name = "factory_id", nullable = true)
    private Integer factoryId;
    @Basic
    @Column(name = "name", nullable = true, length = 30)
    private String name;
    @Basic
    @Column(name = "machine_status_id", nullable = false)
    private int machineStatusId;

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public Integer getMachineTypeId() {
        return machineTypeId;
    }

    public void setMachineTypeId(Integer machineTypeId) {
        this.machineTypeId = machineTypeId;
    }

    public Integer getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(Integer maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMachineStatusId() {
        return machineStatusId;
    }

    public void setMachineStatusId(int machineStatusId) {
        this.machineStatusId = machineStatusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Machinery machinery = (Machinery) o;

        if (machineId != machinery.machineId) return false;
        if (machineStatusId != machinery.machineStatusId) return false;
        if (machineTypeId != null ? !machineTypeId.equals(machinery.machineTypeId) : machinery.machineTypeId != null)
            return false;
        if (maintenanceId != null ? !maintenanceId.equals(machinery.maintenanceId) : machinery.maintenanceId != null)
            return false;
        if (factoryId != null ? !factoryId.equals(machinery.factoryId) : machinery.factoryId != null) return false;
        if (name != null ? !name.equals(machinery.name) : machinery.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = machineId;
        result = 31 * result + (machineTypeId != null ? machineTypeId.hashCode() : 0);
        result = 31 * result + (maintenanceId != null ? maintenanceId.hashCode() : 0);
        result = 31 * result + (factoryId != null ? factoryId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + machineStatusId;
        return result;
    }
}
