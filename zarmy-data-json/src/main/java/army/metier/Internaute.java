package army.metier;

import java.util.List;

import army.dao.ArmeDao;
import army.dao.GuerrierDao;
import army.model.Arme;
import army.model.Guerrier;



public class Internaute implements IInternaute {

	protected GuerrierDao guerrierDao;
	protected ArmeDao armeDao;

	public void setGuerrierDao(GuerrierDao guerrierDao) {
		this.guerrierDao = guerrierDao;
	}

	public void setArmeDao(ArmeDao armeDao) {
		this.armeDao = armeDao;
	}

	@Override
	public List<Guerrier> listGuerriers() {
		return guerrierDao.findAll();
	}

	@Override
	public Guerrier getGuerrier(Long idg) {
		return guerrierDao.find(idg);
	}

	@Override
	public List<Arme> listArmes() {
		return armeDao.findAll();
	}

	@Override
	public List<Arme> armesParMotCle(String mc) {
		return armeDao.armesParMotCle(mc);
	}

	@Override
	public List<Arme> armesParGuerrier(Long idg) {
		return armeDao.armesParGuerrier(idg);
	}

	@Override
	public List<Arme> armesSelectionnes() {
		return armeDao.armesSelectionnes();
	}

	@Override
	public Arme getArme(Long ida) {
		return armeDao.find(ida);
	}

//	@Override
//	public Commande enregistrerCommande(Panier p, Client c) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
