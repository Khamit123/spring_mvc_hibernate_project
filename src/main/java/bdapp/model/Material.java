package bdapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Objects;

@Entity
@Table(name = "material")
public class Material {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "material_id", nullable = false)
    private int materialId;

    @Column(name = "name")
    @Pattern(regexp = "[А-ЯЁ][а-яё]+( )?[а-яё]*",message = "Название материала должно содержать минимум две буквы и начинаться с заглавной буквы")
    private String name;

    @Column(name = "units_of_measurement")
    private String unitsOfMeasurement;
    @Column(name = "price")
    @Min(value = 1,message = "Минимальное значение 1")
    private int price;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        if(price==null){
            price=0;
        }
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Material material = (Material) o;
        return materialId == material.materialId && price == material.price && Objects.equals(name, material.name) && Objects.equals(unitsOfMeasurement, material.unitsOfMeasurement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(materialId, name, unitsOfMeasurement, price);
    }
}
