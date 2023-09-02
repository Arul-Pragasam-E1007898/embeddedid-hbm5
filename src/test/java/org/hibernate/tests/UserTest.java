package org.hibernate.tests;

import static org.hibernate.testing.transaction.TransactionUtil.doInJPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.entities.user.FdMultitenantUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
public class UserTest {

	private EntityManagerFactory entityManagerFactory;

	@Before
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory( "templatePU" );
	}

	@After
	public void destroy() {
		entityManagerFactory.close();
	}

	// Entities are auto-discovered, so just add them anywhere on class-path
	// Add your tests, using standard JUnit.
	@Test
	public void read() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		// Do stuff...

		doInJPA( ()->entityManagerFactory, em -> {
			FdMultitenantUser fdMultitenantUser = entityManager.find(FdMultitenantUser.class, 1L);
			System.out.println(fdMultitenantUser.getUserEmail().getEmail());
		});

		entityManager.getTransaction().commit();
		entityManager.close();
	}
}