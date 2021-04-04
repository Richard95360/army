package army.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import army.dao.GuerrierDao;
import army.model.Guerrier;


@Transactional
@Repository
public class GuerrierDaoJpa implements GuerrierDao {

	@PersistenceContext // annotation jpa qui injecte automatiquement l'entity
						// manager
	private EntityManager em;

//	@Autowired
//	private ReservationDao reservationDao;

	@Override
	@Transactional(readOnly=true)
	public Guerrier find(Long id) {
		return em.find(Guerrier.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Guerrier> findAll() {
		Query query = em.createQuery("select g from Guerrier g");
		return query.getResultList();
	}

	@Override
	public void create(Guerrier guerrier) {
		em.persist(guerrier);
	}

	// un objet récupéré de la base est déjà managé donc les modif se font
	// automatiquement pas besoin d'update
	// on utilise update pour merger objet
	@Override
	public Guerrier update(Guerrier guerrier) {
		return em.merge(guerrier);

	}

	@Override
	public void delete(Guerrier guerrier) {
		guerrier = em.merge(guerrier);
		em.remove(guerrier);

	}

	@Override
	public void delete(Long id) {
		Guerrier guerrier = find(id);
		em.remove(guerrier);

	}

}
