package com.project.crm.action;

import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

//import org.apache.log4j.Logger;
//import org.apache.log4j.MDC;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.TokenHelper;

import com.opensymphony.xwork2.ActionContext;
import com.project.crm.common.action.AbstractSecureAction;
import com.project.crm.common.action.LoginAuthenticator;
import com.project.strutsamit.common.CrmFieldConstants;
import com.project.strutsamit.common.LoginHistory;
import com.project.strutsamit.common.LoginHistoryDao;
import com.project.strutsamit.common.PermissionType;
import com.project.strutsamit.common.Permissions;
import com.project.strutsamit.common.Roles;
import com.project.strutsamit.common.User;
import com.project.strutsamit.common.UserType;
import com.project.strutsamit.common.exception.ErrorType;
import com.project.strutsamit.common.exception.ResponseObject;
import com.project.strutsamit.util.Constants;
import com.project.strutsamit.util.CrmActions;
import com.project.strutsamit.util.CrmFieldType;
import com.project.strutsamit.util.CrmValidator;

/**
 * @author neeraj
 * 
 */

public class LoginAction extends AbstractSecureAction implements ServletRequestAware {

//	private static Logger logger = Logger.getLogger(LoginAction.class.getName());
	private static final long serialVersionUID = -5127683348802926510L;

	private String redirectUrl;
	private String emailId;
	private String password;
	//private String captcha;
	//private String captchaCode;
	private HttpServletRequest request;	
	
	private String permissionString = "";
	
	@Override
	public String execute() {

		LoginAuthenticator loginAuthenticator = new LoginAuthenticator();
		
		
		ResponseObject responseObject = new ResponseObject();
		
		LoginHistoryDao loginHistoryDao = new LoginHistoryDao();
		
		LoginHistory loginHistory = new LoginHistory();
		
		User user;
		try {
			String ipAddress = request.getHeader("X-Forwarded-For");  
			if (ipAddress == null) {  
			   ipAddress = request.getRemoteAddr();  
			}
			
			 responseObject = loginAuthenticator.authenticate(getEmailId(), getPassword(),
			 request.getHeader(CrmFieldConstants.USER_AGENT.getValue()),
			 ipAddress);
		
			if (!ErrorType.SUCCESS.getResponseCode().equals(responseObject.getResponseCode())) {
				addFieldError(CrmFieldType.EMAILID.getName(), responseObject.getResponseMessage());
				return INPUT;
			}
			
			//To add custom field to each log for all activities of this user
			//MDC.put(Constants.CRM_LOG_USER_PREFIX.getValue(), getEmailId());
			//logger.info("logged in");

			loginHistory = loginHistoryDao.findLastLoginByUser(getEmailId());

			SessionCleaner.cleanSession(sessionMap);
 			sessionMap = (SessionMap<String, Object>) ActionContext.getContext().getSession();
			
			user = loginAuthenticator.getUser();
			sessionMap.put(Constants.USER.getValue(),user);
			sessionMap.put(Constants.LAST_LOGIN.getValue(),	loginHistory);
			sessionMap.put(Constants.CUSTOM_TOKEN.getValue(), TokenHelper.generateGUID());
			if (user.getUserType().equals(UserType.SUBUSER) || user.getUserType().equals(UserType.SUBACQUIRER) || user.getUserType().equals(UserType.SUBADMIN)) {
				Set<Roles> roles = user.getRoles();
				Set<Permissions> permissions = roles.iterator().next().getPermissions();
				if (!permissions.isEmpty()) {
					StringBuilder perms = new StringBuilder();
					Iterator<Permissions> itr = permissions.iterator();
					while (itr.hasNext()) {
						PermissionType permissionType = itr.next().getPermissionType();
						perms.append(permissionType.getPermission());
						perms.append("-");
					}
					perms.deleteCharAt(perms.length() - 1);
					setPermissionString(perms.toString());
					sessionMap.put(Constants.USER_PERMISSION.getValue(),
							perms.toString());	
				}
			}
			else if(user.getUserType().equals(UserType.SUBADMIN)){
				redirectUrl = user.getLastActionName();
			}
			
			// redirecting to lastActionName
			redirectUrl = user.getLastActionName();
			if(redirectUrl!=null){
				
				//Quick fix for "resellerLists" action (New name is "adminResellers"
				// that was refactored   --Neeraj
				if(redirectUrl.equalsIgnoreCase("resellerLists")){
					redirectUrl = CrmActions.ADMIN_RESELLER_LISTS.getValue();
					return "redirect";
				}
				return "redirect";
			}
			return SUCCESS;
		}catch (Exception exception) {
//			logger.error("Exception", exception);
			return ERROR;
		}
	}	

	public void validate() {

		CrmValidator validator = new CrmValidator();

		if ((validator.validateBlankFields(getEmailId()))) {
			addFieldError(CrmFieldType.EMAILID.getName(), validator.getResonseObject()
					.getResponseMessage());
			return;
		} else if (validator.validateBlankFields(getPassword())) {
			addFieldError(CrmFieldType.EMAILID.getName(), validator.getResonseObject()
					.getResponseMessage());
			return;
		}

		if (!(validator.isValidEmailId(getEmailId()))) {
			addFieldError(CrmFieldType.EMAILID.getName(),
					ErrorType.INVALID_INPUT.getResponseMessage());
			return;
		}

		if (!(validator.isValidPasword(getPassword()))) {
			addFieldError(CrmFieldType.EMAILID.getName(),
					ErrorType.INVALID_INPUT.getResponseMessage());
		}
		/*if (validator.validateBlankField(getCaptcha())) {
			addFieldError(CrmFieldType.CAPTCHA.getName(), validator.getResonseObject().getResponseMessage());
		} 
		else if (!getCaptcha().equals(getCaptchaCode())){
			addFieldError(CrmFieldType.CAPTCHA.getName(), CrmFieldConstants.INVALID_CAPTCHA.getValue());
		}*/
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

/*	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getCaptchaCode() {
		return captchaCode;
	}

	public void setCaptchaCode(String captchaCode) {
		this.captchaCode = captchaCode;
	}*/

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public String getPermissionString() {
		return permissionString;
	}

	public void setPermissionString(String permissionString) {
		this.permissionString = permissionString;
	}	
	
	
}
