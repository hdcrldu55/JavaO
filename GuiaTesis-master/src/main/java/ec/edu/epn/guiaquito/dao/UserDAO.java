package ec.edu.epn.guiaquito.dao;

import ec.edu.epn.guiaquito.dao.base.BaseDAO;
import ec.edu.epn.guiaquito.entities.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class UserDAO extends BaseDAO<User, String> {

	@Inject
	private EntityManager entityManager;

	public UserDAO() {
		super(User.class);
	}

	public User findByFacebookId(Long facebookId) {
		TypedQuery<User> q = entityManager.createQuery(SELECT_STATEMENT + "User e WHERE e.facebookId = :facebookId", User.class);
		q.setParameter("facebookId", facebookId);
		List<User> resultList = q.getResultList();
		return resultList.size() > 0 ? resultList.get(0) : null;
	}
}
