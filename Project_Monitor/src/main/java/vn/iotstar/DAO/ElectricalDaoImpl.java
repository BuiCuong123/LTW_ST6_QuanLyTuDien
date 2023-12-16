package vn.iotstar.DAO;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.mysql.cj.Query;

import jakarta.persistence.EntityTransaction;
import vn.iotstar.Config.JPAConfig;
import vn.iotstar.Entity.Electrical;

public class ElectricalDaoImpl implements IElectricalDao {

	@Override
	public List<Electrical> findAll() {
	    EntityManager em = JPAConfig.getEntityManager();
	    // Sử dụng JPQL để lấy hàng có ID lớn nhất
	    String jpqlQuery = "SELECT e FROM Electrical e ORDER BY e.id DESC";
	    TypedQuery<Electrical> query = em.createQuery(jpqlQuery, Electrical.class);
	    query.setMaxResults(1); // Giới hạn kết quả trả về chỉ là 1 hàng

	    // Thực hiện truy vấn và trả về kết quả
	    List<Electrical> resultList = query.getResultList();
	    
	    
	    // In ra thông tin của từng đối tượng trong danh sách
	    for (Electrical electrical : resultList) {
	        System.out.println("ID: " + electrical.getId());
	        System.out.println("Voltage: " + electrical.getVoltage());
	        System.out.println("Current: " + electrical.getCurrent());
	        System.out.println("Power: " + electrical.getPower());
	        System.out.println("Power Factor: " + electrical.getpF());
	        System.out.println("Temperature: " + electrical.getTemperature());
	        System.out.println("Humidity: " + electrical.getHumidity());
	        System.out.println("Timestamp: " + electrical.getTimeStamp());
	        System.out.println("----------------------");
	    }
	    em.close();
	    return resultList;
	}


	@Override
	public void add_new_value(Electrical electrical_value) {
		EntityManager enma = JPAConfig.getEntityManager();
		javax.persistence.EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(electrical_value);
			trans.commit();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		}
		finally {
			enma.close();
		}
		
	}


	@Override
	public Timestamp get_timestamp() {
	    EntityManager em = JPAConfig.getEntityManager();

	    // Sử dụng JPQL để lấy hàng có ID lớn nhất
	    String jpqlQuery = "SELECT e FROM Electrical e ORDER BY e.id DESC";
	    TypedQuery<Electrical> query = em.createQuery(jpqlQuery, Electrical.class);
	    query.setMaxResults(1); // Giới hạn kết quả trả về chỉ là 1 hàng

	    // Thực hiện truy vấn và trả về kết quả
	    List<Electrical> resultList = query.getResultList();
	    
	    Timestamp temp1 = null;
	    
	    
	    // In ra thông tin của từng đối tượng trong danh sách
	    for (Electrical electrical : resultList) {
	        System.out.println("ID: " + electrical.getId());
	        System.out.println("Voltage: " + electrical.getVoltage());
	        System.out.println("Current: " + electrical.getCurrent());
	        System.out.println("Power: " + electrical.getPower());
	        System.out.println("Power Factor: " + electrical.getpF());
	        System.out.println("Temperature: " + electrical.getTemperature());
	        System.out.println("Humidity: " + electrical.getHumidity());
	        System.out.println("Timestamp: " + electrical.getTimeStamp());
	        System.out.println("----------------------");
	        
	        temp1 = electrical.getTimeStamp();
	    }
	   
	    em.close();
		return temp1;
	}
	
}
