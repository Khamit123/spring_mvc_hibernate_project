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
public class ProductDAO {
    private SessionFactory sessionFactory =new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Product.class).addAnnotatedClass(CompositionOfProduct.class).addAnnotatedClass(Material.class)
            .buildSessionFactory();
    private List<String> names = new ArrayList<>(List.of("Название продукта","Цвет","Цена"));
    private List<String> colors = new ArrayList<>(List.of("Красный","Зелёный","Синий","Белый"));

    public List<String> getColors() {
        return colors;
    }

    public List<Product> getFindProduct(Product product){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        String name=product.getName();
        String price=Integer.toString(product.getPrice());
        String color = product.getColor();
        if(name==null || name==""){
            name="'%'";
        }
        else name="'"+name+"%'";

        if(price==null || price.equals("0")){
            price="1=1";
        }
        else price="price>= "+price+" ";

        if(color==null || color==""){
            color="'%'";
        }
        else color= "'" +color +"%'";

        Query query= session.createQuery("from Product where  name like "+name+" and "+price +
                "and color like "+color);
        List s =query.getResultList();

        session.getTransaction().commit();

        return s;
    }

    public void updateProduct(Product newProduct){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.merge(newProduct);
        session.getTransaction().commit();

    }
    public void deleteStaff(Product product){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        Product product1= session.get(Product.class,product.getProductId());
        session.delete(product1);
        session.getTransaction().commit();

    }
    public  void addStaff(Product product){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(product);
        session.getTransaction().commit();
    }


    public List<String> getNames() {
        return names;
    }
}
