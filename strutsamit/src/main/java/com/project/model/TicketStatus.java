package com.project.model;

import org.apache.commons.lang3.StringUtils;

public enum TicketStatus {
	OPEN(221, "OPEN"), // This is the default status of a ticket when it is created in our help desk.
	PENDING(222, "PENDING"), // A ticket can be moved into pending when an agent has replied to a ticket and is waiting to get more information from a customer.
	RESOLVED(223, "RESOLVED"), // When your agents are reasonably sure that they have provided the right solution to a customer, they can mark it as resolved.
	CLOSED(224, "CLOSED"), // A ticket gets closed when a requester acknowledges that his issue has indeed been resolved by the customer
	TRANSFERRED(225, "TRANSFERRED"), // If a ticket moves to another member of the support team.
	IN_PROGRESS(226, "IN_PROGRESS"); // After a ticket has a status of Solved, a customer can move the ticket to Closed or reopen it.

	private final int statusCode;
	private final String status;

	private TicketStatus(int statusCode, String status) {
		this.statusCode = statusCode;
		this.status = status;
	}

	public String getStatus() {
		return status;

	}

	public int getStatusCode() {
		return statusCode;
	}

	public static TicketStatus getInstance(String tstatus) {
		TicketStatus tStatusObj = null;
		TicketStatus[] ticketStatus = TicketStatus.values();
		if (!StringUtils.isBlank(tstatus)) {
			for (TicketStatus ticketStatus1 : ticketStatus) {
				if (tstatus.equals(ticketStatus1.getStatus())) {
					tStatusObj = ticketStatus1;
					break;

				}

			}
		}
		return tStatusObj;
	}

}

