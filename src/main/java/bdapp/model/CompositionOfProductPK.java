package bdapp.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@IdClass(CompositionOfProductPK.class)
public class CompositionOfProductPK implements Serializable {

    public CompositionOfProductPK() {
        this.product =new Product();
        this.material = new Material();
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Id
    @ManyToOne
    @JoinColumn(name = "material_id")
    private Material material;


    public CompositionOfProductPK(Product product, Material material) {

        this.product = product;
        this.material = material;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositionOfProductPK that = (CompositionOfProductPK) o;
        return Objects.equals(product, that.product) && Objects.equals(material, that.material);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, material);
    }
}
