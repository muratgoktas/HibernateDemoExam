package com.hibernateDemo;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(City.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			List<City> cities = session.createQuery(" from City where id=13 ").getResultList();
			for(City city : cities) {
				System.out.println(" Name : "+ city.getName());
				System.out.println("Country Code :"+city.getCountryCode());
			}
			
			session.getTransaction().commit();
			
			} finally {
			factory.close();
		}
	}

}
