package bdapp.DAO;

import bdapp.model.MachineType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class MachineTypeDAO {
    private SessionFactory sessionFactory =new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(MachineType.class)
            .buildSessionFactory();
    private List<String> names = new ArrayList<>(List.of("Название  типа","Описание"));


    public List<MachineType> getFind(MachineType obj){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        String name=obj.getName();
        if(name==null || name==""){
            name="'%'";
        }
        else name="'%"+name+"%'";


        Query query= session.createQuery("from MachineType where  name like "+name);
        List s =query.getResultList();

        session.getTransaction().commit();

        return s;
    }
    public MachineType getOne(int id){

        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        MachineType obj= session.get(MachineType.class,id);
        session.getTransaction().commit();
        return obj;
    }

    public void update(MachineType newObj){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.merge(newObj);
        session.getTransaction().commit();

    }
    public void delete(MachineType obj){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        MachineType deleteObj= session.get(MachineType.class,obj.getMachineTypeId());
        session.delete(deleteObj);
        session.getTransaction().commit();

    }
    public  void add(MachineType obj){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(obj);
        session.getTransaction().commit();
    }

    public List<String> getNames() {
        return names;
    }
}
