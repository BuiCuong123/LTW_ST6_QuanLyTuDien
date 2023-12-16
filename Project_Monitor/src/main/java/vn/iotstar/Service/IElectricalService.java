package vn.iotstar.Service;

import java.sql.Timestamp;
import java.util.List;

import vn.iotstar.Entity.Electrical;

public interface IElectricalService {

	List<Electrical> findAll();

	void add_new_value(Electrical electrical_value);
	public Timestamp get_timestamp();
}
