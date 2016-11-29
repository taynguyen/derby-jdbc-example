/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yooup.example;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author TAY
 */
public class TestMain {
  public static void main(String[] args) {
	 addNewPerson();
        
         getAllPerson();
         
         getPersonById();
         
         updatePerson();
         
         deletePerson();
         
	 System.out.println("Done");
         System.exit(0);
  }

    private static void addNewPerson() throws HibernateException {
        Person person = new Person();
        //person.setId(1);
        person.setName("Tay Nguyen");
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(person);
        session.getTransaction().commit();
        session.close();
    }

    private static void getAllPerson() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            Query query = session.createQuery("from Person");
            List<Person> personList = query.list();
            
            System.out.println("------------------------------");
            System.out.println("Person List: ");
            for (Person person : personList) {
                String personText = String.format("Person id: %s name: %s", person.getId(), person.getName());
                System.out.println(personText);
            }
            
        } finally {
            session.close();
        }
    }

    private static void getPersonById() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            Query query = session.createQuery("from Person p where p.id = 1");
            List<Person> personList = query.list();
            
            System.out.println("------------------------------");
            System.out.println("Person List by id: ");
            personList.stream().map((person) -> String.format("Person id: %s name: %s", person.getId(), person.getName())).forEach((personText) -> {
                System.out.println(personText);
            });
            
        } finally {
            session.close();
        }
    }

    private static void updatePerson() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            
            Query query = session.createQuery("from Person p");
            List<Person> personList = query.list();
            Person personToUpdate = personList.get(0);
            personToUpdate.setName("Updated");
            
            session.save(personToUpdate);
            transaction.commit();
        } finally {
            session.close();
        }
    }

    private static void deletePerson() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            
            Query query = session.createQuery("from Person");
            List<Person> personList = query.list();
            Person personToDelete = personList.get(0);
            
            session.delete(personToDelete);
            transaction.commit();
        } finally {
            session.close();
        }
    }
} 
