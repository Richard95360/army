package army.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import army.dao.CorqsMilitaireDao;
import army.model.Civil;
import army.model.CorqsMilitaire;


@Repository
@Transactional
public class CorqsMilitaireDaoJpa implements CorqsMilitaireDao{
	
	@PersistenceContext
	private EntityManager em;

//	@Autowired
//	private ReservationDao reservationDao;

	@Override
	@Transactional(readOnly=true)
	public CorqsMilitaire find(Long id) {
		return em.find(CorqsMilitaire.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<CorqsMilitaire> findAll() {
//		Query query = em.createQuery("from Client c left outer join fetch c.login");
		Query query = em.createQuery("from CorqsMilitaire c");
		return query.getResultList();
	}

	@Override
	public void create(CorqsMilitaire corqsMilitaire) {
		em.persist(corqsMilitaire);
	}

	// un objet récupéré de la base est déjà managé donc les modif se font
	// automatiquement pas besoin d'update
	// on utilise update pour merger objet
	@Override
	public CorqsMilitaire update(CorqsMilitaire corqsMilitaire) {
		return em.merge(corqsMilitaire);

	}

	@Override
	public void delete(CorqsMilitaire corqsMilitaire) {
		corqsMilitaire = em.merge(corqsMilitaire);
//		for (Reservation resa : client.getReservations()) {
//			reservationDao.delete(resa);
//		}
		
//		if (client.getLogin() != null)
//			em.remove(client.getLogin());

		em.remove(corqsMilitaire);

	}

	@Override
	public void delete(Long id) {
		CorqsMilitaire corqsMilitaire = find(id);
//		for (Reservation resa : client.getReservations()) {
//			reservationDao.delete(resa);
//		}
		
//		if (client.getLogin() != null)
//			em.remove(client.getLogin());
		
		em.remove(corqsMilitaire);

	}

}

	
	