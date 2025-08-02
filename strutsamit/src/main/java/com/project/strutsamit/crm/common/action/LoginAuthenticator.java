package com.project.strutsamit.crm.common.action;

import org.apache.commons.lang3.StringUtils;

import com.project.strutsamit.common.LoginHistoryDao;
import com.project.strutsamit.common.User;
import com.project.strutsamit.common.UserDao;
import com.project.strutsamit.common.exception.ErrorType;
import com.project.strutsamit.common.exception.ResponseObject;
import com.project.strutsamit.common.exception.SystemException;
import com.project.strutsamit.util.UserStatusType;



/**
 * @author Neeraj
 *
 */

public class LoginAuthenticator {
		
	private User user = null;

	public ResponseObject authenticate(String emailId, String password,
			String agent, String ip) throws SystemException {

		LoginHistoryDao loginHistoryDao = new LoginHistoryDao();
		ResponseObject responseObject = new ResponseObject();
		UserDao userDao = new UserDao();
		boolean status;
		String failureReason = null;
		
		user = userDao.find(emailId);
		
		if (null == user) {
			// If user is not found, userid is invalid
			responseObject.setResponseMessage(ErrorType.USER_NOT_FOUND
					.getResponseMessage());
			responseObject.setResponseCode(ErrorType.USER_NOT_FOUND
					.getResponseCode());
			return responseObject;
		}

		// Userid is valid
		if (!(user.getUserStatus().equals(UserStatusType.ACTIVE)
				|| user.getUserStatus().equals(UserStatusType.TRANSACTION_BLOCKED) || user.getUserStatus().equals(UserStatusType.SUSPENDED))) {
			responseObject.setResponseMessage(ErrorType.USER_INACTIVE
					.getResponseMessage());
			responseObject.setResponseCode(ErrorType.USER_INACTIVE
					.getResponseCode());
			
			status = false;
			failureReason = ErrorType.USER_INACTIVE.getInternalMessage();
			loginHistoryDao.saveLoginDetails(agent, status, user, ip,failureReason);
			return responseObject;
		}
		
		password = PasswordHasher.hashPassword(password,user.getAppId());
		String userDBPassword = user.getPassword();
		if(StringUtils.isEmpty(userDBPassword)){
			status = false;
			failureReason = ErrorType.USER_PASSWORD_NOT_SET.getInternalMessage();			
			responseObject.setResponseMessage(ErrorType.USER_PASSWORD_NOT_SET
					.getResponseMessage());
			responseObject.setResponseCode(ErrorType.USER_PASSWORD_NOT_SET
					.getResponseCode());
		}else if (userDBPassword.equals(password)) {
			status = true;			
			responseObject.setResponseCode(ErrorType.SUCCESS.getResponseCode());
		} else {
			status = false;
			failureReason = ErrorType.USER_PASSWORD_INCORRECT.getInternalMessage();			
			responseObject.setResponseMessage(ErrorType.USER_PASSWORD_INCORRECT
					.getResponseMessage());
			responseObject.setResponseCode(ErrorType.USER_PASSWORD_INCORRECT
					.getResponseCode());
		}
		loginHistoryDao.saveLoginDetails(agent, status, user, ip, failureReason);
		return responseObject;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
		
}
