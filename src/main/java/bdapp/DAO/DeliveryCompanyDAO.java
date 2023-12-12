package bdapp.DAO;

import bdapp.model.Customer;
import bdapp.model.DeliveryCompany;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class DeliveryCompanyDAO {
    private SessionFactory sessionFactory =new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(DeliveryCompany.class)
            .buildSessionFactory();
    private List<String> names = new ArrayList<>(List.of("Название","Почта","Номер телефона"));


    public List<DeliveryCompany> getFind(DeliveryCompany obj){
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


        Query query= session.createQuery("from DeliveryCompany where  name like "+name + "and email like " + email);
        List s =query.getResultList();

        session.getTransaction().commit();

        return s;
    }
    public DeliveryCompany getOne(int id){

        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        DeliveryCompany obj= session.get(DeliveryCompany.class,id);
        session.getTransaction().commit();
        return obj;
    }

    public void update(DeliveryCompany newObj){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.merge(newObj);
        session.getTransaction().commit();

    }
    public void delete(DeliveryCompany obj){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        DeliveryCompany deleteObj= session.get(DeliveryCompany.class,obj.getId());
        session.delete(deleteObj);
        session.getTransaction().commit();

    }
    public  void add(DeliveryCompany obj){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(obj);
        session.getTransaction().commit();
    }

    public List<String> getNames() {
        return names;
    }
}
