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
        String type=machinery.getMachineTypeId();
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

    public Staff getOneStaff(int id){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        Staff staff1= session.get(Staff.class,id);
        session.getTransaction().commit();
        return staff1;
    }


    public void updateStaff(Staff staff){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        staff.setDepartment( (Department) session.createQuery("from Department where name='"+staff.getDepartment().getName() + "'").getResultList().get(0));
        session.merge(staff);
        session.getTransaction().commit();

    }
    public void deleteStaff(Staff staff){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        Staff staff1= session.get(Staff.class,staff.getId());
        session.delete(staff1);

        session.getTransaction().commit();

    }
    public  void addStaff(Staff staff){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(staff);
        session.getTransaction().commit();
    }


    public List<String> getNames() {
        return names;
    }
}
