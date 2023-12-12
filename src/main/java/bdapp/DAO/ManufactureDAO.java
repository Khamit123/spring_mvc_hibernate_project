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
public class ManufactureDAO {
    private SessionFactory sessionFactory =new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Manufacture.class)
            .addAnnotatedClass(Factory.class).addAnnotatedClass(Department.class)
            .addAnnotatedClass(Product.class).addAnnotatedClass(Staff.class)
            .buildSessionFactory();

    private List<String> names = new ArrayList<>(List.of("Дата производства","Название завода","Названия продукта","Количество продукта"));
    public List<Factory> getFactories(){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        List s =session.createQuery("from Factory").getResultList();
        session.getTransaction().commit();
        return s;
    }

    public List<Product> getProducts(){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        List s =session.createQuery("from Product").getResultList();
        session.getTransaction().commit();
        return s;
    }

    public List<MachineStorage> getFind(Manufacture obj){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        String factoryId;
        if(obj.getFactory().getFactoryId()==0){
            factoryId="1=1";
        }
        else factoryId="factory.factoryId="+obj.getFactory().getFactoryId();
        String productId;
        if(obj.getProduct().getProductId()==0){
            productId="1=1";
        }
        else productId="product.productId="+obj.getProduct().getProductId();

        String date = obj.getDate();
        if(date==null || date==""){
            date="1=1";
        }
        else date="date >='"+date+"'";
        String quantity;
        if(obj.getProductQuantity()==0){
            quantity="1=1";
        }
        else quantity="productQuantity >="+obj.getProductQuantity();
        Query query= session.createQuery("from Manufacture where "+ factoryId+" and "+date+" and "+productId+" and "+quantity);
        List s =query.getResultList();

        session.getTransaction().commit();

        return s;
    }
    public Manufacture getOne(int id){

        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        Manufacture obj= session.get(Manufacture.class,id);
        session.getTransaction().commit();
        return obj;
    }

    public void update(Manufacture newObj){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        newObj.setFactory(session.get(Factory.class,newObj.getFactory().getFactoryId()));
        newObj.setProduct(session.get(Product.class,newObj.getProduct().getProductId()));
        session.merge(newObj);
        session.getTransaction().commit();

    }
    public void delete(Manufacture obj){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        Manufacture deleteObj= session.get(Manufacture.class,obj.getId());
        session.delete(deleteObj);
        session.getTransaction().commit();

    }
    public  void add(Manufacture newObj){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        newObj.setFactory(session.get(Factory.class,newObj.getFactory().getFactoryId()));
        newObj.setProduct(session.get(Product.class,newObj.getProduct().getProductId()));
        session.persist(newObj);
        session.getTransaction().commit();
    }

    public List<String> getNames() {
        return names;
    }
}
