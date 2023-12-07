package bdapp.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
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
    @Pattern(regexp = "[А-ЯЁ].[а-яё]+")
    private String name;

    @NotNull
    @NotEmpty
    @Column(name = "last_name")
    @Pattern(regexp = "[А-ЯЁ].[а-яё]+")
    private String lastName;
    @NotNull
    @NotEmpty
    @Column(name = "middle_name")
    @Pattern(regexp = "[А-ЯЁ].[а-яё]+")
    private String middleName;
    @NotNull
    @Column(name = "salary")
    @Pattern(regexp = "\\d{8}")
    private int salary;
    @NotNull
    @NotEmpty
    @Column(name = "phone_number")
    @Pattern(regexp = "\\d{11}")
    private String phoneNumber;
    @NotNull
    @NotEmpty
    @Column(name = "email")
    @Email
    private String email;
    @NotNull
    @NotEmpty
    @Column(name = "post")
    private String post;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birthday")
    private Date birthday;

    @NotNull
    @Column(name ="isworking")
    private boolean isWorking;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Column(name = "date_of_hire")
    private Date dateOfHire;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_of_slary")

    private Date dateOfSalary;
    @NotNull
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    public Staff(String name, String lastName, String middleName, int salary, String phoneNumber, String email, String post, Date birthday, boolean isWorking, Date dateOfHire, Date dateOfSalary, Department department) {
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
        this.birthday = Date.valueOf(birthday);
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
        this.dateOfHire = Date.valueOf(dateOfHire);
    }

    public String getDateOfSalary() {
        if(dateOfSalary==null){
            return "";
        }
        return dateOfSalary.toString();
    }

    public void setDateOfSalary(String dateOfSalary) {
        this.dateOfSalary = Date.valueOf(dateOfSalary);
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
