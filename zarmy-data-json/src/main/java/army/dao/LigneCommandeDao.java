package army.dao;

import java.util.List;

import army.model.LigneCommande;

public interface LigneCommandeDao extends Dao<LigneCommande, Long> {
	

	List<LigneCommande> findByCommande(Long id);


}
