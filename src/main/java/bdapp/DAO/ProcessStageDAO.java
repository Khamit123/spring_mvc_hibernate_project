package bdapp.DAO;

import bdapp.model.*;
import bdapp.model.Process;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProcessStageDAO {

    private SessionFactory sessionFactory =new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Process.class).addAnnotatedClass(MachineType.class).addAnnotatedClass(ProcessStage.class).addAnnotatedClass(Material.class)
            .addAnnotatedClass(Product.class)
            .buildSessionFactory();
    private List<String> names = new ArrayList<>(List.of("Процесс","Тип машины",
            "Количество машин"));


    public List<Process> getAllProcess(){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Process> materials = session.createQuery("from Process ").getResultList();
        session.getTransaction().commit();
        return materials;
    }

    public List<MachineType> getAllMacType() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<MachineType> products = session.createQuery("from MachineType ").getResultList();
        session.getTransaction().commit();
        return products;
    }

    public List<ProcessStage> getFind(ProcessStage obj){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        String material=Integer.toString( obj.getMachineType().getMachineTypeId());
        String product=obj.getProcess().getName();
        if(material==null || material.equals("0")){
            material="1=1";
        }
        else material="machineType.machineTypeId = "+material;

        if(product==null || product==""){
            product="'%'";
        }
        else product="'%"+product+"%'";




        Query query= session.createQuery("from ProcessStage where "+material
                + " and process.name like " + product );
        List s =query.getResultList();

        session.getTransaction().commit();

        return s;
    }
    public ProcessStage getOne(int id){

        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        ProcessStage obj= session.get(ProcessStage.class,id);
        session.getTransaction().commit();
        return obj;
    }

    public  void add(ProcessStage obj){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Process process=session.get(Process.class,obj.getProcess().getId());
        MachineType machineType=session.get(MachineType.class,obj.getMachineType().getMachineTypeId());
        obj.setProcess(process);
        obj.setMachineType(machineType);
        session.persist(obj);
        session.getTransaction().commit();
    }
    public void delete(ProcessStage obj){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        ProcessStage deleteObj= session.get(ProcessStage.class,obj.getId());
        session.delete(deleteObj);
        session.getTransaction().commit();

    }
    public void update(ProcessStage obj){
        Session session =sessionFactory.getCurrentSession();
        session.beginTransaction();
        Process process=session.get(Process.class,obj.getProcess().getId());
        MachineType machineType=session.get(MachineType.class,obj.getMachineType().getMachineTypeId());
        obj.setProcess(process);
        obj.setMachineType(machineType);
        session.merge(obj);
        session.getTransaction().commit();

    }

    public List<String> getNames() {
        return names;
    }
}
