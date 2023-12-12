package bdapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
@Entity
@Table(name = "manufacture", schema = "pen_factory", catalog = "")
public class Manufacture {
    @Id
    @Column(name = "manufacture_id", nullable = false)
    @NotEmpty
    private int id;

    @Column(name = "date_of_production")
    @NotEmpty(message = "Не должно быть пустым")
    private String date;

    @NotNull
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "factory_id")
    private Factory factory;

    @NotNull
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "product_quantity")
    @Min(value = 1,message = "Минимальное значение 1")
    private int productQuantity;

    public Manufacture() {
        this.product=new Product();
        this.factory=new Factory();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int product_quantity) {
        this.productQuantity = product_quantity;
    }
}
