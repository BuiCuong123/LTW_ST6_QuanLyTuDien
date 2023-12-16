package vn.iotstar.DAO;

import java.sql.Timestamp;
import java.util.List;

import vn.iotstar.Entity.Electrical;

public interface IElectricalDao {
	
	List<Electrical> findAll();

	public void add_new_value(Electrical electrical_value);

	public Timestamp get_timestamp();
	
}
