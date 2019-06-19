package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {			
			 
			// start a transaction
			session.beginTransaction();
			
			// 1- query the students
			  
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			// display the students
			displayStudents(theStudents);
			
			// 2- query the students : lastname="doe"
			
			theStudents=session.createQuery("from Student s where s.lastName= 'Duck'").getResultList();
			
			// display the students
			System.out.println("\n \n Students  who have last nameof duck");
			displayStudents(theStudents);
			
			// 3- query the students : lastname="doe" or firstName="Daffy"
			
			theStudents=session.createQuery("from Student s where "
												+ " s.lastName='Duck' OR s.firstName='dsn'")
					.getResultList();
						
			// display the students
			System.out.println("\n \n Students  who have last name of duck and last name of dsn");
			displayStudents(theStudents);
					
			

			// 4- query the students where email like '%luv2code.com'
			
			theStudents=session.createQuery("from Student s where "
												+ " s.email LIKE '%gmail.com'")
					.getResultList();
						
			// display the students
			System.out.println("\n \n Students  who email ends with luv2code.com");
			displayStudents(theStudents);
			
			
			
			
			 // commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent: theStudents) {
			System.out.println(tempStudent);
		}
	}

}





