package org.hibernate.tests;

import static org.hibernate.testing.transaction.TransactionUtil.doInJPA;

import java.time.Instant;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.entities.contacts.ContactEmail;
import org.hibernate.entities.contacts.SalesContact;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
public class ContactTest {

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
			SalesContact contact = entityManager.find(SalesContact.class, 1L);
			System.out.println(contact.getEmails().size());
		});

		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Test
	public void create() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		// Do stuff...

		doInJPA( ()->entityManagerFactory, em -> {
			SalesContact contact = SalesContact.builder().accountId(1679911196L)
				.createdAt(Instant.now()).updatedAt(Instant.now()).status(0).build();
			ContactEmail email = ContactEmail.builder().email("c1@yopmail.com")
				.createdAt(Instant.now()).updatedAt(Instant.now()).build();
			contact.addContactEmail(email);
			SalesContact merged = entityManager.merge(contact);
			entityManager.flush();
			System.out.println(merged.getId());
		});

		entityManager.getTransaction().commit();
		entityManager.close();
	}


	@Test
	public void update() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		// Do stuff...

		doInJPA( ()->entityManagerFactory, em -> {
			SalesContact contact = SalesContact.builder().accountId(1679911196L)
				.createdAt(Instant.now()).updatedAt(Instant.now()).status(0).build();
			ContactEmail email1 = ContactEmail.builder().email("c1@yopmail.com")
				.createdAt(Instant.now()).updatedAt(Instant.now()).build();
			contact.addContactEmail(email1);
			entityManager.persist(contact);

			contact.setFirstName("fname");
			contact.getEmails().remove(email1);
			ContactEmail email2 = ContactEmail.builder().email("c2@yopmail.com")
				.createdAt(Instant.now()).updatedAt(Instant.now()).build();
			contact.addContactEmail(email2);
			entityManager.persist(contact);
			entityManager.flush();

			System.out.println(contact.getId());
		});

		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Test
	public void delete() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		// Do stuff...

		doInJPA( ()->entityManagerFactory, em -> {
			SalesContact contact = SalesContact.builder().accountId(1679911196L)
				.createdAt(Instant.now()).updatedAt(Instant.now()).status(0).build();
			ContactEmail email = ContactEmail.builder().email("c1@yopmail.com")
				.createdAt(Instant.now()).updatedAt(Instant.now()).build();
			contact.addContactEmail(email);
			entityManager.persist(contact);

			entityManager.remove(contact);
		});

		entityManager.getTransaction().commit();
		entityManager.close();
	}
}