package vn.iotstar.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import vn.iotstar.Config.JPAConfig;
import vn.iotstar.Entity.Users;

public class UserDaoImpl implements IUserDao {

	@Override
	public List<Users> findAll() {
		EntityManager entityManager = JPAConfig.getEntityManager();
		try {
			TypedQuery<Users> query = entityManager.createQuery("SELECT e FROM users e", Users.class);
			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}

	@Override
	public Users findOne(int id) {
		EntityManager entityManager = JPAConfig.getEntityManager();
		try {
			return entityManager.find(Users.class, id);
		} finally {
			entityManager.close();
		}
	}

	@Override
	public Users findOne(String username) {
		EntityManager entityManager = JPAConfig.getEntityManager();
		try {
			String jpql = "SELECT u FROM Users u WHERE u.username = :username";
			TypedQuery<Users> query = entityManager.createQuery(jpql, Users.class);
			query.setParameter("username", username);
			return query.getSingleResult();
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void insertRegister(Users user) {
		EntityManager entityManager = JPAConfig.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		try {
			transaction.begin();
			entityManager.persist(user);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public boolean checkExistEmail(String email) {
		EntityManager entityManager = JPAConfig.getEntityManager();

		try {
			long count = entityManager.createQuery("SELECT COUNT(u) FROM Users u WHERE u.email = :email", Long.class)
					.setParameter("email", email).getSingleResult();
			return count > 0; // Trả về true nếu email đã tồn tại
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public boolean checkExistUsername(String username) {
		EntityManager entityManager = JPAConfig.getEntityManager();

		try {
			long count = entityManager.createQuery("SELECT COUNT(u) FROM Users u WHERE u.username = :username", Long.class)
					.setParameter("username", username).getSingleResult();
			return count > 0; // Trả về true nếu email đã tồn tại
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}
	@Override
	public void updatestatus(String email, int newStatus, String newCode) {
		EntityManager entityManager = JPAConfig.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		try {
			transaction.begin();

			Users user = entityManager.createQuery("SELECT u FROM Users u WHERE u.email = :email", Users.class).setParameter("email", email).getSingleResult();

			if (user != null) {
				// Cập nhật trường status và code
				user.setStatus(newStatus);
				user.setCode(newCode);

				entityManager.merge(user);
				transaction.commit();
			}
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}
}
