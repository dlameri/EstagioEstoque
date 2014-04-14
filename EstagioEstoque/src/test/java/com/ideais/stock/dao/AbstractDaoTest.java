package com.ideais.stock.dao;

import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Ignore
@ContextConfiguration({"classpath:spring/integrationTestContext.xml", "classpath:spring/dataSource.xml"})
@TransactionConfiguration(defaultRollback=false)
@Transactional(propagation=Propagation.REQUIRED)
public class AbstractDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Before
	public void setUp() {
		prepareDatabase();
//		executeSqlScript("sql/prepare", false);
	}
	
	private Session session() {
		if (sessionFactory == null) {
			throw new SessionException("Session Factory não pode ser nulo.");
		}
		
		return sessionFactory.getCurrentSession();
	}

	private void prepareDatabase() {
			session().createSQLQuery("DELETE FROM IMAGENS").executeUpdate();
			session().createSQLQuery("DELETE FROM ITEM").executeUpdate();
			session().createSQLQuery("DELETE FROM PRODUTO").executeUpdate();
			session().createSQLQuery("DELETE FROM ADMINISTRADOR").executeUpdate();
			session().createSQLQuery("DELETE FROM SUBCATEGORIA").executeUpdate();
			session().createSQLQuery("DELETE FROM CATEGORIA").executeUpdate();
			session().createSQLQuery("DELETE FROM DIMENSOES").executeUpdate();
			session().createSQLQuery("INSERT INTO CATEGORIA VALUES(1, 1, 'Livros')").executeUpdate();
			session().createSQLQuery("INSERT INTO SUBCATEGORIA VALUES(1, 1, 'Historia', 1)").executeUpdate();
			session().createSQLQuery("INSERT INTO ADMINISTRADOR VALUES(1,'admin@teste.com','Admin','98f97621dc9308ce4496edd3ee32c6d583c54f0e8368626697dd6de1daa98675576aba57e92220ef08b89f30e2cbafd8a92646bf5fa92a395d4c1e7133181986')").executeUpdate();
			session().createSQLQuery("INSERT INTO DIMENSOES VALUES(1, 10, 10, 10)").executeUpdate();
			session().createSQLQuery("INSERT INTO PRODUTO VALUES (1,1,'Companhia das Letras','long desc','Brochura','Pequeno Principe',0,'short desc',12,10,1,1,1)").executeUpdate();
			
	}
}
