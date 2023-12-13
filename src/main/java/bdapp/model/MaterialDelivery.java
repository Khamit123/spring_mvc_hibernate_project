package bdapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;


@Entity
@Table(name = "material_delivery", schema = "pen_factory")
public class MaterialDelivery {
    @ManyToOne
    @JoinColumn(name = "delivery_company_id")
    private DeliveryCompany deliveryCompany;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private Material material;

    @Column(name = "material_quantity", nullable = true)
    @Min(value = 1,message = "Минимальное значение 1")
    private int materialQuantity;

    @Column(name = "price", nullable = true)
    @Min(value = 1,message = "Минимальное значение 1")
    private int price;

    @Column(name = "order_status", nullable = true, length = 10)
    @Length(max=10,message = "Должно содержать максиму 10 символов")
    @Pattern(regexp = "[А-ЯЁ]+[. А-ЯЁа-яё0-9]*",message = "Статус должен содержать минимум две буквы и начинаться с заглавной буквы")
    private String orderStatus;

    @Column(name = "delivery_date", nullable = true)
    @NotNull(message = "Не должно быть пустым")
    @NotEmpty(message = "Не должно быть пустым")
    private String deliveryDate;

    @Column(name = "date_of_order", nullable = true)
    @NotNull(message = "Не должно быть пустым")
    @NotEmpty(message = "Не должно быть пустым")
    private String dateOfOrder;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "delivery_order_id", nullable = false)
    private int id;


    public int getMaterialQuantity() {
        return materialQuantity;
    }

    public void setMaterialQuantity(Integer materialQuantity) {

        if(materialQuantity==null){
            materialQuantity=0;
        }
        this.materialQuantity = materialQuantity;

    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getDeliveryDate() {
        if (deliveryDate == null) {
            return "";
        }
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDateOfOrder() {

        if (dateOfOrder == null) {
            return "";
        }

        return dateOfOrder;
    }

    public void setDateOfOrder(String dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int deliveryOrderId) {
        this.id = deliveryOrderId;
    }

    public DeliveryCompany getDeliveryCompany() {
        return deliveryCompany;
    }

    public void setDeliveryCompany(DeliveryCompany deliveryCompany) {
        this.deliveryCompany = deliveryCompany;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public MaterialDelivery() {
        deliveryCompany=new DeliveryCompany();
        material=new Material();
    }
}
