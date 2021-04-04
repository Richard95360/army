package army.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import army.dao.PacifisteDao;

import army.model.Pacifiste;
@Repository
@Transactional
public class PacifisteDaoJpa implements PacifisteDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional(readOnly=true)
	public Pacifiste find(Long id) {
		return em.find(Pacifiste.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Pacifiste> findAll() {
//		Query query = em.createQuery("from Client c left outer join fetch c.login");
		Query query = em.createQuery("from Pacifiste p");
		return query.getResultList();
	}

	@Override
	public void create(Pacifiste pacifiste) {
		em.persist(pacifiste);
	}

	// un objet récupéré de la base est déjà managé donc les modif se font
	// automatiquement pas besoin d'update
	// on utilise update pour merger objet
	@Override
	public Pacifiste update(Pacifiste pacifiste) {
		return em.merge(pacifiste);

	}

	@Override
	public void delete(Pacifiste pacifiste) {
		pacifiste = em.merge(pacifiste);
//		for (Reservation resa : client.getReservations()) {
//			reservationDao.delete(resa);
//		}
		
//		if (client.getLogin() != null)
//			em.remove(client.getLogin());

		em.remove(pacifiste);

	}

	@Override
	public void delete(Long id) {
		Pacifiste pacifiste = find(id);
//		for (Reservation resa : client.getReservations()) {
//			reservationDao.delete(resa);
//		}
		
//		if (client.getLogin() != null)
//			em.remove(client.getLogin());
		
		em.remove(pacifiste);

	}

}


