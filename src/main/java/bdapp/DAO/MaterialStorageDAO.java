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
public class MaterialStorageDAO {
    private SessionFactory sessionFactory =new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(MaterialStorage.class).addAnnotatedClass(Material.class)
            .buildSessionFactory();

    private List<String> names = new ArrayList<>(List.of("Название  склада","Адрес","Названия материала","Количество материала"));
    public List<Material> getMaterials(){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        List s =session.createQuery("from Material").getResultList();
        session.getTransaction().commit();
        return s;
    }

    public List<MaterialStorage> getFind(MaterialStorage obj){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        String name=obj.getName();
        String address=obj.getAdress();
        String id;
        if(obj.getMaterialId().getMaterialId()==0){
            id="1=1";
        }
        else id="materialId.materialId="+obj.getMaterialId().getMaterialId();
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


        Query query= session.createQuery("from MaterialStorage where  name like "+name+"and adress like "+address+"and "+id+"and "+quantity);
        List s =query.getResultList();

        session.getTransaction().commit();

        return s;
    }
    public MaterialStorage getOne(String id){

        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        MaterialStorage obj= session.get(MaterialStorage.class,id);
        session.getTransaction().commit();
        return obj;
    }

    public void update(MaterialStorage newObj){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        newObj.setMaterialId(session.get(Material.class,newObj.getMaterialId().getMaterialId()));
        session.merge(newObj);
        session.getTransaction().commit();

    }
    public void delete(MaterialStorage obj){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        MaterialStorage deleteObj= session.get(MaterialStorage.class,obj.getName());
        session.delete(deleteObj);
        session.getTransaction().commit();

    }
    public  void add(MaterialStorage obj){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        obj.setMaterialId(session.get(Material.class,obj.getMaterialId().getMaterialId()));
        session.persist(obj);
        session.getTransaction().commit();
    }

    public List<String> getNames() {
        return names;
    }
}
