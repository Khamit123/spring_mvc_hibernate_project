package bdapp.model;

import jakarta.persistence.*;

@Entity
public class Process {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "process_id", nullable = false)
    private Integer processId;

    @Column(name = "product_id", nullable = true)
    private Integer productId;

    @Column(name = "material_id", nullable = true)
    private Integer materialId;

    @Column(name = "material_quantity", nullable = true)
    private Integer materialQuantity;

    @Column(name = "name", nullable = true, length = 30)
    private String name;

    @Column(name = "description", nullable = true, length = -1)
    private String description;

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public Integer getMaterialQuantity() {
        return materialQuantity;
    }

    public void setMaterialQuantity(Integer materialQuantity) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Process process = (Process) o;

        if (processId != null ? !processId.equals(process.processId) : process.processId != null) return false;
        if (productId != null ? !productId.equals(process.productId) : process.productId != null) return false;
        if (materialId != null ? !materialId.equals(process.materialId) : process.materialId != null) return false;
        if (materialQuantity != null ? !materialQuantity.equals(process.materialQuantity) : process.materialQuantity != null)
            return false;
        if (name != null ? !name.equals(process.name) : process.name != null) return false;
        if (description != null ? !description.equals(process.description) : process.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = processId != null ? processId.hashCode() : 0;
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (materialId != null ? materialId.hashCode() : 0);
        result = 31 * result + (materialQuantity != null ? materialQuantity.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
