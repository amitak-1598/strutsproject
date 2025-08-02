package com.project.strutsamit.util;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.project.strutsamit.model.HelpTicket;

public class TicketDao {

	public void saveHelpTicket(HelpTicket ticket) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(ticket);
			transaction.commit();
			System.out.println("Ticket saved successfully!");
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		}
	}

	public HelpTicket getHelpTicketById(String ticketId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(HelpTicket.class, ticketId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
