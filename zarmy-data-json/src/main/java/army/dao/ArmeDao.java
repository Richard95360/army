package army.dao;

import java.util.List;

import army.model.Arme;

public interface ArmeDao extends Dao<Arme,Long> {
	
	List<Arme> armesParMotCle(String mc);
	List<Arme> armesParGuerrier(Long idg);
	List<Arme> armesSelectionnes();



}
