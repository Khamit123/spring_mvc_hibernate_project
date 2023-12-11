package bdapp.DAO;

import bdapp.model.MachineStatus;
import bdapp.model.Material;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class MachineStatusDAO {
    private SessionFactory sessionFactory =new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(MachineStatus.class)
            .buildSessionFactory();
    private List<String> names = new ArrayList<>(List.of("Название  статуса","Описание"));


    public List<MachineStatus> getFind(MachineStatus obj){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        String name=obj.getName();
        if(name==null || name==""){
            name="'%'";
        }
        else name="'%"+name+"%'";


        Query query= session.createQuery("from MachineStatus where  name like "+name);
        List s =query.getResultList();

        session.getTransaction().commit();

        return s;
    }
    public MachineStatus getOne(int id){

        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        MachineStatus obj= session.get(MachineStatus.class,id);
        session.getTransaction().commit();
        return obj;
    }

    public void update(MachineStatus newObj){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.merge(newObj);
        session.getTransaction().commit();

    }
    public void delete(MachineStatus obj){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        MachineStatus deleteObj= session.get(MachineStatus.class,obj.getMachineStatusId());
        session.delete(deleteObj);
        session.getTransaction().commit();

    }
    public  void add(MachineStatus obj){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(obj);
        session.getTransaction().commit();
    }

    public List<String> getNames() {
        return names;
    }
}
