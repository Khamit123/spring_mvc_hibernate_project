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
    private SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml")
            .addAnnotatedClass(Staff.class)
            .addAnnotatedClass(Department.class).buildSessionFactory();
    private Session session=sessionFactory.getCurrentSession();

    public List<Staff> getAllStaffs(){
        session.beginTransaction();
        List<Staff> s= session.createQuery("from Staff ").getResultList();
        session.getTransaction().commit();
        return s;
    }

    public List<String> getNames() {
        return names;
    }
}
