package bdapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "material")
public class Material {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "material_id", nullable = false)
    private int materialId;

    @Column(name = "name", nullable = true, length = 30)
    @Pattern(regexp = "[А-ЯЁ].[а-яё]+",message = "Название отдела должно содержать минимум две буквы и начинаться с заглавной буквы")
    private String name;

    @Column(name = "units_of_measurement", nullable = true, length = 3)
    @Pattern(regexp = "\\d (кг||мл)",message = "Единицы измерения должны быть подобны ? кг/мл(?-любое число")
    private String unitsOfMeasurement;
    @Column(name = "price")
    @NotNull
    @Min(1)
    private Integer price;

//    @ManyToOne
//    private CompositionOfProduct compositionOfProduct;

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitsOfMeasurement() {
        return unitsOfMeasurement;
    }

    public void setUnitsOfMeasurement(String unitsOfMeasurement) {
        this.unitsOfMeasurement = unitsOfMeasurement;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Material material = (Material) o;

        if (materialId != material.materialId) return false;
        if (name != null ? !name.equals(material.name) : material.name != null) return false;
        if (unitsOfMeasurement != null ? !unitsOfMeasurement.equals(material.unitsOfMeasurement) : material.unitsOfMeasurement != null)
            return false;
        if (price != null ? !price.equals(material.price) : material.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = materialId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (unitsOfMeasurement != null ? unitsOfMeasurement.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
