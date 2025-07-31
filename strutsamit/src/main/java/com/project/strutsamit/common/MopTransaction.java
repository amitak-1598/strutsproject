package com.project.strutsamit.common;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class MopTransaction implements Serializable {

	private static final long serialVersionUID = -5319143866033620832L;
		
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;
	
	public MopTransaction(){
		
	}
	public MopTransaction(TransactionType transactionType){
		this.transactionType= transactionType;
	}
	
	public TransactionType getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
		
}
