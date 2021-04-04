package army.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import army.dao.GuerrierDao;
import army.model.Guerrier;

public class TestGuerrier {
	
	private static ClassPathXmlApplicationContext context;

	public static void main(String[] args) {
		
		context = new ClassPathXmlApplicationContext("applicationContext.xml");

		m1();

		context.close();


	}

	private static void m1() {
		
		Guerrier gu = new Guerrier();
		
		GuerrierDao dgu = context.getBean(GuerrierDao.class);
		
		dgu.create(gu);
	}

}
