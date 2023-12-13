package bdapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@Table(name = "machine_status", schema = "pen_factory", catalog = "")
public class MachineStatus {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "machine_status_id", nullable = false)
    private int machineStatusId;

    @NotNull(message = "Не должно быть пустым")
    @NotEmpty(message = "Не должно быть пустым")
    @Pattern(regexp = "[А-ЯЁ]+[. А-ЯЁа-яё0-9]*",message = "Имя должно содержать минимум две буквы и начинаться с заглавной буквы")
    @Column(name = "name", nullable = true, length = 30)
    @Length(max = 30,message ="Максимальная длина 30" )
    private String name;

    @NotNull(message = "Не должно быть пустым")
    @NotEmpty(message = "Не должно быть пустым")
    @Column(name = "description", nullable = true, length = -1)
    @Pattern(regexp = "[А-ЯЁ]+[. А-ЯЁа-яё0-9]*",message = "Описание должно содержать минимум два символа и начинаться с заглавной буквы")
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
