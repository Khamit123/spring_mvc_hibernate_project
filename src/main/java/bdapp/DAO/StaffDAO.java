package bdapp.DAO;

import bdapp.model.Department;
import bdapp.model.Staff;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
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
        String department=staff.getDepartment().getName();
        System.out.println(department);
        if(name==null || name==""){
            name="'%'";
        }
        else name="'"+name+"%'";

        if(lastName==null || lastName==""){
            lastName="'%'";
        }
        else lastName= "'" +lastName +"%'";

        if(email==null || email==""){
            email="'%'";
        }
        else email= "'" +email +"%'";
        if(department=="" || department==null){
            department="1=1";
        }
        else department= " department.name ='" +department +"'";

        Query query= session.createQuery("from Staff where  name like "+name+" and lastName like " + lastName +" " +
                "and email like "+email +" and " +department);
        List s =query.getResultList();

        session.getTransaction().commit();

        return s;
    }

    public Staff getOneStaff(int id){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        Staff staff1= session.get(Staff.class,id);
        session.getTransaction().commit();
        return staff1;
    }


    public void updateStaff(Staff staff){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
       staff.setDepartment( (Department) session.createQuery("from Department where name='"+staff.getDepartment().getName() + "'").getResultList().get(0));
       session.merge(staff);
       session.getTransaction().commit();

    }
    public void deleteStaff(Staff staff){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
       Staff staff1= session.get(Staff.class,staff.getId());
       session.delete(staff1);

        session.getTransaction().commit();

    }
    public  void addStaff(Staff staff){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(staff);
        session.getTransaction().commit();
    }


    public List<String> getNames() {
        return names;
    }
}
