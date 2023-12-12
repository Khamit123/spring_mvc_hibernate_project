package bdapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "process_stage", schema = "pen_factory")
public class ProcessStage {

    @Column(name = "id", nullable = true)
    @Id
    private int id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "process_id")
    private Process process;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "machine_type_id")
    private MachineType machineType;

    @Column(name = "machine_quantity", nullable = true)
    @Min(value = 1,message = "Минимальное значение 1")
    private int machineQuantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public MachineType getMachineType() {
        return machineType;
    }

    public ProcessStage() {
        machineType=new MachineType();
        process=new Process();
    }

    public void setMachineType(MachineType machineType) {
        this.machineType = machineType;
    }

    public int getMachineQuantity() {
        return machineQuantity;
    }

    public void setMachineQuantity(Integer machineQuantity) {
        if(machineQuantity==null){
            machineQuantity=0;
        }
        this.machineQuantity = machineQuantity;
    }

}
