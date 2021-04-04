package army.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import army.dao.PacifisteDao;
import army.model.Pacifiste;

public class TestPacifiste {
	
	private static ClassPathXmlApplicationContext context;

	public static void main(String[] args) {
		
		context = new ClassPathXmlApplicationContext("applicationContext.xml");

		m1();

		context.close();


	}

	private static void m1() {
		
		Pacifiste pas = new Pacifiste ("Didier","PeaceInLove");
		
		PacifisteDao dpas = context.getBean(PacifisteDao.class);
		dpas.create(pas);
		
	}

}
