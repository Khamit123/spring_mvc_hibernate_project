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
        String name= main.getStaffId().getName();
        String date =main.getDateOfMaintenance();
        if(name==null || name==""){
            name="'%'";
        }
        else name="'"+name+"%'";
        if(date==null || date==""){
            date="'%'";
        }
        else date="'%"+date+"%'";
        Query query= session.createQuery("from Maintenance where  staffId.name like "+name+"and dateOfMaintenance like"+date);
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
