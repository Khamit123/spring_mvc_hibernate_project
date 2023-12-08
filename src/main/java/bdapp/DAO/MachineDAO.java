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
public class MachineDAO {
    private List<String> names=new ArrayList<>(List.of("Название ","Тип","Статус ","Завод ","Техобслуживание "));

    private SessionFactory sessionFactory =new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Machinery.class).addAnnotatedClass(MachineStatus.class)
            .addAnnotatedClass(MachineType.class).addAnnotatedClass(Maintenance.class).addAnnotatedClass(Factory.class)
            .addAnnotatedClass(Staff.class).addAnnotatedClass(Department.class)
            .buildSessionFactory();

    public List<Machinery> getFindMachine(Machinery machinery){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        String name=machinery.getName();
        String factory=machinery.getFactoryId().getName();
        String status=machinery.getMachineStatusId().getName();
        String type=machinery.getMachineTypeId().getName();
        System.out.println(name+"\n"+status+"\n"+factory+"\n"+type);
        if(name==null || name==""){
            name="'%'";
        }
        else name="'"+name+"%'";

        if(factory=="" || factory==null){
            factory="'%'";
        }
        else factory= "'" +factory +"%'";
        if(status=="" || status==null){
            status="'%'";
        }
        else status= "'" +status +"%'";
        if(type=="" || type==null){
            type="'%'";
        }
        else type= "'" +type +"%'";


        Query query= session.createQuery("from Machinery where  name like "+name+" and factoryId.name like " + factory +" " +
                "and machineStatusId.name like "+status +" and machineTypeId.name like" +type);
        List s =query.getResultList();

        session.getTransaction().commit();

        return s;
    }

    public Machinery getOneMachine(int id){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        Machinery machinery= session.get(Machinery.class,id);
        session.getTransaction().commit();
        return machinery;
    }


    public void updateMachine(Machinery machinery){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        machinery.setFactoryId((Factory) session.createQuery
                ("from Factory where name ='"+ machinery.getFactoryId().getName()+"'")
                .getResultList().get(0));
        machinery.setMachineStatusId((MachineStatus) session.createQuery
                ("from MachineStatus where name ='"+machinery.getMachineStatusId().getName()+"'")
                .getResultList().get(0));
        machinery.setMachineTypeId((MachineType) session.createQuery
                ("from MachineType where name ='"+machinery.getMachineTypeId().getName()+"'")
                .getResultList().get(0));
        machinery.setMaintenanceId(session.get(Maintenance.class,machinery.getMaintenanceId().getMaintenanceId()));
        session.merge(machinery);
        session.getTransaction().commit();

    }
    public void deleteMachine(Machinery machinery){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        Machinery machinery1= session.get(Machinery.class,machinery.getMachineId());
        session.delete(machinery1);

        session.getTransaction().commit();

    }
    public  void addMachine(Machinery machinery){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(machinery);
        session.getTransaction().commit();
    }
    public List<MachineStatus> getStatus(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List macs= session.createQuery("from MachineStatus").getResultList();
        session.getTransaction().commit();
        return macs;
    }
    public List<MachineType> getType(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List macs= session.createQuery("from MachineType").getResultList();
        session.getTransaction().commit();
        return macs;
    }
    public List<Factory> getFactory(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List macs= session.createQuery("from Factory").getResultList();
        session.getTransaction().commit();
        return macs;
    }
    public List<Maintenance> getMain(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List macs= session.createQuery("from Maintenance").getResultList();
        session.getTransaction().commit();
        return macs;
    }



    public List<String> getNames() {
        return names;
    }
}
