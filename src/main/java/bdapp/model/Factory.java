package bdapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "factory", schema = "pen_factory", catalog = "")
public class Factory {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "factory_id", nullable = false)
    private int factoryId;

    @NotEmpty    @Pattern(regexp = "[А-ЯЁ][а-яё]+[ а-яё0-9]*",message = "Имя должно содержать минимум две буквы и начинаться с заглавной буквы")
    @Column(name = "adress", nullable = true, length = 30)
    private String adress;

    @NotEmpty
    @Pattern(regexp = "[А-ЯЁ][а-яё]+[ а-яё]*",message = "Имя должно содержать минимум две буквы и начинаться с заглавной буквы")
    @Column(name = "name", nullable = true, length = 30)
    private String name;

    @NotNull
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "staff_id")
    private Staff staffId;



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
}
