package com.project.strutsamit.common;

import java.io.Serializable;



/**
 * @author Neeraj
 *
 */
public class SubAdmin implements Serializable {

	private static final long serialVersionUID = -5371568875441496496L;

	public SubAdmin() {

	}

	private String agentEmailId;
	private String agentFirstName;
	private String agentLastName;
	private String appId;
	private String agentExperience;
	private String agentMobile;
	private Boolean agentIsActive;

	public void setSubAdmin(User user) {
		setAgentEmailId(user.getEmailId());
		setAgentFirstName(user.getFirstName());
		setAgentLastName(user.getLastName());
		setAppId(user.getAppId());

	}

	public String getAgentEmailId() {
		return agentEmailId;
	}

	public void setAgentEmailId(String agentEmailId) {
		this.agentEmailId = agentEmailId;
	}

	public String getAgentFirstName() {
		return agentFirstName;
	}

	public void setAgentFirstName(String agentFirstName) {
		this.agentFirstName = agentFirstName;
	}

	public String getAgentLastName() {
		return agentLastName;
	}

	public void setAgentLastName(String agentLastName) {
		this.agentLastName = agentLastName;
	}

	public String getAgentExperience() {
		return agentExperience;
	}

	public void setAgentExperience(String agentExperience) {
		this.agentExperience = agentExperience;
	}

	public String getAgentMobile() {
		return agentMobile;
	}

	public void setAgentMobile(String agentMobile) {
		this.agentMobile = agentMobile;
	}

	public Boolean getAgentIsActive() {
		return agentIsActive;
	}

	public void setAgentIsActive(Boolean agentIsActive) {
		this.agentIsActive = agentIsActive;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	

}
