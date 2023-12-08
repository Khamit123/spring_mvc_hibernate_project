package bdapp.DAO;

import bdapp.model.Department;
import bdapp.model.Staff;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
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
        session.getTransaction().commit();
        return deps;
    }

    public List<Department> getFindDepatment(Department dep){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        String name= dep.getName();
        if(name==null || name==""){
            name="'%'";
        }
        else name="'%"+name+"%'";
        Query query= session.createQuery("from Department where  name like "+name);
        List s =query.getResultList();

        session.getTransaction().commit();

        return s;
    }

    public Department getOneDepartment(int id){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        Department dep= session.get(Department.class,id);
        session.getTransaction().commit();
        return dep;
    }


    public void updateDepartment(Department dep){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();

        session.merge(dep);
        session.getTransaction().commit();

    }
    public void deleteDepartment(Department dep){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        Department d= session.get(Department.class,dep.getId());
        session.delete(d);

        session.getTransaction().commit();

    }
    public  void addDepartment(Department dep){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(dep);
        session.getTransaction().commit();
    }
}
