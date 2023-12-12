package bdapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


@Entity
@Table(name = "orderord")
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_id", nullable = false)
    private int id;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @NotNull(message = "Не должно быть пустым")
    @NotEmpty(message = "Не должно быть пустым")
    @Column(name = "date_of_order", nullable = true)
    private String dateOfOrder;
    @NotNull(message = "Не должно быть пустым")
    @NotEmpty(message = "Не должно быть пустым")
    @Column(name = "date_of_execution", nullable = true)
    private String dateOfExecution;

    @Column(name = "status", nullable = true, length = 45)
    private String status;

    @Column(name = "delivery_adress", nullable = true, length = 45)
    @Pattern(regexp = "[А-ЯЁ][а-яё]+[. А-ЯЁа-яё0-9]*",message = "Адрес должен содержать минимум две буквы и начинаться с заглавной буквы")
    private String deliveryAdress;

    @Column(name = "price", nullable = true)
    private int price;

    @Min(value = 1,message = "Минимальное значение 1")
    @Column(name = "product_quantity", nullable = true)
    private int productQuantity;

    @Column(name = "date_of_update", nullable = true)
    private String dateOfUpdate;

    @Column(name = "date_of_record", nullable = true)
    private String dateOfRecord;

    public int getId() {
        return id;
    }

    public void setId(int orderId) {
        this.id = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getDateOfOrder() {
        if (dateOfOrder == null) {
            return "";
        }

        return dateOfOrder.toString();
    }

    public void setDateOfOrder(String dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public String getDateOfExecution() {
        if (dateOfExecution == null) {
            return "";
        }

        return dateOfExecution.toString();
    }

    public void setDateOfExecution(String dateOfExecution) {
        this.dateOfExecution = dateOfExecution;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeliveryAdress() {
        return deliveryAdress;
    }

    public void setDeliveryAdress(String deliveryAdress) {
        this.deliveryAdress = deliveryAdress;
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

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        if(productQuantity==null){
            productQuantity=0;
        }
        this.productQuantity = productQuantity;
    }

    public String getDateOfUpdate() {
        if (dateOfUpdate == null) {
            return "";
        }

        return dateOfUpdate.toString();

    }

    public void setDateOfUpdate(String dateOfUpdate) {
        this.dateOfUpdate = dateOfUpdate;
    }

    public String getDateOfRecord() {
        if (dateOfRecord == null) {
            return "";
        }

        return dateOfRecord.toString();
    }

    public void setDateOfRecord(String dateOfRecord) {
        this.dateOfRecord = dateOfRecord;
    }

    public Order() {
        customer = new Customer();
        product = new Product();
    }
}
