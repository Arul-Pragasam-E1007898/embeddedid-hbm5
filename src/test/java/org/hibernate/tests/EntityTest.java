package org.hibernate.tests;

import com.github.javafaker.Faker;
import org.hibernate.entities.Identity;
import org.hibernate.entities.MediumTextEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityTest {

    private EntityManagerFactory entityManagerFactory;
    private Faker faker;
    private Long accountId;

    @Before
    public void init() {
        faker = Faker.instance();
        accountId = 9200L;
        entityManagerFactory = Persistence.createEntityManagerFactory( "templatePU" );
    }

    @After
    public void destroy() {
        entityManagerFactory.close();
    }

    @Test
    public void read() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        for(long i=0;i<10000;i++) {
            Identity id = Identity.builder().id(i).accountId(accountId).build();
            MediumTextEntity entity = entityManager.find(MediumTextEntity.class, id);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    public void create() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        String paragraph = faker.lorem().characters(1_00_000);

        for(long i=10000;i<1000000;i++) {
            Identity id = Identity.builder().id(i).accountId(accountId).build();
            MediumTextEntity entity = MediumTextEntity.builder().id(id).mediumText(paragraph).build();

            entityManager.persist(entity);
        }
        entityManager.flush();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    public void update() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        String paragraph = faker.lorem().characters(1_00_000);

        for(long i=0;i<1000;i++) {
            Identity id = Identity.builder().id(i).accountId(accountId).build();
            MediumTextEntity entity = entityManager.find(MediumTextEntity.class, id);
            entity.setMediumText(paragraph);

            entityManager.persist(entity);
            entityManager.flush();
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
