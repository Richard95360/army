package army.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import army.dao.RoleDao;
import army.model.Role;
import army.model.User;



@Transactional
@Repository
public class RoleDaoJpa implements RoleDao {

	@PersistenceContext // annotation jpa qui injecte automatiquement l'entity
						// manager
	private EntityManager em;

	@Override
	@Transactional(readOnly=true)
	public Role find(Long id) {
		return em.find(Role.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Role> findAll() {
		Query query = em.createQuery("from Role r");
		return query.getResultList();
	}

	@Override
	public void create(Role role) {
		em.persist(role);
	}

	// un objet recupere de la base est deje manage donc les modif se font
	// automatiquement pas besoin d'update
	// on utilise update pour merger objet
	@Override
	public Role update(Role role) {
		return em.merge(role);
	}

	@Override
	public void delete(Role role) {
		role = em.merge(role);
		em.remove(role);
	}

	@Override
	public void delete(Long id) {
		Role role = find(id);
		em.remove(role);
	}

	@Override
	public void attribuerRole(Role r, Long idUser) {
		User u = em.find(User.class, idUser);
		u.getRoles().add(r);
		em.persist(r);
	}

}
