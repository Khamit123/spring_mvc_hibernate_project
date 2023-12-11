package bdapp.DAO;

import bdapp.model.*;
import bdapp.model.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDAO {

    private SessionFactory sessionFactory =new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Order.class).addAnnotatedClass(Product.class).addAnnotatedClass(Customer.class)
            .buildSessionFactory();
    private List<String> names = new ArrayList<>(List.of("Заказчик","Продукт",
            "Дата заказа","Дата выполнения","Статус","Адрес доставки","Цена","Количество продукта","Дата обновления","Дата записи"));


    public List<Customer> getAllCustomer(){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Customer> customers = session.createQuery("from Customer ").getResultList();
        session.getTransaction().commit();
        return customers;
    }

    public List<Product> getAllProd() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Product> products = session.createQuery("from Product ").getResultList();
        session.getTransaction().commit();
        return products;
    }

    public List<Order> getFind(Order obj){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        String product=obj.getProduct().getName();
        String customer=obj.getCustomer().getName();
        String dateOrder=obj.getDateOfOrder();
        if(product==null || product==""){
            product="'%'";
        }
        else product="'%"+product+"%'";

        if(customer==null || customer==""){
            customer="'%'";
        }
        else customer="'%"+customer+"%'";

        if(dateOrder==null || dateOrder==""){
            dateOrder="1=1";
        }
        else dateOrder=" dateOfOrder>='"+dateOrder+"'";


        Query query= session.createQuery("from Order where  product.name like "+product
                + " and customer.name like " + customer +" and " + dateOrder);
        List s =query.getResultList();

        session.getTransaction().commit();

        return s;
    }
    public Order getOne(int id){

        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        Order obj= session.get(Order.class,id);
        session.getTransaction().commit();
        return obj;
    }

    public  void add(Order obj){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Customer customer=session.get(Customer.class,obj.getCustomer().getId());
        Product product=session.get(Product.class,obj.getProduct().getProductId());
        obj.setCustomer(customer);
        obj.setProduct(product);
        session.persist(obj);
        session.getTransaction().commit();
    }
    public void update(Order newObj){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        Customer customer=session.get(Customer.class,newObj.getCustomer().getId());
        Product product=session.get(Product.class,newObj.getProduct().getProductId());
        newObj.setCustomer(customer);
        newObj.setProduct(product);
        session.merge(newObj);
        session.getTransaction().commit();

    }

    public List<String> getNames() {
        return names;
    }
}
