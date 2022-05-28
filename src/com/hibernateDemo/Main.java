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
			// Hibernate Query language
			@SuppressWarnings("unchecked")
			List<City> cities = session.createQuery(" from City where CountryCode like 'TU%' order by Name desc ").getResultList();
			for(City city : cities) {
				System.out.println(" Name : "+ city.getName());
				System.out.println("Country Code :"+city.getCountryCode());
			}
			
			@SuppressWarnings("unchecked")
			List<String> countryCodes = session.createQuery(" Select c.countryCode  from City c  group by countryCode " ).getResultList();
			session.getTransaction().commit();
			
			for(String countryCode: countryCodes ) {
				System.out.println("CountyCode  :"+countryCode);
				
			}
			} finally {
			factory.close();
		}
	}

}
