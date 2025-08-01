package com.project.strutsamit.util;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import com.project.strutsamit.common.ChargingDetails;
import com.project.strutsamit.common.exception.ErrorType;
import com.project.strutsamit.common.exception.ResponseObject;



/**
 * @author Neeraj
 * 
 */

public class CrmValidator {
	public static final String batchEmailRegex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[_A-Za-z0-9-]+)+([,.][_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[_A-Za-z0-9-]+))*$";
	public static final String emailRegex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[_A-Za-z0-9-]+)";
	public static final String passwordRegex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@,_+/=]).{8,32}$";
	public static final String amountRegex = "[0-9]+([,.][0-9]{1,2})?";
	public static final String negativeAmountRegex = "-?[0-9]+([,.][0-9]{1,2})?";
	public static final String urlRegex = "^(https?|http?|www.?)[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
	private boolean isValid;
	private ResponseObject resonseObject = new ResponseObject();

	public boolean isValidEmailId(String email) {
		if (email.matches(emailRegex)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isValidBatchEmailId(String email) {
		if (email.matches(batchEmailRegex)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isValidURL(String url) {
		if (url.matches(urlRegex)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isValidPasword(String password) {
		if (password.matches(passwordRegex) && ((password.length() >= CrmFieldType.PASSWORD.getMinLength()))
				&& (password.length() <= CrmFieldType.PASSWORD.getMaxLength())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validateBlankField(String field) {
		if (StringUtils.isBlank(field) || field.equals(Constants.FALSE.getValue())) {
			resonseObject.setResponseMessage(ErrorType.EMPTY_FIELD.getResponseMessage());
			return true;
		}
		return false;
	}

	public boolean validateBlankField(Long field) {
		if (null == field || field == 0) {
			resonseObject.setResponseMessage(ErrorType.EMPTY_FIELD.getResponseMessage());
			return true;
		}
		return false;
	}

	public boolean validateBlankField(int field) {
		if (field == 0) {
			resonseObject.setResponseMessage(ErrorType.EMPTY_FIELD.getResponseMessage());
			return true;
		}
		return false;
	}

	public boolean validateBlankFields(String field) {
		if (StringUtils.isEmpty(field) || field.equals(Constants.FALSE.getValue())) {
			resonseObject.setResponseMessage(ErrorType.EMPTY_FIELDS.getResponseMessage());
			return true;
		}
		return false;
	}

	public boolean validateField(CrmFieldType fieldType, String field) {

		StringBuilder value = new StringBuilder(field);

		if (null == fieldType) {
			resonseObject.setResponseMessage(ErrorType.INVALID_FIELD_VALUE.getResponseMessage());
			return false;
		} else if (value.length() < fieldType.getMinLength()) {
			resonseObject.setResponseMessage(ErrorType.INVALID_FIELD_VALUE.getResponseMessage());
			return false;
		} else if (value.length() > fieldType.getMaxLength()) {
			resonseObject.setResponseMessage(ErrorType.INVALID_FIELD_VALUE.getResponseMessage());
			return false;
		}
		// validate type

		switch (fieldType.getType()) {
		case ALPHA:
			validateAlpha(value, fieldType);
			break;

		case ALPHASPACE:
			validateAlphaSpace(value, fieldType);
			break;

		case ALPHANUM:
			validateAlphaNum(value, fieldType);
			break;

		case EMAIL:
			validateEmail(value, fieldType);
			break;

		case NUMBER:
			validateNumber(value, fieldType);
			break;

		case SPECIAL:
			validateSpecialChar(value, fieldType);
			break;
		case ALPHASPACENUM:
			validateAlphaSpaceNumeric(value, fieldType);
			break;
		case AMOUNT:
			validateAmount(value, fieldType);
			break;
		case URL:
			validateUrl(value, fieldType);
			break;
		default:
			break;
		}
		return getIsValid();
	}

	public void append(StringBuilder value, char inputChar, int length) {
		for (int index = 0; index < length; ++index) {
			value.append(inputChar);
		}
	}

	public void validateAlphaSpaceNumeric(StringBuilder request, CrmFieldType fieldType) {
		final int NUMBER_START = (int) '0';
		final int NUMBER_END = (int) '9';
		final int CAPITAL_ALPHA_START = (int) 'A';
		final int CAPITAL_ALPHA_END = (int) 'Z';
		final int ALPHA_START = (int) 'a';
		final int ALPHA_END = (int) 'z';
		final int SPACE = (int) ' ';
		final int DOT = (int) '.';
		int length = request.length();
		setIsValid(true);
		for (int index = 0; index < length; ++index) {
			char ch = request.charAt(index);
			int ascii = (int) ch;
			if (ascii >= NUMBER_START && ascii <= NUMBER_END) {
				continue;
			} else if (ascii >= CAPITAL_ALPHA_START && ascii <= CAPITAL_ALPHA_END) {
				// allow capital alphabets
				continue;
			} else if (ascii >= ALPHA_START && ascii <= ALPHA_END) {
				// allow small char alphabets
				continue;
			} else if (SPACE == ascii) {
				// allow space in between string
				continue;
			} else if (DOT == ascii) {
				// allow fullstop in between string
				continue;
			} else {
				resonseObject.setResponseMessage(ErrorType.INVALID_FIELD_VALUE.getResponseMessage());
				setIsValid(false);
			}
		}
	}

	public void validateAlphaSpace(StringBuilder request, CrmFieldType fieldType) {

		final int CAPITAL_ALPHA_START = (int) 'A';
		final int CAPITAL_ALPHA_END = (int) 'Z';
		final int ALPHA_START = (int) 'a';
		final int ALPHA_END = (int) 'z';
		final int SPACE = (int) ' ';
		final int DOT = (int) '.';

		int length = request.length();
		setIsValid(true);
		for (int index = 0; index < length; ++index) {
			char ch = request.charAt(index);
			int ascii = (int) ch;

			if (ascii >= CAPITAL_ALPHA_START && ascii <= CAPITAL_ALPHA_END) {
				// allow capital alphabets
				continue;
			} else if (ascii >= ALPHA_START && ascii <= ALPHA_END) {
				// allow small char alphabets
				continue;
			} else if (SPACE == ascii) {
				// allow space in between string
				continue;
			} else if (DOT == ascii) {
				// allow fullstop in between string
				continue;
			} else {
				resonseObject.setResponseMessage(ErrorType.INVALID_FIELD_VALUE.getResponseMessage());
				setIsValid(false);
			}
		}
	} // validate alpha

	public void validateAlpha(StringBuilder request, CrmFieldType fieldType) {

		final int CAPITAL_ALPHA_START = (int) 'A';
		final int CAPITAL_ALPHA_END = (int) 'Z';
		final int ALPHA_START = (int) 'a';
		final int ALPHA_END = (int) 'z';

		int length = request.length();
		setIsValid(true);
		for (int index = 0; index < length; ++index) {
			char ch = request.charAt(index);
			int ascii = (int) ch;

			if (ascii >= CAPITAL_ALPHA_START && ascii <= CAPITAL_ALPHA_END) {
				// allow capital alphabets
				continue;
			} else if (ascii >= ALPHA_START && ascii <= ALPHA_END) {
				// allow small char alphabets
				continue;
			} else {
				resonseObject.setResponseMessage(ErrorType.INVALID_FIELD_VALUE.getResponseMessage());
				setIsValid(false);
			}
		}
	} // validate alpha

	public void validateAlphaNum(StringBuilder request, CrmFieldType fieldType) {

		final int NUMBER_START = (int) '0';
		final int NUMBER_END = (int) '9';
		final int CAPITAL_ALPHA_START = (int) 'A';
		final int CAPITAL_ALPHA_END = (int) 'Z';
		final int ALPHA_START = (int) 'a';
		final int ALPHA_END = (int) 'z';

		setIsValid(true);
		int length = request.length();
		for (int index = 0; index < length; ++index) {
			char ch = request.charAt(index);
			int ascii = (int) ch;

			if (ascii >= NUMBER_START && ascii <= NUMBER_END) {
				continue;
			} else if (ascii >= CAPITAL_ALPHA_START && ascii <= CAPITAL_ALPHA_END) {
				// allow capital alphabets
				continue;
			} else if (ascii >= ALPHA_START && ascii <= ALPHA_END) {
				// allow small char alphabets
				continue;
			} else {
				resonseObject.setResponseMessage(ErrorType.INVALID_FIELD_VALUE.getResponseMessage());
				setIsValid(false);
			}
		}
	}// validate alphanum

	public void validateNumber(StringBuilder request, CrmFieldType fieldType) {

		final int NUMBER_START = (int) '0';
		final int NUMBER_END = (int) '9';

		setIsValid(true);
		int length = request.length();
		for (int index = 0; index < length; ++index) {
			char ch = request.charAt(index);
			int ascii = (int) ch;

			if (ascii >= NUMBER_START && ascii <= NUMBER_END) {
				continue;
			} else {
				resonseObject.setResponseMessage(ErrorType.INVALID_FIELD_VALUE.getResponseMessage());
				setIsValid(false);
			}
		}
	}// validateNumber()

	public void validateSpecialChar(StringBuilder request, CrmFieldType fieldType) {

		final int NUMBER_START = (int) '0';
		final int NUMBER_END = (int) '9';
		final int CAPITAL_ALPHA_START = (int) 'A';
		final int CAPITAL_ALPHA_END = (int) 'Z';
		final int ALPHA_START = (int) 'a';
		final int ALPHA_END = (int) 'z';
		final char[] permittedSpecialChars = fieldType.getSpecialChars().getPermittedSpecialChars();
		request = new StringBuilder(request.toString().replaceAll("\\n", ""));

		setIsValid(true);
		int length = request.length();
		for (int index = 0; index < length; ++index) {
			char ch = request.charAt(index);
			int ascii = (int) ch;

			if (ascii >= NUMBER_START && ascii <= NUMBER_END) {
				// allow numbers
				continue;
			} else if (ascii >= CAPITAL_ALPHA_START && ascii <= CAPITAL_ALPHA_END) {
				// allow capital alphabets
				continue;
			} else if (ascii >= ALPHA_START && ascii <= ALPHA_END) {
				// allow small char alphabets
				continue;
			} else {
				boolean foundFlag = false;
				// allow permitted special chars
				for (char specialChar : permittedSpecialChars) {
					if (specialChar == ch) {
						foundFlag = true;
						break;
					}
				}

				if (foundFlag) {
					continue;
				} else {
					resonseObject.setResponseMessage(ErrorType.INVALID_FIELD_VALUE.getResponseMessage());
					setIsValid(false);
				}
			}
		}
	}// validateSpecialChar()

	public void validateAmount(StringBuilder request, CrmFieldType fieldType) {
		setIsValid(false);
		if (request.toString().matches(amountRegex)) {
			setIsValid(true);
		}
	}// validateAmount()

	public boolean validateNegativeAmount(String request) {
		boolean result = false;
		if (request.matches(negativeAmountRegex)) {
			result = true;
		}
		return result;
	}// validateNegativeAmount() for settlement

	public void validateUrl(StringBuilder request, CrmFieldType fieldType) {
		setIsValid(false);
		if (request.toString().matches(urlRegex)) {
			setIsValid(true);
		}
	}// validateUrl()

	public void validateEmail(StringBuilder request, CrmFieldType fieldType) {
		setIsValid(false);
		if (isValidEmailId(request.toString())) {
			setIsValid(true);
		}
	}// validareEmail

	public String validateRequestParameter(String request) {
		final int NUMBER_START = (int) '0';
		final int NUMBER_END = (int) '9';
		final int CAPITAL_ALPHA_START = (int) 'A';
		final int CAPITAL_ALPHA_END = (int) 'Z';
		final int ALPHA_START = (int) 'a';
		final int ALPHA_END = (int) 'z';
		final char[] permittedSpecialChars = CrmSpecialCharacters.ALL_SPEICIAL_CHARACTERS.getPermittedSpecialChars();
		final char REPLACEMENT_CHAR = ' ';

		StringBuilder value = new StringBuilder(request);
		int length = request.length();
		for (int index = 0; index < length; ++index) {
			char ch = request.charAt(index);
			int ascii = (int) ch;

			if (ascii >= NUMBER_START && ascii <= NUMBER_END) {
				// allow numbers
				continue;
			} else if (ascii >= CAPITAL_ALPHA_START && ascii <= CAPITAL_ALPHA_END) {
				// allow capital alphabets
				continue;
			} else if (ascii >= ALPHA_START && ascii <= ALPHA_END) {
				// allow small char alphabets
				continue;
			} else {
				boolean foundFlag = false;
				// allow permitted special chars
				for (char specialChar : permittedSpecialChars) {
					if (specialChar == ch) {
						foundFlag = true;
						break;
					}
				}

				if (foundFlag) {
					continue;
				} else {
					// Replace invalid characters
					value.setCharAt(index, REPLACEMENT_CHAR);
					break;
				}
			}
		}
		return value.toString();
	}// validateRequestParameters()

	public boolean validateChargingDetailsValues(ChargingDetails chargingDetails) {
		// if(chargingDetails.getMerchantTDR() != (chargingDetails.getPgTDR() +
		// chargingDetails.getBankTDR())){
		// To allow Pg TDR and bank TDR as Zero for Txn below amount 
		if ((chargingDetails.getMerchantServiceTax()== 0.0)) {
			return false;
		}
		if(!(BigDecimal.valueOf(chargingDetails.getMerchantTDR()).stripTrailingZeros()).equals
				((BigDecimal.valueOf(chargingDetails.getPgTDR()).add(BigDecimal.valueOf(chargingDetails.getBankTDR()))).stripTrailingZeros())){			
			return false;
		}
		if(!(BigDecimal.valueOf(chargingDetails.getMerchantTDRAFC()).stripTrailingZeros()).equals
				((BigDecimal.valueOf(chargingDetails.getPgTDRAFC()).add(BigDecimal.valueOf(chargingDetails.getBankTDRAFC()))).stripTrailingZeros())){
			return false;
		}
		/*if (!BigDecimal.valueOf(chargingDetails.getMerchantTDR()).equals(
				BigDecimal.valueOf(chargingDetails.getPgTDR()).add(BigDecimal.valueOf(chargingDetails.getBankTDR())))) {
			return false;
		}
		if (!BigDecimal.valueOf(chargingDetails.getMerchantTDRAFC()).equals(BigDecimal
				.valueOf(chargingDetails.getPgTDRAFC()).add(BigDecimal.valueOf(chargingDetails.getBankTDRAFC())))) {
			return false;
		}*/
		return true;
	}

//	public boolean validateBatchEmailPhone(Invoice invoice) {
//		CrmValidator validator = new CrmValidator();
//		if ((validator.validateBlankField(invoice.getPhone())) || (invoice.getPhone().equalsIgnoreCase(null))) {
//		} else if (!validator.validateField(CrmFieldType.MOBILE, invoice.getPhone())) {
//			return false;
//		}
//		if ((validator.validateBlankField(invoice.getEmail()) || invoice.getEmail().equalsIgnoreCase(null))) {
//		} else if (!validator.validateField(CrmFieldType.EMAILID, invoice.getEmail())) {
//			return false;
//		}
//
//		return true;
//	}
//
//	public boolean validateBinRangeFile(BinRange binRange) {
//		CrmValidator validator = new CrmValidator();
//		if (validator.validateBlankFields(binRange.getBinCode()) || binRange.getBinCode().equalsIgnoreCase(null)) {
//
//		} else if (!validator.validateField(CrmFieldType.BIN_CODE, binRange.getBinCode())) {
//			return false;
//		}
//		if (validator.validateBlankFields(binRange.getCardType().toString()) || binRange.getCardType().toString().equalsIgnoreCase(null)) {
//
//		} else if (!validator.validateField(CrmFieldType.CARD_TYPE, binRange.getCardType().toString())) {
//			return false;
//		}
//		if (validator.validateBlankFields(binRange.getGroupCode()) || binRange.getGroupCode().equalsIgnoreCase(null)) {
//
//		} else if (!validator.validateField(CrmFieldType.GROUP_CODE, binRange.getGroupCode())) {
//			return false;
//		}
//		if (validator.validateBlankFields(binRange.getIssuerBankName())
//				|| binRange.getIssuerBankName().equalsIgnoreCase(null)) {
//
//		} else if (!validator.validateField(CrmFieldType.ISSUER_BANK, binRange.getIssuerBankName())) {
//			return false;
//		}
//		if (validator.validateBlankFields(binRange.getIssuerCountry())
//				|| binRange.getIssuerCountry().equalsIgnoreCase(null)) {
//
//		} else if (!validator.validateField(CrmFieldType.ISSUER_COUNTRY, binRange.getIssuerCountry())) {
//			return false;
//		}
//		if (validator.validateBlankFields(binRange.getMopType().toString()) || binRange.getMopType().toString().equalsIgnoreCase(null)) {
//
//		} else if (!validator.validateField(CrmFieldType.MOP_TYPE, binRange.getMopType().toString())) {
//			return false;
//		}
//		if (validator.validateBlankFields(binRange.getProductName())
//				|| binRange.getProductName().equalsIgnoreCase(null)) {
//
//		} else if (!validator.validateField(CrmFieldType.PRODUCT_NAME, binRange.getProductName())) {
//			return false;
//		}
//
//		return true;
//	}

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public ResponseObject getResonseObject() {
		return resonseObject;
	}

	public void setResonseObject(ResponseObject resonseObject) {
		this.resonseObject = resonseObject;
	}
}
