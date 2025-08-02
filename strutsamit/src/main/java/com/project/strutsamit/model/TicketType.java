package com.project.strutsamit.model;

import org.apache.commons.lang3.StringUtils;


public enum TicketType {
	PAYMENT(211, "PAYMENT"), REFUND(212, "REFUND"), TRANSACTION(213, "TRANSACTION");
	private final int ticketTypeCode;
	private final String ticketType;

	private TicketType(int ticketTypeCode, String ticketType) {
		this.ticketTypeCode = ticketTypeCode;
		this.ticketType = ticketType;
	}

	public int getTicketTypeCode() {
		return ticketTypeCode;
	}

	public String getTicketType() {
		return ticketType;
	}

	public static TicketType getInstance(String tType) {
		TicketType tTypeObj = null;
		TicketType[] ticketTypes = TicketType.values();
		if (!StringUtils.isBlank(tType)) {
			for (TicketType ticketType1 : ticketTypes) {
				if (tType.equals(ticketType1.getTicketType())) {
					tTypeObj = ticketType1;
					break;

				}

			}
		}
		return tTypeObj;
	}
}

