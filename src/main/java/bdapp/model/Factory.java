package bdapp.model;

import jakarta.persistence.*;

@Entity
public class Factory {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "factory_id", nullable = false)
    private int factoryId;
    @Basic
    @Column(name = "adress", nullable = true, length = 30)
    private String adress;
    @Basic
    @Column(name = "name", nullable = true, length = 30)
    private String name;
    @Basic
    @Column(name = "staff_id", nullable = true)
    private Integer staffId;

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

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Factory factory = (Factory) o;

        if (factoryId != factory.factoryId) return false;
        if (adress != null ? !adress.equals(factory.adress) : factory.adress != null) return false;
        if (name != null ? !name.equals(factory.name) : factory.name != null) return false;
        if (staffId != null ? !staffId.equals(factory.staffId) : factory.staffId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = factoryId;
        result = 31 * result + (adress != null ? adress.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (staffId != null ? staffId.hashCode() : 0);
        return result;
    }
}
