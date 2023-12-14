package bdapp.DAO;

import bdapp.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FactoryDAO {
    private SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Factory.class).addAnnotatedClass(Department.class)
            .addAnnotatedClass(Product.class).addAnnotatedClass(Staff.class)
            .buildSessionFactory();

    private List<String> names = new ArrayList<>(List.of("Название", "Адрес", "Руководитель", "Зарплата завода"));

    public List<Staff> getStaffs() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List s = session.createQuery("from Staff").getResultList();
        session.getTransaction().commit();
        return s;
    }

    public List<Factory> getFind(Factory obj) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String StaffId;
        if (obj.getStaffId().getId() == 0) {
            StaffId = "1=1";
        } else StaffId = "staffId.id=" + obj.getStaffId().getId();

        String name = obj.getName();
        if (name == null || name == "") {
            name = "'%'";
        } else name = "'%" + name + "%'";
        String address = obj.getAdress();
        if (address == null || address == "") {
            address = "'%'";
        } else address = "'%" + address + "%'";
        Query query = session.createQuery("from Factory where name like" + name + " and adress like" + address + " and " + StaffId);
        List<Factory> s = query.getResultList();
        s.forEach(factory -> {
            factory.setSalary((int) (session.createNativeQuery("select pen_factory.factorysalary(" + factory.getFactoryId() + ");").getResultList().get(0)));
        });
        session.getTransaction().

    commit();

        return s;
}

    public Factory getOne(int id) {

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Factory obj = session.get(Factory.class, id);
        session.getTransaction().commit();
        return obj;
    }

    public void update(Factory newObj) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        newObj.setStaffId(session.get(Staff.class, newObj.getStaffId().getId()));
        session.merge(newObj);
        session.getTransaction().commit();

    }

    public void delete(Factory obj) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Factory deleteObj = session.get(Factory.class, obj.getFactoryId());
        session.delete(deleteObj);
        session.getTransaction().commit();

    }

    public void add(Factory newObj) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        newObj.setStaffId(session.get(Staff.class, newObj.getStaffId().getId()));
        session.persist(newObj);
        session.getTransaction().commit();
    }

    public List<String> getNames() {
        return names;
    }
}
