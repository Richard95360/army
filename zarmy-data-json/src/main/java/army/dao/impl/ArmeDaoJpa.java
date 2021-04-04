package army.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import army.dao.ArmeDao;
import army.model.Arme;



@Transactional
@Repository
public class ArmeDaoJpa implements ArmeDao {

	@PersistenceContext // annotation jpa qui injecte automatiquement l'entity
						// manager
	private EntityManager em;

//	@Autowired
//	private ReservationDao reservationDao;

	@Override
	@Transactional(readOnly=true)
	public Arme find(Long id) {
		return em.find(Arme.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Arme> findAll() {
		Query query = em.createQuery("select a from Arme a");
		return query.getResultList();
	}

	@Override
	public void create(Arme arme) {
		em.persist(arme);
	}

	@Override
	public Arme update(Arme arme) {
		return em.merge(arme);

	}

	@Override
	public void delete(Arme arme) {
		arme = em.merge(arme);
		em.remove(arme);

	}

	@Override
	public void delete(Long id) {
		Arme arme = find(id);
		em.remove(arme);

	}

	@Override
	@Transactional(readOnly=true)
	public List<Arme> armesParMotCle(String mc) {
		Query query = em.createQuery("select a from Arme a where a.nom like :m or a.description like :m");
		query.setParameter("m", "%"+mc+"%");
		return query.getResultList();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Arme> armesParGuerrier(Long idg) {
		Query query = em.createQuery("select a from Arme a where a.guerrier.id = :i");
		query.setParameter("i", idg);
		return query.getResultList();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Arme> armesSelectionnes() {
		Query query = em.createQuery("select a from Arme a where a.selectionne = true");
		return query.getResultList();
	}

}
