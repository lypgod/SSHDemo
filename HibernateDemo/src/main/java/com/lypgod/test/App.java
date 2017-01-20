package com.lypgod.test;

import com.lypgod.test.Entities.OneToMany.Person;
import com.lypgod.test.Entities.OneToMany.Phone;
import com.lypgod.test.Entities.OneToOne.Husband;
import com.lypgod.test.Entities.OneToOne.Wife;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class App {
    private static EntityManager entityManager;

    @Before
    public void before() throws Exception {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hibernateTest");
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }

    @After
    public void after() throws Exception {
        entityManager.getTransaction().commit();
    }

    @Test
    public void testOneToOne() throws Exception {
        Husband husband = new Husband();
        husband.setName("husband1");
        Wife wife = new Wife();
        wife.setName("wife1");
        husband.setWife(wife);
        wife.setHusband(husband);
        entityManager.persist(husband);

        entityManager.flush();
        entityManager.clear();
        Husband husband1 = entityManager.find(Husband.class, husband.getId());
        System.out.println(husband.getName());
        System.out.println(husband.getWife().getName());
    }

    @Test
    public void testOneToMany() throws Exception {
        Person person = new Person("person1");
        Phone phone1 = new Phone("112233");
        Phone phone2 = new Phone("223344");
        Phone phone3 = new Phone("334455a");
        phone1.setPerson(person);
        phone2.setPerson(person);
        phone3.setPerson(person);
        person.getPhones().add(phone1);
        person.getPhones().add(phone2);
        person.getPhones().add(phone3);
        entityManager.persist(person);

        entityManager.flush();
        entityManager.clear();
        Person person1 = entityManager.find(Person.class, person.getId());
        System.out.println(person1.getName());
        for (Phone p : person1.getPhones()) {
            System.out.println(p.getPhoneNumber());

        }
    }

    @Test
    public void testQuery() throws Exception {
        //        Person person = entityManager.createQuery("from Person where name='person1'", Person.class).getSingleResult();
        //        System.out.println(person.getName());
        //        for (Phone p:person.getPhones()) {
        //            System.out.println(p.getPhoneNumber());
        //        }
        Person person = entityManager.createQuery("from Person where name=:name", Person.class).setParameter("name", "person1").getSingleResult();
        List<Phone> phones = entityManager.createQuery("from Phone where person=:person", Phone.class).setParameter("person", person).getResultList();
        for (Phone p : phones) {
            System.out.println(p.getPhoneNumber());
        }
    }
}
