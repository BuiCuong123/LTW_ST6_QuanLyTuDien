package vn.iotstar.Controller;

import javax.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import vn.iotstar.Config.JPAConfig;
import vn.iotstar.Entity.Electrical;
import vn.iotstar.Service.ElectricalServiceImpl;
import vn.iotstar.Service.IElectricalService;




@WebServlet("/getDataServlet")
public class GetDataServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            try {
        	    EntityManager em = JPAConfig.getEntityManager();
        	    String jpqlQuery = "SELECT e FROM Electrical e ORDER BY e.id DESC";
        	    TypedQuery<Electrical> query = em.createQuery(jpqlQuery, Electrical.class);
        	    query.setMaxResults(1);

        	    List<Electrical> resultList = query.getResultList();
            	
                JSONArray ajax_result = new JSONArray();
                JSONObject get_data_sql = new JSONObject();
        	    
        	    for (Electrical electrical : resultList) {
        	    	get_data_sql.put("ID", electrical.getId());
        	    	get_data_sql.put("voltage", electrical.getVoltage());
        	    	get_data_sql.put("current", electrical.getCurrent());
        	    	get_data_sql.put("power", electrical.getPower());
        	    	get_data_sql.put("power_factor", electrical.getpF());
        	    	get_data_sql.put("temperature", electrical.getTemperature());
        	    	get_data_sql.put("humidity", electrical.getHumidity());
        	    }
        	    em.close();

                ajax_result.put(get_data_sql);
                
                /* send result to client */
                out.println(ajax_result);	
            }
            
            catch (Exception e) {
                e.printStackTrace();
                out.println("{\"error\":\"" + e.getMessage() + "\"}");
            }
        }
    }
}

