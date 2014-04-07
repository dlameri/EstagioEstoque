package com.ideais.stock.dao;

import static org.junit.Assert.fail;

import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Ignore;

@Ignore
public class AbstractDaoTest {
	
	private SessionFactory sessionFactory;
	
	public void setUp(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
//		prepareDatabase();
	}
	
	private Session session() {
		if (sessionFactory == null) {
			throw new SessionException("Session Factory não pode ser nulo.");
		}
		
		return sessionFactory.getCurrentSession();
	}

	private void prepareDatabase() {
		Transaction tx = null;
		try {
			tx = session().beginTransaction();
			session().createSQLQuery("DELETE FROM DIMENSOES").executeUpdate();
			session().createSQLQuery("DELETE FROM IMAGENS").executeUpdate();
			session().createSQLQuery("DELETE FROM ITEM").executeUpdate();
			session().createSQLQuery("DELETE FROM PRODUTO").executeUpdate();
			session().createSQLQuery("DELETE FROM SUBCATEGORIA").executeUpdate();
			session().createSQLQuery("DELETE FROM CATEGORIA").executeUpdate();
			session().createSQLQuery("INSERT INTO CATEGORIA(CD_CATEGORIA, NM_NOME) VALUE(1,'Historia')").executeUpdate();
//			session().createSQLQuery("INSERT INTO SUBCATEGORIA(CD_CATEGORIA, NM_NOME) VALUE(1,'HISTORIA')").executeUpdate();
			tx.commit();
		} catch(Exception e) {
			if(tx != null){
				tx.rollback();
			}
			e.printStackTrace();
			fail("prapare database falhou");
		}finally {
			session().close();
		}
	}
}
