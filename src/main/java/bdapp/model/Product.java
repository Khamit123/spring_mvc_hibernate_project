package bdapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

import java.util.Objects;

@Entity
@Table(name = "product")
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "product_id")
    private int productId;
    @Pattern(regexp = "[А-ЯЁ].[а-яё]+( )?[а-яё]*",message = "Название продукта должно содержать минимум две буквы и начинаться с заглавной буквы")
    @Column(name = "name")
    private String name;

    @Column(name = "price")
    @Min(1)
    private int price;

    @Column(name = "color")
    private String color;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }
    public String getColorName(){
       return name + " "  +getConvColor();
    }

    public void setPrice(Integer price) {
        if(price==null){
            price=0;
        }
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        if(color.equals("Синий")){
            color="b";
        }
        if(color.equals("Красный")){
            color= "r";
        }
        if(color.equals("Зелёный")){
            color= "g";
        }
        if(color.equals("Белый")){
            color= "w";
        }
        this.color = color;
    }
    public String getConvColor(){
        if(color==null){
            return "";
        }
        if(color.equals("b")){
            return "Синий";
        }
        if(color.equals("r")){
            return "Красный";
        }
        if(color.equals("g")){
            return "Зелёный";
        }
        if(color.equals("w")){
            return "Белый";
        }
        return "";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId && price == product.price && Objects.equals(name, product.name) && Objects.equals(color, product.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, name, price, color);
    }

    public Product() {
    }

    public Product(String name, int price, String color) {
        this.name = name;
        this.price = price;
        this.color = color;
    }
}
