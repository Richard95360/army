package army.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import army.dao.ArmeDao;
import army.dao.CommandeDao;
import army.dao.LigneCommandeDao;
import army.model.Arme;
import army.model.Commande;
import army.model.LigneCommande;



@Transactional
@Repository
public class LigneCommandeDaoJpa implements LigneCommandeDao {

	@PersistenceContext // annotation jpa qui injecte automatiquement l'entity
						// manager
	private EntityManager em;

	@Autowired
	private CommandeDao commandeDao;
	@Autowired
	private ArmeDao armeDao;

	@Override
	@Transactional(readOnly=true)
	public LigneCommande find(Long id) {
		return em.find(LigneCommande.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<LigneCommande> findAll() {
		Query query = em.createQuery("from LigneCommande c");
		return query.getResultList();
	}

	@Override
	public void create(LigneCommande ligneCommande) {
		ligneCommande.calculMontant();
		em.persist(ligneCommande);
		majCommande(ligneCommande);
		majProduit(ligneCommande, 0);
	}

	
	// on utilise update pour merger objet
	@Override
	public LigneCommande update(LigneCommande ligneCommande) {
		ligneCommande.calculMontant();
		LigneCommande lcAvantUpdate = find(ligneCommande.getId());
		int quantiteAvantMaj =  lcAvantUpdate.getQuantite();
		ligneCommande = em.merge(ligneCommande);
		majCommande(ligneCommande);
		majProduit(ligneCommande, quantiteAvantMaj);
		return ligneCommande;

	}

	@Override
	public void delete(LigneCommande ligneCommande) {
		ligneCommande = em.merge(ligneCommande);
//		for (Reservation resa : ligneCommande.getReservations()) {
//			reservationDao.delete(resa);
//		}
		em.remove(ligneCommande);

	}

	@Override
	public void delete(Long id) {
		LigneCommande ligneCommande = find(id);
//		for (Reservation resa : ligneCommande.getReservations()) {
//			reservationDao.delete(resa);
//		}
		em.remove(ligneCommande);

	}
	
	@Override
	public List<LigneCommande> findByCommande(Long id) {
//		Query query = em.createQuery("SELECT l FROM LigneCommande l left join fetch l.commande where l.commande.id = :id");
		Query query = em.createQuery("SELECT l FROM LigneCommande l left join fetch l.commande c where c.id = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}

	private void majCommande(LigneCommande ligneCommande) {
		Commande commande = ligneCommande.getCommande();
		List<LigneCommande> lcList = findByCommande(commande.getId());
		int nbProduits = 0;
		Double prixTotal = 0.0;
		for (LigneCommande lc : lcList) {
			nbProduits += lc.getQuantite();
			prixTotal += lc.getMontant();
		}
		commande.setNbArmes(nbProduits);
		commande.setPrixTotal(prixTotal);
		commandeDao.update(commande);
	}

	private void majProduit(LigneCommande ligneCommande, int quantiteAvantMaj) {
		Arme arme = ligneCommande.getArme();
		int stock = arme.getStock() - (ligneCommande.getQuantite() - quantiteAvantMaj);
		arme.setStock(stock);
		armeDao.update(arme);
	}

}
