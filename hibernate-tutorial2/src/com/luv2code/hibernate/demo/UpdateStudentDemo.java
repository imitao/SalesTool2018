package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {			
			 
			int studentId=1;			
			 
			// get  a new session and start transaction
			session= factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve student based on the id: primary key
			System.out.println(" \n getting student with id: "+ studentId);
			Student myStudent = session.get(Student.class,studentId);
			
			
			System.out.println(" updating student ... ");
			myStudent.setFirstName("Scooby");
		
			
			// commit the transaction
			
			session.getTransaction().commit();

			
			
			// new code
			session=factory.getCurrentSession();
			session.beginTransaction();
			System.out.println("update email for all students");
			session.createQuery("update Student set email='foo@gmail.com'")
			.executeUpdate();
			
			session.getTransaction().commit();

			
			
			
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

}





