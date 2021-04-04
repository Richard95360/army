package army.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import army.dao.MedecinDao;
import army.model.Medecin;

public class TestMedecin {
	
	private static ClassPathXmlApplicationContext context;

	public static void main(String[] args) {
		
		context = new ClassPathXmlApplicationContext("applicationContext.xml");

		m1();

		context.close();


	}

	private static void m1() {
		
		Medecin mdc = new Medecin("Chirurgie","Attele","Mr Raimont");
		
		MedecinDao dmd = context.getBean(MedecinDao.class);
		
		dmd.create(mdc);
	}

}
