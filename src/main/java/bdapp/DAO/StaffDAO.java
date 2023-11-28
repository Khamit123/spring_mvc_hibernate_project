package bdapp.DAO;

import bdapp.model.Department;
import bdapp.model.Staff;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StaffDAO {
    private List<String> names=new ArrayList<>(List.of("Имя ","Фамилия ","Отчество ","Зарплата ","Номер телефона ",
            "Электронная почта ","Должность ","День рождения ","Статус","Дата найма ","Дата повышения зарплаты ","Отдел"));

    private SessionFactory sessionFactory =new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Staff.class).addAnnotatedClass(Department.class)
            .buildSessionFactory();

    public List<Staff> getAllStaffs(){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Staff> s= session.createQuery("from Staff ").getResultList();
        session.getTransaction().commit();
        return s;
    }
    public List<Staff> getFindStaff(Staff staff){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        String name=staff.getName();
        String lastName=staff.getLastName();
        String email=staff.getEmail();
        if(name==null || name==""){
            name="1=1";
        }
        else name="name ='"+name+"'";

        if(lastName==null || lastName==""){
            lastName="1=1";
        }
        else lastName= " lastName ='" +lastName +"'";

        if(email==null || email==""){
            email="1=1";
        }
        else email= " lastName ='" +email +"'";


        System.out.println("from Staff where "+name+" and" + lastName);
        List<Staff> s= session.createQuery("from Staff where "+name+" and " + lastName +" and "+email).getResultList();

        session.getTransaction().commit();

        return s;
    }


    public List<String> getNames() {
        return names;
    }
}
