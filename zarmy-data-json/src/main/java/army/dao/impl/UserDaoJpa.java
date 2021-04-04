package army.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import army.dao.UserDao;
import army.model.User;


@Transactional
@Repository
public class UserDaoJpa implements UserDao {

	@PersistenceContext // annotation jpa qui injecte automatiquement l'entity
						// manager
	private EntityManager em;

	@Override
	@Transactional(readOnly=true)
	public User find(Long id) {
		return em.find(User.class, id);

	}

	@Override
	@Transactional(readOnly=true)
	public User find(String username) {
		Query query = em.createQuery("from User l where l.username = :username");
		query.setParameter("username", username);
		List<User> log = query.getResultList();
		return log.size() > 0 ? log.get(0) : null;
	}

	@Override
	@Transactional(readOnly=true)
	public List<User> findAll() {
		Query query = em.createQuery("from User l");
		return query.getResultList();
	}

	@Override
	public void create(User user) {
		em.persist(user);
	}

	// un objet r�cup�r� de la base est d�j� manag� donc les modif se font
	// automatiquement pas besoin d'update
	// on utilise update pour merger objet
	@Override
	public User update(User user) {
		return em.merge(user);

	}

	@Override
	public void delete(User user) {
		em.remove(em.merge(user));

	}

	@Override
	public void delete(Long id) {
		User user = find(id);
		em.remove(user);

	}

	@Override
	@Transactional(readOnly=true)
	public User checkLogin(String username, String password) {
		TypedQuery<User> query = em.createQuery(
				"select l from User as l where l.username = :username AND l.password=:password ", User.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		// getSingleResult() throws an exception if no entity is found
		// User loginEntity = query.getSingleResult();
		List<User> list = query.getResultList();
		if (list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

}
