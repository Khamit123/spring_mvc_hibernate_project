package bdapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "factory", schema = "pen_factory")
public class Factory {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "factory_id", nullable = false)
    private int factoryId;

    @NotNull(message = "Не должно быть пустым")
    @NotEmpty(message = "Не должно быть пустым")
    @Pattern(regexp = "[А-ЯЁ]+[. А-ЯЁа-яё0-9]*",message = "Адрес должно содержать минимум две буквы и начинаться с заглавной буквы")
    @Column(name = "adress", nullable = true, length = 30)
    @Length(max = 30,message ="Максимальная длина 30" )
    private String adress;

    @NotNull(message = "Не должно быть пустым")
    @NotEmpty(message = "Не должно быть пустым")
    @Pattern(regexp = "[А-ЯЁ]+[. А-ЯЁа-яё0-9]*",message = "Название должно содержать минимум две буквы и начинаться с заглавной буквы")
    @Column(name = "name", nullable = true, length = 30)
    @Length(max = 30,message ="Максимальная длина 30" )
    private String name;

    @NotNull
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "staff_id")
    private Staff staffId;

    public Factory(){
        this.staffId=new Staff();
    }

    public int getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(int factoryId) {
        this.factoryId = factoryId;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Staff getStaffId() {
        return staffId;
    }

    public void setStaffId(Staff staffId) {
        this.staffId = staffId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Factory factory = (Factory) o;
        return factoryId == factory.factoryId && staffId == factory.staffId && adress.equals(factory.adress) && name.equals(factory.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(factoryId, adress, name, staffId);
    }

    transient private int salary;

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
