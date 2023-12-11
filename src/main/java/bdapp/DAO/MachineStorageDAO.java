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
public class MachineStorageDAO {
    private SessionFactory sessionFactory =new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(MachineStorage.class) .addAnnotatedClass(Machinery.class).addAnnotatedClass(MachineStatus.class)
            .addAnnotatedClass(MachineType.class).addAnnotatedClass(Maintenance.class).addAnnotatedClass(Factory.class)
            .addAnnotatedClass(Staff.class).addAnnotatedClass(Department.class)
            .buildSessionFactory();

    private List<String> names = new ArrayList<>(List.of("Название  склада","Адрес","Названия оборудования"));
    public List<Machinery> getMachines(){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        List s =session.createQuery("from Machinery").getResultList();
        session.getTransaction().commit();
        return s;
    }

    public List<MachineStorage> getFind(MachineStorage obj){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        String name=obj.getName();
        String address=obj.getAdress();
        String id;
        if(obj.getMachineId().getMachineId()==0){
            id="1=1";
        }
        else id="machineId.machineId="+obj.getMachineId().getMachineId();
        if(name==null || name==""){
            name="'%'";
        }
        else name="'%"+name+"%'";
        if(address==null || address==""){
            address="'%'";
        }
        else address="'%"+address+"%'";


        Query query= session.createQuery("from MachineStorage where  name like "+name+"and adress like "+address+"and "+id);
        List s =query.getResultList();

        session.getTransaction().commit();

        return s;
    }
    public MachineStorage getOne(String id){

        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        MachineStorage obj= session.get(MachineStorage.class,id);
        session.getTransaction().commit();
        return obj;
    }

    public void update(MachineStorage newObj){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        newObj.setMachineId(session.get(Machinery.class,newObj.getMachineId().getMachineId()));
        session.merge(newObj);
        session.getTransaction().commit();

    }
    public void delete(MachineStorage obj){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        MachineStorage deleteObj= session.get(MachineStorage.class,obj.getName());
        session.delete(deleteObj);
        session.getTransaction().commit();

    }
    public  void add(MachineStorage obj){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        obj.setMachineId(session.get(Machinery.class,obj.getMachineId().getMachineId()));
        session.persist(obj);
        session.getTransaction().commit();
    }

    public List<String> getNames() {
        return names;
    }
}
