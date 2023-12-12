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
public class MaterialDeliveryDAO {
    private SessionFactory sessionFactory =new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(MaterialDelivery.class).addAnnotatedClass(Material.class).addAnnotatedClass(DeliveryCompany.class)
            .buildSessionFactory();
    private List<String> names = new ArrayList<>(List.of("Заказчик","Материал",
            "Количество материала","Цена","Статус","Дата выполнения","Дата заказа"));


    public List<Material> getAllMaterial(){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Material> materials = session.createQuery("from Material ").getResultList();
        session.getTransaction().commit();
        return materials;
    }

    public List<DeliveryCompany> getAllProdDeliveryCompany() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<DeliveryCompany> deliveryCompanies = session.createQuery("from DeliveryCompany ").getResultList();
        session.getTransaction().commit();
        return deliveryCompanies;
    }

    public List<MaterialDelivery> getFind(MaterialDelivery obj){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        String material=obj.getMaterial().getName();
        String company=obj.getDeliveryCompany().getName();
        String dateOrder=obj.getDateOfOrder();
        if(material==null || material==""){
            material="'%'";
        }
        else material="'%"+material+"%'";

        if(company==null || company==""){
            company="'%'";
        }
        else company="'%"+company+"%'";

        if(dateOrder==null || dateOrder==""){
            dateOrder="1=1";
        }
        else dateOrder=" dateOfOrder>='"+dateOrder+"'";


        Query query= session.createQuery("from MaterialDelivery where  material.name like "+material
                + " and deliveryCompany.name like " + company +" and " + dateOrder);
        List s =query.getResultList();

        session.getTransaction().commit();

        return s;
    }
    public MaterialDelivery getOne(int id){

        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        MaterialDelivery obj= session.get(MaterialDelivery.class,id);
        session.getTransaction().commit();
        return obj;
    }

    public  void add(MaterialDelivery obj){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Material material=session.get(Material.class,obj.getMaterial().getMaterialId());
        DeliveryCompany company=session.get(DeliveryCompany.class,obj.getDeliveryCompany().getId());
        obj.setMaterial(material);
        obj.setDeliveryCompany(company);
        session.persist(obj);
        session.getTransaction().commit();
    }
    public void update(MaterialDelivery newObj){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        Material material=session.get(Material.class,newObj.getMaterial().getMaterialId());
        DeliveryCompany company=session.get(DeliveryCompany.class,newObj.getDeliveryCompany().getId());
        newObj.setMaterial(material);
        newObj.setDeliveryCompany(company);
        session.merge(newObj);
        session.getTransaction().commit();

    }

    public List<String> getNames() {
        return names;
    }
}
