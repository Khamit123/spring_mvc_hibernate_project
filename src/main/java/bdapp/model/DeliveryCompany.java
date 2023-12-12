package bdapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.util.Objects;

@Entity
@Table(name = "delivery_company", schema = "pen_factory")
public class DeliveryCompany {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "delivery_company_id", nullable = false)
    private int id;
    @Pattern(regexp = "[А-ЯЁ]+[. А-ЯЁа-яё0-9]*",message = "Название компании должно содержать минимум две буквы и начинаться с заглавной буквы")
    @Column(name = "delivery_company_name", nullable = true, length = 30)
    @Length(max = 30,message ="Максимальная длина 30" )
    private String name;

    @NotNull(message = "Не должно быть пустым")
    @NotEmpty(message = "Не должно быть пустым")
    @Pattern(regexp = "([a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z0-9_-]+)",message = "Почта должна соответствовать виду ???@???.??(знак вопроса - любой символ,причем до знака @ должно быть 2 знака)")
    @Column(name = "email", nullable = true, length = 30)
    @Length(max = 30,message ="Максимальная длина 30" )
    private String email;
    @NotNull(message = "Не должно быть пустым")
    @NotEmpty(message = "Не должно быть пустым")
    @Pattern(regexp = "\\d{11}",message = "Номер телефона должен содержать 11 цифр")
    @Column(name = "phone_number", nullable = true, precision = 0)
    private String phoneNumber;

    public int getId() {
        return id;
    }

    public void setId(int deliveryCompanyId) {
        this.id = deliveryCompanyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String deliveryCompanyName) {
        this.name = deliveryCompanyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryCompany that = (DeliveryCompany) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, phoneNumber);
    }

}
