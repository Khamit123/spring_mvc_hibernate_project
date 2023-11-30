package bdapp.model;

import jakarta.persistence.*;
import org.hibernate.mapping.ToOne;

import java.util.Objects;

@Entity
@Table(name = "composition_of_product")
@IdClass(CompositionOfProductPK.class)
public class CompositionOfProduct {

    @Column(name = "product_id")
    @Id
    private int product_id;


    @Id
    @ManyToOne
    @JoinColumn(name = "material_id")
    private Material material_id;
    @Column(name = "material_quntity")
    private Integer materialQuntity;

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

//    public int getMaterial_id() {
//        return material_id;
//    }
//
//    public void setMaterial_id(int material_id) {
//        this.material_id = material_id;
//    }


    public Material getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(Material material_id) {
        this.material_id = material_id;
    }

    public Integer getMaterialQuntity() {
        return materialQuntity;
    }

    public void setMaterialQuntity(Integer materialQuntity) {
        this.materialQuntity = materialQuntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositionOfProduct that = (CompositionOfProduct) o;
        return product_id == that.product_id && Objects.equals(material_id, that.material_id) && Objects.equals(materialQuntity, that.materialQuntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product_id, material_id, materialQuntity);
    }
}
