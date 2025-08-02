package com.project.strutsamit.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.Proxy;

@Entity
@Proxy(lazy = false)
@Table
public class HelpTicket implements Serializable {

	private static final long serialVersionUID = -5897774594345351594L;

	@Id
	@Column(name = "ticket_id",nullable = false, unique = true)
	private String ticketId;
	@Column(name = "session_userapp_id")
	private String sessionUserappId;
	@Enumerated(EnumType.STRING)
	@Column(name = "ticket_type")
	private TicketType ticketType;
	@Column(name = "contact_email_id")
	private String contactEmailId;
	@Column(name = "contact_mobile_no")
	private String contactMobileNo;
	@Column(name = "concerned_user")
	private String concernedUser;
	@Column(name = "concerned_user_email_id")
	private String concernedUserEmailId;
	@Column(name = "message_body")
	private String messageBody;
	@Column(name = "subject")
	private String subject;
	@Column(name = "assigned_to")
	private String assignedTo;
	@Column(name = "agent_email_id")
	private String agentEmailId;
	@Enumerated(EnumType.STRING)
	@Column(name = "ticket_status")
	private TicketStatus ticketStatus;

	@OneToMany(targetEntity = TicketComment.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "helpticket_ticket_comment",joinColumns = @JoinColumn(name = "HelpTicket_ticketId"),inverseJoinColumns = @JoinColumn(name = "ticketComment_commentId"))
	private Set<TicketComment> ticketComment = new HashSet<TicketComment>();

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getSessionUserappId() {
		return sessionUserappId;
	}

	public void setSessionUserappId(String sessionUserappId) {
		this.sessionUserappId = sessionUserappId;
	}

	public String getContactEmailId() {
		return contactEmailId;
	}

	public void setContactEmailId(String contactEmailId) {
		this.contactEmailId = contactEmailId;
	}

	public String getContactMobileNo() {
		return contactMobileNo;
	}

	public void setContactMobileNo(String contactMobileNo) {
		this.contactMobileNo = contactMobileNo;
	}

	public Set<TicketComment> getTicketComment() {
		return ticketComment;
	}

	public void setTicketComment(Set<TicketComment> ticketComment) {
		this.ticketComment = ticketComment;
	}

	public String getConcernedUser() {
		return concernedUser;
	}

	public void setConcernedUser(String concernedUser) {
		this.concernedUser = concernedUser;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public TicketStatus getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(TicketStatus ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public TicketType getTicketType() {
		return ticketType;
	}

	public void setTicketType(TicketType ticketType) {
		this.ticketType = ticketType;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	public Set<TicketComment> getTicketComments() {
		return ticketComment;
	}

	public void setTicketComments(Set<TicketComment> ticketComment) {
		this.ticketComment = ticketComment;
	}

	public void addTicketComments(TicketComment ticketComment) {
		this.ticketComment.add(ticketComment);
	}

	public void removeTicketComments(TicketComment ticketComment) {
		this.ticketComment.remove(ticketComment);
	}

	public String getConcernedUserEmailId() {
		return concernedUserEmailId;
	}

	public void setConcernedUserEmailId(String concernedUserEmailId) {
		this.concernedUserEmailId = concernedUserEmailId;
	}

	public String getAgentEmailId() {
		return agentEmailId;
	}

	public void setAgentEmailId(String agentEmailId) {
		this.agentEmailId = agentEmailId;
	}

}
