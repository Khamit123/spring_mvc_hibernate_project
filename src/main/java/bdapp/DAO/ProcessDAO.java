package bdapp.DAO;

import bdapp.model.*;
import bdapp.model.Process;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProcessDAO {
    private SessionFactory sessionFactory =new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Process.class).addAnnotatedClass(Material.class).addAnnotatedClass(Product.class)
            .buildSessionFactory();
    private List<String> names = new ArrayList<>(List.of("Название","Продукт",
            "Материал","Количество материала","Описание"));


    public List<Material> getAllMaterial(){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Material> materials = session.createQuery("from Material ").getResultList();
        session.getTransaction().commit();
        return materials;
    }

    public List<Product> getAllProduct() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Product> products = session.createQuery("from Product ").getResultList();
        session.getTransaction().commit();
        return products;
    }

    public List<Process> getFind(Process obj){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        String material=Integer.toString( obj.getMaterial().getMaterialId());
        String product=Integer.toString(obj.getProduct().getProductId());
        String name=obj.getName();
        if(material==null || material.equals("0")){
            material="1=1";
        }
        else material="material.materialId = "+material;

        if(product==null || product.equals("0")){
            product="1=1";
        }
        else product="product.productId = "+product;

        if(name==null || name==""){
            name="'%'";
        }
        else name="'%"+name+"%'";



        Query query= session.createQuery("from Process where "+material
                + " and " + product +" and name like  " + name);
        List s =query.getResultList();

        session.getTransaction().commit();

        return s;
    }
    public Process getOne(int id){

        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        Process obj= session.get(Process.class,id);
        session.getTransaction().commit();
        return obj;
    }

    public  void add(Process obj){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Material material=session.get(Material.class,obj.getMaterial().getMaterialId());
        Product product=session.get(Product.class,obj.getProduct().getProductId());
        obj.setMaterial(material);
        obj.setProduct(product);
        session.persist(obj);
        session.getTransaction().commit();
    }
    public void delete(Process obj){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        Process deleteObj= session.get(Process.class,obj.getId());
        session.delete(deleteObj);
        session.getTransaction().commit();

    }
    public void update(Process obj){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        Material material=session.get(Material.class,obj.getMaterial().getMaterialId());
        Product product=session.get(Product.class,obj.getProduct().getProductId());
        obj.setMaterial(material);
        obj.setProduct(product);
        session.merge(obj);
        session.getTransaction().commit();

    }

    public List<String> getNames() {
        return names;
    }
}
