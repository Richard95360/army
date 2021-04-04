package army.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import army.dao.CivilDao;
import army.model.Civil;


@Transactional
@Repository
public class CivilDaoJpa implements CivilDao {

	@PersistenceContext // annotation jpa qui injecte automatiquement l'entity
						// manager
	private EntityManager em;

//	@Autowired
//	private ReservationDao reservationDao;

	@Override
	@Transactional(readOnly=true)
	public Civil find(Long id) {
		return em.find(Civil.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Civil> findAll() {
//		Query query = em.createQuery("from Client c left outer join fetch c.login");
		Query query = em.createQuery("from Civil c");
		return query.getResultList();
	}

	@Override
	public void create(Civil civil) {
		em.persist(civil);
	}

	// un objet récupéré de la base est déjà managé donc les modif se font
	// automatiquement pas besoin d'update
	// on utilise update pour merger objet
	@Override
	public Civil update(Civil civil) {
		return em.merge(civil);

	}

	@Override
	public void delete(Civil civil) {
		civil = em.merge(civil);
//		for (Reservation resa : client.getReservations()) {
//			reservationDao.delete(resa);
//		}
		
//		if (client.getLogin() != null)
//			em.remove(client.getLogin());

		em.remove(civil);

	}

	@Override
	public void delete(Long id) {
		Civil civil = find(id);
//		for (Reservation resa : client.getReservations()) {
//			reservationDao.delete(resa);
//		}
		
//		if (client.getLogin() != null)
//			em.remove(client.getLogin());
		
		em.remove(civil);

	}

}
