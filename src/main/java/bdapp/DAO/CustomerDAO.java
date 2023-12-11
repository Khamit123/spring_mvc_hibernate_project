package bdapp.DAO;

import bdapp.model.Customer;
import bdapp.model.MachineStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerDAO {
    private SessionFactory sessionFactory =new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Customer.class)
            .buildSessionFactory();
    private List<String> names = new ArrayList<>(List.of("Имя","Почта"));


    public List<Customer> getFind(Customer obj){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        String name=obj.getName();
        String email=obj.getEmail();
        if(name==null || name==""){
            name="'%'";
        }
        else name="'%"+name+"%'";
        if(email==null || email==""){
            email="'%'";
        }
        else email="'%"+email+"%'";


        Query query= session.createQuery("from Customer where  name like "+name + "and email like " + email);
        List s =query.getResultList();

        session.getTransaction().commit();

        return s;
    }
    public Customer getOne(int id){

        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        Customer obj= session.get(Customer.class,id);
        session.getTransaction().commit();
        return obj;
    }

    public void update(Customer newObj){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.merge(newObj);
        session.getTransaction().commit();

    }
    public void delete(Customer obj){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        Customer deleteObj= session.get(Customer.class,obj.getId());
        session.delete(deleteObj);
        session.getTransaction().commit();

    }
    public  void add(Customer obj){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(obj);
        session.getTransaction().commit();
    }

    public List<String> getNames() {
        return names;
    }
}
