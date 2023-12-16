package vn.iotstar.Service;

import java.sql.Timestamp;
import java.util.List;

import vn.iotstar.DAO.ElectricalDaoImpl;
import vn.iotstar.DAO.IElectricalDao;
import vn.iotstar.Entity.Electrical;

public class ElectricalServiceImpl implements IElectricalService {

	IElectricalDao electricalDao = new ElectricalDaoImpl();


	@Override
	public List<Electrical> findAll() {
		return electricalDao.findAll();
	}
	
	public void add_new_value(Electrical electrical_value) {
		electricalDao.add_new_value(electrical_value);
	}
	
	public Timestamp get_timestamp() {
		return electricalDao.get_timestamp();
	}
}
