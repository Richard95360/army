package army.test;



import org.springframework.context.support.ClassPathXmlApplicationContext;

import army.dao.ArmeDao;
import army.dao.CivilDao;
import army.dao.CommandeDao;
import army.dao.CorqsMilitaireDao;
import army.dao.GuerrierDao;
import army.dao.LigneCommandeDao;
import army.dao.MedecinDao;
import army.dao.UserDao;



public class TestArmy {
	
	private static ClassPathXmlApplicationContext context;

	private static UserDao loginDao;
	private static GuerrierDao guerrierDao;
	private static ArmeDao armeDao;
	private static CommandeDao commandeDao;
	private static LigneCommandeDao ligneCommandeDao;
	private static CivilDao civilDao;
	private static MedecinDao medecinDao;
	private static CorqsMilitaireDao corqsmilitaireDao;


	
	
	public static void main(String[] args) {
		
		
		
		
		context = new ClassPathXmlApplicationContext("applicationContext.xml");

		loginDao = context.getBean(UserDao.class);
		guerrierDao = context.getBean(GuerrierDao.class);
		armeDao = context.getBean(ArmeDao.class);
		commandeDao = context.getBean(CommandeDao.class);
		ligneCommandeDao = context.getBean(LigneCommandeDao.class);
		civilDao = context.getBean(CivilDao.class);
		medecinDao = context.getBean(MedecinDao.class);
		corqsmilitaireDao = context.getBean(CorqsMilitaireDao.class);
		
		
		creation();
		context.close();
	}

	public static void creation() {
	}

}

