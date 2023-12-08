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
public class CompositionOfProductDAO {
    private List<String> names = new ArrayList<>(List.of("Название продукта","Цвет","Название материала","Количество материала"));
    public List<String> getNames() {
        return names;
    }

    private SessionFactory sessionFactory =new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Product.class).addAnnotatedClass(CompositionOfProduct.class).addAnnotatedClass(Material.class)
            .buildSessionFactory();

    public Product product(int id){

        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        Product product= session.get(Product.class,id);
        session.getTransaction().commit();
        return product;
    }
    public List<CompositionOfProduct> ComProducts(int id){
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<CompositionOfProduct> compositionOfProducts
                =session.createQuery("from CompositionOfProduct where product_id =" + id).getResultList();
        session.getTransaction().commit();

        return compositionOfProducts;
    }


    public String colorConv(String col){
        if(col.equals("b")){
            return "Синий";
        }
        if(col.equals("r")){
            return "Красный";
        }
        if(col.equals("g")){
            return "Зелёный";
        }
        if(col.equals("w")){
            return "Белый";
        }
        return null;
    }



}
