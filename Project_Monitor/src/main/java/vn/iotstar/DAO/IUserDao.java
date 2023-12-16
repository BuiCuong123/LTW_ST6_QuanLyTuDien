package vn.iotstar.DAO;

import java.util.List;

import vn.iotstar.Entity.Users;

public interface IUserDao {

	boolean checkExistEmail(String email);

	void insertRegister(Users user);

	void updatestatus(String email, int newStatus, String newCode);

	Users findOne(String username);

	Users findOne(int id);

	List<Users> findAll();

	boolean checkExistUsername(String username);

}
