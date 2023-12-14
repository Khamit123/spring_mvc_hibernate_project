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



    private SessionFactory sessionFactory =new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Product.class).addAnnotatedClass(CompositionOfProduct.class).addAnnotatedClass(Material.class)
            .buildSessionFactory();
    private List<String> names = new ArrayList<>(List.of("Продукт","Материал","Количество"));

    public List<Product> getAllProd() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Product> products = session.createQuery("from Product ").getResultList();
        session.getTransaction().commit();
        return products;
    }
    public List<Material> getAllMat() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Material> materials = session.createQuery("from Material ").getResultList();
        session.getTransaction().commit();
        return materials;
    }
    public List<CompositionOfProduct> getFind(CompositionOfProduct compositionOfProduct){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        String prId=Integer.toString(compositionOfProduct.getProduct().getProductId());
        String matId=Integer.toString(compositionOfProduct.getMaterial().getMaterialId());
        System.out.println(prId);
        System.out.println(matId);
        System.out.println("------------------------------------------------------------------------------------");
        if(prId==null ||  prId.equals("0")){
            prId="1=1";
        }
        else prId="product.productId= "+prId;

        if(matId==null || matId.equals("0")){
            matId="1=1";
        }
        else matId="material.materialId= "+matId;


        Query query= session.createQuery("from CompositionOfProduct where "+prId+" and "+matId );
        List s =query.getResultList();
        System.out.println("------------------------------------------------------------------------------------");

        session.getTransaction().commit();

        return s;
    }
    public CompositionOfProduct getOne(int matId,int prodId){

        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        CompositionOfProduct compositionOfProduct= (CompositionOfProduct)session.
                createQuery("from CompositionOfProduct where material.id =" + matId + " and product.id = " + prodId)
                .getResultList().get(0);
        session.getTransaction().commit();
        return compositionOfProduct;
    }

    public void update(CompositionOfProduct newcompositionOfProduct,int matId,int prodId){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        newcompositionOfProduct.getProduct().setProductId(prodId);
        newcompositionOfProduct.getMaterial().setMaterialId(matId);
        try {
            session.merge(newcompositionOfProduct);
        }
        catch (Exception e) {
            throw e;
        }
        finally {
            session.getTransaction().commit();
        }



    }
    public void delete(int matId,int prodId){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        CompositionOfProduct material1= (CompositionOfProduct)session.createQuery("from CompositionOfProduct where product.productId = "
                + prodId + " and material.materialId =" + matId).getResultList().get(0);
        session.delete(material1);
        session.getTransaction().commit();

    }
    public  void add(CompositionOfProduct compositionOfProduct){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        try {
            compositionOfProduct.setProduct(session.get(Product.class,compositionOfProduct.getProduct().getProductId()));
            compositionOfProduct.setMaterial(session.get(Material.class,compositionOfProduct.getMaterial().getMaterialId()));
            session.persist(compositionOfProduct);
        }
        catch (Exception e) {
            e.printStackTrace();
        throw  e;
        }
        finally {
            session.getTransaction().commit();
        }
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

    public List<String> getNames() {
        return names;
    }



}
