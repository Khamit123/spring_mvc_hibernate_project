package bdapp.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

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
    private String name;


    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "salary")
    private int salary;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "post")
    private String post;


    @Column(name = "birthday")
    private Date birthday;


    @Column(name ="isworking")
    private boolean isWorking;

    @Column(name = "date_of_hire")
    private Date dateOfHire;

    @Column(name = "date_of_slary")
    private Date dateOfSalary;

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

    public Staff() {
        this.department=new Department();
    }

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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
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

    public Date getDateOfHire() {
        return dateOfHire;
    }

    public void setDateOfHire(Date dateOfHire) {
        this.dateOfHire = dateOfHire;
    }

    public Date getDateOfSalary() {
        return dateOfSalary;
    }

    public void setDateOfSalary(Date dateOfSalary) {
        this.dateOfSalary = dateOfSalary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department.setName(department);
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
}
