package army.metier;

import army.model.Arme;

public class AdminArme extends Internaute implements IAdminArme {

	@Override
	public void ajouterArme(Arme a) {
		armeDao.create(a);
	}

	@Override
	public void supprimerArme(Arme a) {
		armeDao.delete(a);
	}

	@Override
	public void modifierArme(Arme a) {
		armeDao.update(a);
	}

}


