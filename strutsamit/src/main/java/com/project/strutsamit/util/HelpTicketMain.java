package com.project.strutsamit.util;

import java.util.UUID;

import com.project.strutsamit.model.HelpTicket;
import com.project.strutsamit.model.TicketComment;
import com.project.strutsamit.model.TicketStatus;
import com.project.strutsamit.model.TicketType;

public class HelpTicketMain {

	 public static void main(String[] args) {
	        TicketDao dao = new TicketDao();

	        HelpTicket ticket = new HelpTicket();
	     
	        ticket.setTicketId(UUID.randomUUID().toString());
	        ticket.setSessionUserappId("user123");
	        ticket.setTicketType(TicketType.PAYMENT);
	        ticket.setContactEmailId("user@example.com");
	        ticket.setContactMobileNo("9876543210");
	        ticket.setConcernedUser("Amit Kumar");
	        ticket.setConcernedUserEmailId("amit@example.com");
	        ticket.setMessageBody("Facing login issue on the dashboard.");
	        ticket.setSubject("Login Error");
	        ticket.setAssignedTo("Support Team");
	        ticket.setAgentEmailId("support@example.com");
	        ticket.setTicketStatus(TicketStatus.OPEN);

	        TicketComment comment = new TicketComment();
	        comment.setCommentId(UUID.randomUUID().toString());
	        comment.setCommentSenderEmailId("support@example.com");
	        comment.setCommentBody("We are checking your issue.");

	        ticket.addTicketComments(comment);
          
	        dao.saveHelpTicket(ticket);
	        System.out.println("Help Ticket Added Successfully ");
	    }
}
