package bdapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
@Table(name = "machinery", schema = "pen_factory", catalog = "")
public class Machinery {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "machine_id", nullable = false)
    private int machineId;

    @NotNull
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name="machine_type_id")
    private MachineType machineTypeId;

    @NotNull
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "maintenance_id")
    private Maintenance maintenanceId;

    @NotNull
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "factory_id")
    private Factory factoryId;

    @Column(name = "name", nullable = true, length = 30)
    private String name;

    @NotNull
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "machine_status_id")
    private MachineStatus machineStatusId;

    public Machinery() {
        this.machineTypeId = new MachineType();
        this.maintenanceId = new Maintenance();
        this.factoryId = new Factory();
        this.machineStatusId = new MachineStatus();
    }

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public String getMachineTypeId() {
        return machineTypeId.getName();
    }

    public void setMachineTypeId(String machineTypeId) {
        this.machineTypeId.setName(machineTypeId);
    }

    public Maintenance getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(Maintenance maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    public Factory getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Factory factoryId) {
        this.factoryId = factoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MachineStatus getMachineStatusId() {
        return machineStatusId;
    }

    public void setMachineStatusId(MachineStatus machineStatusId) {
        this.machineStatusId = machineStatusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Machinery machinery = (Machinery) o;
        return machineId == machinery.machineId && machineTypeId.equals(machinery.machineTypeId) && maintenanceId.equals(machinery.maintenanceId) && factoryId.equals(machinery.factoryId) && name.equals(machinery.name) && machineStatusId.equals(machinery.machineStatusId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(machineId, machineTypeId, maintenanceId, factoryId, name, machineStatusId);
    }
}
