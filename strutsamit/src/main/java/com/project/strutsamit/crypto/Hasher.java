package com.project.strutsamit.crypto;

import java.security.MessageDigest;
import java.util.Map;
import java.util.TreeMap;

import com.project.strutsamit.common.exception.ErrorType;
import com.project.strutsamit.common.exception.SystemException;
import com.project.strutsamit.util.ConfigurationConstants;
import com.project.strutsamit.util.FieldType;
import com.project.strutsamit.util.Fields;
import com.project.strutsamit.util.PropertiesManager;

import org.apache.commons.codec.binary.Hex;
//import org.apache.log4j.Logger;

//import com.kbn.commons.exception.ErrorType;
//import com.kbn.commons.exception.SystemException;
//import com.kbn.commons.util.ConfigurationConstants;
//import com.kbn.commons.util.FieldType;
//import com.kbn.commons.util.Fields;
//import com.kbn.commons.util.PropertiesManager;

public class Hasher {
	// private static Logger logger = Logger.getLogger(Hasher.class.getName());

	public Hasher() {
	}

	public static String getHash(String input) throws SystemException {
		String response = null;
		// logger.info("Calculated Hash Param:" + input);
		MessageDigest messageDigest = MessageDigestProvider.provide();
		messageDigest.update(input.getBytes());
		MessageDigestProvider.consume(messageDigest);

		response = new String(Hex.encodeHex(messageDigest.digest()));
		// logger.info("Calculated Hash :" + response.toUpperCase());
		return response.toUpperCase();
	}// getSHA256Hex()

	public static String getHash(Fields fields) throws SystemException {

		String hash = fields.get(FieldType.HASH.getName());
		if (hash != null) {
			fields.remove("HASH");
		} else {

		}

		// Append salt of merchant
		String salt = (new PropertiesManager()).getSalt(fields.get(FieldType.APP_ID.getName()));
		if (null == salt) {
			fields.put(FieldType.RESPONSE_CODE.getName(), ErrorType.INVALID_APPID_ATTEMPT.getCode());
			throw new SystemException(ErrorType.INVALID_APPID_ATTEMPT, "Invalid " + FieldType.APP_ID.getName());
		}
		// Sort the request map
		Fields internalFields = fields.removeInternalFields();
		Map<String, String> treeMap = new TreeMap<String, String>(fields.getFields());
		fields.put(internalFields);

		// Calculate the hash string
		StringBuilder allFields = new StringBuilder();
		for (String key : treeMap.keySet()) {
			allFields.append(ConfigurationConstants.FIELD_SEPARATOR.getValue());
			allFields.append(key);
			allFields.append(ConfigurationConstants.FIELD_EQUATOR.getValue());
			allFields.append(fields.get(key));
		}

		allFields.deleteCharAt(0); // Remove first FIELD_SEPARATOR
//		logger.info("Remove first FIELD_SEPARATOR Calculated Hash Param:" + allFields);
		allFields.append(salt);
//		logger.info("Add salt Calculated Hash Param:" + allFields);
		// Calculate hash at server side
		return getHash(allFields.toString());
	}
}
