package army.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import army.dao.MedecinDao;
import army.model.Civil;
import army.model.Medecin;
@Repository
@Transactional
public class MedecinDaoJpa implements MedecinDao {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(readOnly=true)
	public Medecin find(Long id) {
		return em.find(Medecin.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Medecin> findAll() {
//		Query query = em.createQuery("from Client c left outer join fetch c.login");
		Query query = em.createQuery("from Medecin c");
		return query.getResultList();
	}

	@Override
	public void create(Medecin medecin) {
		em.persist(medecin);
	}

	// un objet récupéré de la base est déjà managé donc les modif se font
	// automatiquement pas besoin d'update
	// on utilise update pour merger objet
	@Override
	public Medecin update(Medecin medecin) {
		return em.merge(medecin);

	}

	@Override
	public void delete(Medecin medecin) {
		medecin = em.merge(medecin);
//		for (Reservation resa : client.getReservations()) {
//			reservationDao.delete(resa);
//		}
		
//		if (client.getLogin() != null)
//			em.remove(client.getLogin());

		em.remove(medecin);

	}

	@Override
	public void delete(Long id) {
		Medecin medecin = find(id);
//		for (Reservation resa : client.getReservations()) {
//			reservationDao.delete(resa);
//		}
		
//		if (client.getLogin() != null)
//			em.remove(client.getLogin());
		
		em.remove(medecin);

	}

}
