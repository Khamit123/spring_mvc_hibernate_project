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
public class MaintenanceDAO {
    private List<String> names = new ArrayList<>(List.of("ID","Дата","Сотрудник","Что сделано"));
    public List<String> getNames() {
        return names;
    }
    private SessionFactory sessionFactory =new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Machinery.class).addAnnotatedClass(MachineStatus.class)
            .addAnnotatedClass(MachineType.class).addAnnotatedClass(Maintenance.class).addAnnotatedClass(Factory.class)
            .addAnnotatedClass(Staff.class).addAnnotatedClass(Department.class)
            .buildSessionFactory();

    public List<Maintenance> getFindMaintenance(Maintenance main){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        if(main.getMaintenanceId()!=0){
            List s=List.of( session.get(Maintenance.class,main.getMaintenanceId()));
            session.getTransaction().commit();
            return s;
        }
        String id;
        if(main.getStaffId().getId()==-1 || main.getStaffId().getId()==0){
            id="1=1";
        }
        else id="staffId.id="+main.getStaffId().getId();
        String date =main.getDateOfMaintenance();
        if(date==null || date==""){
            date="1=1";
        }
        else date="dateOfMaintenance>='"+date+"'";
        Query query= session.createQuery("from Maintenance where "+ id+" and "+date);
        List s =query.getResultList();

        session.getTransaction().commit();

        return s;
    }

    public Maintenance getOneMaintenance(int id){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        Maintenance main= session.get(Maintenance.class,id);
        session.getTransaction().commit();
        return main;
    }
    public List<Staff> getStaffs(){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        List s=session.createQuery("from Staff").getResultList();
        session.getTransaction().commit();
        return s;
    }


    public void updateMaintenance(Maintenance main){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        main.setStaffId(session.get(Staff.class,main.getStaffId().getId()));
        session.merge(main);
        session.getTransaction().commit();

    }
    public void deleteMaintenance(Maintenance main){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        Maintenance m= session.get(Maintenance.class,main.getMaintenanceId());
        session.delete(m);

        session.getTransaction().commit();

    }
    public  void addMaintenance(Maintenance maintenance){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        maintenance.setStaffId(session.get(Staff.class,maintenance.getStaffId().getId()));
        session.persist(maintenance);
        session.getTransaction().commit();
    }
}
