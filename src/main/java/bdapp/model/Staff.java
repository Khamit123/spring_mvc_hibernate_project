package bdapp.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "staff")
public class Staff {
    @Column(name = "staff_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty
    @Pattern(regexp = "[А-ЯЁ].[а-яё]+",message = "Имя должно содержать минимум две буквы и начинаться с заглавной буквы")
    private String name;

    @NotNull(message = "Не должно быть пустым")
    @NotEmpty(message = "Не должно быть пустым")
    @Column(name = "last_name")
    @Pattern(regexp = "[А-ЯЁ].[а-яё]+",message = "Фамилия должно содержать минимум две буквы и начинаться с заглавной буквы")
    private String lastName;
    @NotNull(message = "Не должно быть пустым")
    @NotEmpty(message = "Не должно быть пустым")
    @Column(name = "middle_name")
    @Pattern(regexp = "[А-ЯЁ].[а-яё]+",message = "Отчество должно содержать минимум две буквы и начинаться с заглавной буквы")
    private String middleName;
    @NotNull(message = "Не должно быть пустым")
    @Column(name = "salary")
    @Max(10000000)
    @Min(1)
    private int salary;
    @NotNull(message = "Не должно быть пустым")
    @NotEmpty(message = "Не должно быть пустым")
    @Column(name = "phone_number")
    @Pattern(regexp = "\\d{11}",message = "Номер телефона должен содержать 11 цифр")
    private String phoneNumber;
    @NotNull(message = "Не должно быть пустым")
    @NotEmpty(message = "Не должно быть пустым")
    @Column(name = "email")
    @Email(message = "Почта должна соответсвовать виду ???@???(знак вопроса - любой символ,причем до знака @ должно быть 2 знака)")
    private String email;
    @NotNull(message = "Не должно быть пустым")
    @NotEmpty(message = "Не должно быть пустым")
    @Column(name = "post")
    private String post;

    @NotNull(message = "Не должно быть пустым")
    @NotEmpty(message = "Не должно быть пустым")
    @Column(name = "birthday")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public String birthday;

    @NotNull(message = "Не должно быть пустым")
    @Column(name ="isworking")

    private boolean isWorking;
    @NotNull(message = "Не должно быть пустым")
    @Column(name = "date_of_hire")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotEmpty(message = "Не должно быть пустым")
    private String dateOfHire;
    @NotNull(message = "Не должно быть пустым")
    @Column(name = "date_of_slary")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotEmpty(message = "Не должно быть пустым")
    private String dateOfSalary;
    @NotNull(message = "Не должно быть пустым")
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;
    public String FullName(){
        return lastName+" "+name+" "+middleName;
    }

    public Staff(int id, String name, String lastName, String middleName, int salary, String phoneNumber, String email, String post, String birthday, boolean isWorking, String dateOfHire, String dateOfSalary, Department department) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.middleName = middleName;
        this.salary = salary;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.post = post;
        this.birthday = birthday;
        this.isWorking = isWorking;
        this.dateOfHire = dateOfHire;
        this.dateOfSalary = dateOfSalary;
        this.department = department;
    }

    public Staff() {this.department=new Department();}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getBirthday() {
        if(birthday==null){
            return "";
        }

        return birthday.toString();
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public boolean isWorking() {
        return isWorking;
    }
    public String GetisWorking() {
        if(isWorking){
            return "Работает";
        }
        return "Не работает";
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

    public String getDateOfHire() {
        if(dateOfHire==null){
            return "";
        }
        return dateOfHire.toString();
    }

    public void setDateOfHire(String dateOfHire) {
        this.dateOfHire =dateOfHire;
    }

    public String getDateOfSalary() {
        if(dateOfSalary==null){
            return "";
        }
        return dateOfSalary.toString();
    }

    public void setDateOfSalary(String dateOfSalary) {
        this.dateOfSalary = dateOfSalary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department=department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return id == staff.id && salary == staff.salary && isWorking == staff.isWorking && Objects.equals(name, staff.name) && Objects.equals(lastName, staff.lastName) && Objects.equals(middleName, staff.middleName) && Objects.equals(phoneNumber, staff.phoneNumber) && Objects.equals(email, staff.email) && Objects.equals(post, staff.post) && Objects.equals(birthday, staff.birthday) && Objects.equals(dateOfHire, staff.dateOfHire) && Objects.equals(dateOfSalary, staff.dateOfSalary) && Objects.equals(department, staff.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, middleName, salary, phoneNumber, email, post, birthday, isWorking, dateOfHire, dateOfSalary, department);
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", salary=" + salary +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", post='" + post + '\'' +
                ", birthday=" + birthday +
                ", isWorking=" + isWorking +
                ", dateOfHire=" + dateOfHire +
                ", dateOfSalary=" + dateOfSalary +
                ", department=" + department +
                '}';
    }
}
