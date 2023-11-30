package bdapp.model;

import jakarta.persistence.*;

import java.io.Serializable;


@Embeddable
public class CompositionOfProductPK implements Serializable {


    private int product_id;


    private int material_id;


    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }


    public int getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(int material_id) {
        this.material_id = material_id;
    }


}
