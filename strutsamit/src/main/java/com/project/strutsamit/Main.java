package com.project.strutsamit;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.project.model.User;
import com.project.strutsamit.util.HibernateUtil;

public class Main {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		User user = new User("Amit Kumar", "amit@example.com", "securepassword");
		session.save(user);

		tx.commit();
		session.close();

		System.out.println("User saved successfully!");
	}
}