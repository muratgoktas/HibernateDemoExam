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
		//Unit of work tasarým deseni.
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
		
			
			for(String countryCode: countryCodes ) {
				System.out.println("CountyCode  :"+countryCode);
				
			}
			// INSERT
			City city = new City();
			city.setName("Musul");
			city.setCountryCode("TUR");
			city.setDistrict("Anadolu");
			city.setPopulation(100000);
			session.save(city);
			System.out.println("Insert operation ok. ");
			//READ and UPDATE
			City city2 = session.get(City.class, 4100);
			city2.setPopulation(123444);
			session.save(city2);
			System.out.println("Read and Update operations ok. ");
			// DELETE
			City city3= session.get(City.class, 4101);
			session.delete(city3);
			
			System.out.println("Read and Delete operations ok. ");
			session.getTransaction().commit();
			System.out.println("CRUD operations finihed.");
			} finally {
			factory.close();
		}
	}

}
