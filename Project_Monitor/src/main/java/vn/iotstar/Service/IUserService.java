package vn.iotstar.Service;

import java.util.List;

import vn.iotstar.Entity.Users;

public interface IUserService {

	boolean checkExistEmail(String email);

	boolean register(String email, String password, String username, String fullname, String code);

	Users login(String username, String password);

	Users findOne(String username);

	Users findOne(int id);

	List<Users> findAll();

	void updatestatus(String email, int newStatus, String newCode);

	boolean checkExistUsername(String username);
}
