package bdapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import org.hibernate.mapping.ToOne;

import java.util.Objects;

@Entity
@Table(name = "composition_of_product")
@IdClass(value =CompositionOfProductPK.class)
public class CompositionOfProduct {


    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    @Id
    @ManyToOne
    @JoinColumn(name = "material_id")
    private Material material;
    @Column(name = "material_quntity")
    @Min(1)
    private int materialQuntity;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    //    public int getProduct_id() {
//        return product_id;
//    }
//
//    public void setProduct_id(int product_id) {
//        this.product_id = product_id;
//    }

//    public int getMaterial_id() {
//        return material_id;
//    }
//
//    public void setMaterial_id(int material_id) {
//        this.material_id = material_id;
//    }


    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Integer getMaterialQuntity() {
        return materialQuntity;
    }

    public void setMaterialQuntity(Integer materialQuntity) {

        if(materialQuntity==null){
            materialQuntity=0;
        }
        this.materialQuntity = materialQuntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositionOfProduct that = (CompositionOfProduct) o;
        return Objects.equals(product, that.product) && Objects.equals(material, that.material) && Objects.equals(materialQuntity, that.materialQuntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, material, materialQuntity);
    }

    public CompositionOfProduct(Product product, Material material, int materialQuntity) {
        this.product = product;
        this.material = material;
        this.materialQuntity = materialQuntity;
    }

    public CompositionOfProduct() {
        this.product =new Product();
        this.material = new Material();

    }

    public CompositionOfProduct(Product product, Material material) {
        this.product = product;
        this.material = material;
    }
}
