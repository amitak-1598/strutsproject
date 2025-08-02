package com.project.strutsamit.crm.common.action;

import com.project.strutsamit.common.exception.ErrorType;
import com.project.strutsamit.common.exception.SystemException;
import com.project.strutsamit.crypto.Hasher;
import com.project.strutsamit.util.PropertiesManager;

/**
 * @author Neeraj
 *
 */
public class PasswordHasher {

	public static String hashPassword(String password,String appId) throws SystemException{
	
		String salt = (new PropertiesManager()).getSalt(appId);	
		if(null==salt){
			throw new SystemException(ErrorType.AUTHENTICATION_UNAVAILABLE, ErrorType.AUTHENTICATION_UNAVAILABLE.getResponseCode());
		}
		
		String hashedPassword = Hasher.getHash(password.concat(salt));		
		return hashedPassword;		
	}		
}

	
