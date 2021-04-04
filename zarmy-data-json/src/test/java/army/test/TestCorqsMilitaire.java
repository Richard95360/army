package army.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import army.dao.CorqsMilitaireDao;
import army.model.CorqsMilitaire;

public class TestCorqsMilitaire {
	
	private static ClassPathXmlApplicationContext context;

	public static void main(String[] args) {
		
		context = new ClassPathXmlApplicationContext("applicationContext.xml");

		m1();

		context.close();

		
	}

	private static void m1() {
		
		
		CorqsMilitaire cpm = new CorqsMilitaire("Dragon","Para");
		
		CorqsMilitaireDao  dcoq = context.getBean(CorqsMilitaireDao.class);
		dcoq.create(cpm);
		
		
	}

}
