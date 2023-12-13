package bdapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.util.Objects;

@Entity
@Table(name = "process")
public class Process {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "process_id", nullable = false)
    private int id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "material_id")
    private Material material;
    @Min(value = 1,message = "Минимальное значение 1")
    @Column(name = "material_quantity", nullable = true)
    private int materialQuantity;
    @NotNull(message = "Не должно быть пустым")
    @NotEmpty(message = "Не должно быть пустым")
    @Length(max = 30,message ="Максимальная длина 30" )
    @Pattern(regexp = "[А-ЯЁ]+[. А-ЯЁа-яё0-9]*",message = "Название компании должно содержать минимум две буквы и начинаться с заглавной буквы")
    @Column(name = "name", nullable = true, length = 30)
    private String name;

    @Column(name = "description", nullable = true)
    @NotNull(message = "Не должно быть пустым")
    @NotEmpty(message = "Не должно быть пустым")
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int processId) {
        this.id = processId;
    }

    public Process() {
        product=new Product();
        material=new Material();
    }

    public int getMaterialQuantity() {
        return materialQuantity;
    }

    public void setMaterialQuantity(Integer materialQuantity) {

        if(materialQuantity==null){
            materialQuantity=0;
        }
        this.materialQuantity = materialQuantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        Process process = (Process) o;
        return id == process.id && materialQuantity == process.materialQuantity && Objects.equals(product, process.product) && Objects.equals(material, process.material) && Objects.equals(name, process.name) && Objects.equals(description, process.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, material, materialQuantity, name, description);
    }
}
