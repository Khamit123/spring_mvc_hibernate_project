package bdapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Objects;

@Entity
@Table(name = "material_storage", schema = "pen_factory")
public class MaterialStorage {
    @Id
    @Column(name = "storage_name", nullable = false)
    @NotEmpty
    @Pattern(regexp = "[А-ЯЁ][а-яё]+[ а-яё]*",message = "Должно содержать минимум два символа и начинаться с заглавной буквы")
    private String name;
    @NotEmpty
    @Column(name = "adress")
    @Pattern(regexp = "[А-ЯЁ][а-яё]+[. А-ЯЁа-яё0-9]*",message = "Должно содержать минимум два символа и начинаться с заглавной буквы")
    private String adress;


    @NotNull
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "material_id")
    private Material materialId;

    @Min(value = 1,message = "Должно быть больше 0")
    @Column(name = "material_quantity")
    private int quantity;

    public MaterialStorage() {
        this.materialId =new Material();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Material getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Material materialId) {
        this.materialId = materialId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {

        if(quantity==null){
            quantity=0;
        }
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaterialStorage that = (MaterialStorage) o;
        return quantity == that.quantity && Objects.equals(name, that.name) && Objects.equals(adress, that.adress) && Objects.equals(materialId, that.materialId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, adress, materialId, quantity);
    }

}
