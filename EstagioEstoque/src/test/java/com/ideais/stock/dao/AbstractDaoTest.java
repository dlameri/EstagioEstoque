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
@ContextConfiguration({"classpath:spring/applicationContext.xml", "classpath:spring/dataSource.xml"})
@TransactionConfiguration(defaultRollback=false)
@Transactional(propagation=Propagation.REQUIRED)
public class AbstractDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Before
	public void setUp() {
		prepareDatabase();
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
			session().createSQLQuery("INSERT INTO CATEGORIA(CD_CATEGORIA, NM_NOME) VALUE(1,'Historia')").executeUpdate();
//			session().createSQLQuery("INSERT INTO SUBCATEGORIA(CD_CATEGORIA, NM_NOME) VALUE(1,'HISTORIA')").executeUpdate();
	}
}
