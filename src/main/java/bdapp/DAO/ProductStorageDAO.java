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
public class ProductStorageDAO {
    private SessionFactory sessionFactory =new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(ProductStorage.class).addAnnotatedClass(Product.class)
            .buildSessionFactory();

    private List<String> names = new ArrayList<>(List.of("Название  склада","Адрес","Названия продукта","Количество продукта"));
    public List<Product> getMaterials(){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        List s =session.createQuery("from Product").getResultList();
        session.getTransaction().commit();
        return s;
    }

    public List<ProductStorage> getFind(ProductStorage obj){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        String name=obj.getName();
        String address=obj.getAdress();
        String id;
        if(obj.getMaterialId().getProductId()==0){
            id="1=1";
        }
        else id="materialId.productId="+obj.getMaterialId().getProductId();
        if(name==null || name==""){
            name="'%'";
        }
        else name="'%"+name+"%'";
        if(address==null || address==""){
            address="'%'";
        }
        else address="'%"+address+"%'";
        String quantity;
        if(obj.getQuantity()==0){
            quantity="1=1";
        }
        else quantity="quantity >="+obj.getQuantity();


        List<ProductStorage> s= session.createQuery("from ProductStorage where  " +
                "name like "+name+"and adress like "+address+"and "+id+"and "+quantity).getResultList();

        session.getTransaction().commit();

        return s;
    }
    public ProductStorage getOne(String id){

        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        ProductStorage obj= session.get(ProductStorage.class,id);
        session.getTransaction().commit();
        return obj;
    }

    public void update(ProductStorage newObj){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        newObj.setMaterialId(session.get(Product.class,newObj.getMaterialId().getProductId()));
        session.merge(newObj);
        session.getTransaction().commit();

    }
    public void delete(ProductStorage obj){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        ProductStorage deleteObj= session.get(ProductStorage.class,obj.getName());
        session.delete(deleteObj);
        session.getTransaction().commit();

    }
    public  void add(ProductStorage obj){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        obj.setMaterialId(session.get(Product.class,obj.getMaterialId().getProductId()));
        session.persist(obj);
        session.getTransaction().commit();
    }

    public List<String> getNames() {
        return names;
    }
}
