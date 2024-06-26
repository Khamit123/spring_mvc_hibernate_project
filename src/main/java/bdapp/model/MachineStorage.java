package bdapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "machine_storage", schema = "pen_factory")
public class MachineStorage {
    @Id
    @Column(name = "storage_name", nullable = false)
    @NotNull(message = "Не должно быть пустым")
    @NotEmpty(message = "Не должно быть пустым")
    @Pattern(regexp = "[А-ЯЁ][а-яё]+[ а-яё]*",message = "Должно содержать минимум два символа и начинаться с заглавной буквы")
    @Length(max = 45,message ="Максимальная длина 45" )
    private String name;

    @NotNull(message = "Не должно быть пустым")
    @NotEmpty(message = "Не должно быть пустым")
    @Column(name = "adress")
    @Length(max = 45,message ="Максимальная длина 45" )
    @Pattern(regexp = "[А-ЯЁ]+[. А-ЯЁа-яё0-9]*",message = "Должно содержать минимум два символа и начинаться с заглавной буквы")
    private String adress;


    @NotNull
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "machine_id")
    private Machinery machineId;

    public MachineStorage(String storageName, String adress, Machinery machineId) {
        this.name = storageName;
        this.adress = adress;
        this.machineId = machineId;
    }

    public MachineStorage() {
        this.machineId = new Machinery();
    }

    public String getName() {
        return name;
    }

    public void setName(String storageName) {
        this.name = storageName;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Machinery getMachineId() {
        return machineId;
    }

    public void setMachineId(Machinery machineId) {
        this.machineId = machineId;
    }
}
