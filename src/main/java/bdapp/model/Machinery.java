package bdapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.util.Objects;

@Entity
@Table(name = "machinery", schema = "pen_factory")
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
    private Maintenance maintenance;

    @NotNull
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "factory_id")
    private Factory factoryId;

    @NotNull(message = "Не должно быть пустым")
    @NotEmpty(message = "Не должно быть пустым")
    @Column(name = "name", nullable = true, length = 30)
    @Pattern(regexp = "[А-ЯЁ]+[. А-ЯЁа-яё0-9]*",message = "Имя должно содержать минимум две буквы и начинаться с заглавной буквы")
    @Length(max = 30,message ="Максимальная длина 30" )
    private String name;

    @NotNull
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "machine_status_id")
    private MachineStatus machineStatusId;

    public Machinery() {
        this.machineTypeId = new MachineType();
        this.maintenance = new Maintenance();
        this.factoryId = new Factory();
        this.machineStatusId = new MachineStatus();
    }

    public Machinery(int machineId, MachineType machineTypeId, Maintenance maintenance, Factory factoryId, String name, MachineStatus machineStatusId) {
        this.machineId = machineId;
        this.machineTypeId = machineTypeId;
        this.maintenance = maintenance;
        this.factoryId = factoryId;
        this.name = name;
        this.machineStatusId = machineStatusId;
    }

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public MachineType getMachineTypeId() {
        return machineTypeId;
    }

    public void setMachineTypeId(MachineType machineTypeId) {
        this.machineTypeId = machineTypeId;
    }

    public Maintenance getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(Maintenance maintenance) {
        this.maintenance = maintenance;
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
        return machineId == machinery.machineId && machineTypeId.equals(machinery.machineTypeId) && maintenance.equals(machinery.maintenance) && factoryId.equals(machinery.factoryId) && name.equals(machinery.name) && machineStatusId.equals(machinery.machineStatusId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(machineId, machineTypeId, maintenance, factoryId, name, machineStatusId);
    }
}
