package bdapp.DAO;

import bdapp.model.Department;
import bdapp.model.Staff;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepartmentDAO {
    private SessionFactory sessionFactory =new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Staff.class).addAnnotatedClass(Department.class)
            .buildSessionFactory();

    public List<Department> allDep(){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        List deps= session.createQuery("from Department").getResultList();
        System.out.println(deps);
        session.getTransaction().commit();
        return deps;
    }
}
