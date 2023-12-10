package bdapp.DAO;

import bdapp.model.CompositionOfProduct;
import bdapp.model.Material;
import bdapp.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MaterialDAO {
    private SessionFactory sessionFactory =new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Material.class)
            .buildSessionFactory();
    private List<String> names = new ArrayList<>(List.of("Название материала","Единица измерения","Цена"));
    private List<String> units = new ArrayList<>(List.of("кг","л","шт"));

    public List<String> getUnits() {
        return units;
    }

    public List<Material> getFind(Material material){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        String name=material.getName();
        String price=Integer.toString(material.getPrice());
        String unit = material.getUnitsOfMeasurement();
        if(name==null || name==""){
            name="'%'";
        }
        else name="'%"+name+"%'";

        if(price==null || price.equals("0")){
            price="1=1";
        }
        else price="price>= "+price+" ";

        if(unit==null || unit==""){
            unit="'%'";
        }
        else unit= "'" +unit +"%'";

        Query query= session.createQuery("from Material where  name like "+name+" and "+price +
                "and unitsOfMeasurement like "+unit);
        List s =query.getResultList();

        session.getTransaction().commit();

        return s;
    }
    public Material getOne(int id){

        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        Material material= session.get(Material.class,id);
        session.getTransaction().commit();
        return material;
    }

    public void update(Material newMaterial,int id){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        Material material=session.get(Material.class,id);
        newMaterial.setMaterialId(material.getMaterialId());
        session.merge(newMaterial);
        session.getTransaction().commit();

    }
    public void delete(Material material){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        Material material1= session.get(Material.class,material.getMaterialId());
        session.delete(material1);
        session.getTransaction().commit();

    }
    public  void add(Material material){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(material);
        session.getTransaction().commit();
    }

    public List<String> getNames() {
        return names;
    }

}
