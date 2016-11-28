/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yooup.example;

import org.hibernate.Session;

/**
 *
 * @author TAY
 */
public class TestMain {
  public static void main(String[] args) {
	 Person person = new Person();
	 //person.setId(1);
	 person.setName("Tay Nguyen");
	 Session session = HibernateUtil.getSessionFactory().openSession();
	 session.beginTransaction();
	 session.save(person);
	 session.getTransaction().commit();
	 session.close();
         
	 System.out.println("Done");
         System.exit(0);
  }
} 
