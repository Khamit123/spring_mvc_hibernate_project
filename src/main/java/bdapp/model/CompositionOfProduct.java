package bdapp.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "composition_of_product")
public class CompositionOfProduct {

    @Column(name = "product_id", nullable = false)
    private int productId;

    @Column(name = "material_id", nullable = false)
    private int materialId;
    @Column(name = "material_quntity", nullable = true)
    private Integer materialQuntity;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
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

        if (productId != that.productId) return false;
        if (materialId != that.materialId) return false;
        if (materialQuntity != null ? !materialQuntity.equals(that.materialQuntity) : that.materialQuntity != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productId;
        result = 31 * result + materialId;
        result = 31 * result + (materialQuntity != null ? materialQuntity.hashCode() : 0);
        return result;
    }
}
