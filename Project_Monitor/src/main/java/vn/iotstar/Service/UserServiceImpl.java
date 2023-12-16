package vn.iotstar.Service;

import java.util.List;


import vn.iotstar.DAO.IUserDao;
import vn.iotstar.DAO.UserDaoImpl;
import vn.iotstar.Entity.Users;

public class UserServiceImpl implements IUserService {
	IUserDao userDao = new UserDaoImpl();

	@Override
	public List<Users> findAll(){
		return userDao.findAll();
	}
	
	@Override
	public Users findOne(int id) {
		return userDao.findOne(id);
	}

	@Override
	public Users findOne(String username) {
		return userDao.findOne(username);
	}
	
	@Override
	public boolean register(String email, String password, String username, String fullname, String code) {
		if (userDao.checkExistEmail(email)) {
			return false;
		}
		Users newUser = new Users();
		newUser.setEmail(email);
		newUser.setPassword(password);
		newUser.setUsername(username);
		newUser.setFullname(fullname);
		newUser.setCode(code);
		newUser.setStatus(0);

		userDao.insertRegister(newUser);	
		return true;
	}

	@Override
	public boolean checkExistEmail(String email) {
		return userDao.checkExistEmail(email);
	}
	
	@Override
	public boolean checkExistUsername(String username) {
		return userDao.checkExistUsername(username);
	}
	@Override
	public Users login(String username, String password) {
		Users user = this.findOne(username);
		if (user != null && password.equals(user.getPassword())) {
			return user;
		}
		return null;
	}
	
	@Override
    public void updatestatus(String email, int newStatus, String newCode) {
        try {
            userDao.updatestatus(email, newStatus, newCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
