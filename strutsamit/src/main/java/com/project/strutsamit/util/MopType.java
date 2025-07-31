package com.project.strutsamit.util;

import java.util.ArrayList;
import java.util.List;


public enum MopType {

	// Cards
	VISA		("Visa", "VI"),
	AMEX		("Amex", "AX"),
	DISCOVER	("Discover", "DI"),
	JCB			("JCB", "JC"),
	MASTERCARD	("MasterCard", "MC"),
	MAESTRO		("Maestro", "MS"),
	DINERS		("Diners", "DN"),
	RUPAY		("Rupay", "RU"),
	EZEECLICK	("EzeeClick", "EZ"),

	//DIRECPAY & CITRUSPAY
	AXIS_BANK ("Axis Bank", "1005"),
	BANK_OF_BAHRAIN_AND_KUWAIT ("Bank Of Bahrain And Kuwait", "1043"),
	BANK_OF_INDIA ("Bank Of India", "1009"),
	BANK_OF_MAHARASHTRA ("Bank Of Maharashtra", "1064"),
	CANARA_BANK ("Canara Bank", "1055"),
	CENTRAL_BANK_OF_INDIA ("Central Bank Of India", "1063"),
	CITIBANK ("CitiBank", "1010"),
	CITY_UNION_BANK ("City Union Bank", "1060"),
	CORPORATION_BANK ("Coporation Bank", "1034"),
	DEUTSCHE_BANK ("Deutsche Bank", "1026"),
	DEVELOPMENT_CREDIT_BANK ("Development Credit Bank", "1040"),
	FEDERAL_BANK ("Federal Bank", "1027"), 
	HDFC_BANK ("Hdfc Bank", "1004"),
	ICICI_BANK ("Icici Bank", "1013"),
	INDIAN_BANK ("Indian Bank", "1069"),
	INDIAN_OVERSEAS_BANK ("Indian Overseas Bank", "1049"),
	INDUSIND_BANK ("Indusind Bank", "1054"),
	INDUSTRIAL_DEVELOPMENT_BANK_OF_INDIA ("Industrial Development Bank Of India", "1003"),
	ING_VYSYA_BANK ("IngVysya Bank", "1062"),
	JAMMU_AND_KASHMIR_BANK ("Jammu And Kashmir Bank", "1041"),
	KARNATAKA_BANK_LTD ("Karnatka Bank Ltd", "1032"),
	KARUR_VYSYA_BANK ("KarurVysya Bank", "1048"),
	KOTAK_BANK ("Kotak Bank", "1012"),
	ORIENTAL_BANK_OF_COMMERCE ("Oriental Bank Of Commerce", "1042"),
	RATNAKAR_BANK ("Ratnakar Bank", "1053"),
	SOUTH_INDIAN_BANK ("South Indian Bank", "1045"),
	STATE_BANK_OF_BIKANER_AND_JAIPUR ("State Bank Of Bikaner And Jaipur", "1050"),
	STATE_BANK_OF_HYDERABAD("StateBank Of Hyderabad", "1039"), 
	STATE_BANK_OF_INDIA ("State Bank Of India", "1030"), 
	STATE_BANK_OF_MYSORE ("State Bank Of Mysore", "1037"), 
	STATE_BANK_OF_PATIALA ("State Bank Of Patiala", "1068"),
	STATE_BANK_OF_TRAVANCORE ("State Bank Of Travancore", "1061"),
	TAMILNAD_MERCANTILE_BANK ("Tamilnad Mercantile Bank", "1065"),
	UNION_BANK_OF_INDIA ("Union Bank Of India", "1038"),
	UNITED_BANK_OF_INDIA ("United Bank Of India", "1046"),
	VIJAYA_BANK ("Vijay Bank", "1044"),
	YES_BANK ("Yes Bank", "1001"),

	// Direcpay Only
	DEMO_BANK ("DEMO Bank", "1025"),

	//CITRUSPAY ONLY
	ANDHRA_BANK ("Andhra Bank", "1091"),
	BANK_OF_BRAODA_CORPORATE_ACCOUNTS("Bank of Baroda Corporate Accounts", "1092"),
	BANK_OF_BARODA_RETAIL_ACCOUNTS("Bank of Baroda Retail Accounts", "1093"),
	CATHOLIC_SYRIAN_BANK("Catholic Syrian Bank", "1094"),
	LAKSHMI_VILAS_BANK_NETBANKING("Lakshmi Vilas Bank NetBanking", "1095"),	
	PUNJAB_NATIONAL_BANK_CORPORATE_ACCOUNTS("Punjab National Bank Corporate Accounts", "1096"),
	STANDARD_CHARTERED_BANK("Standard Chartered Bank", "1097"),
	SYNDICATE_BANK ("Syndicate Bank", "1098"),
	AXIS_CORPORATE_BANK ("Axis Corporate Bank", "1099"),
	ICICI_CORPORATE_BANK ("ICICI Corporate Bank", "1100"),
	PNB_CORPORATE_BANK ("PNB Corporate Bank", "1101"),
	HSBC_BANK ("HSBC Bank", "1102"),
	UCO_BANK ("UCO Bank", "1103"),
	COSMOS_BANK ("COSMOS Bank", "1104"),
	
	//BillDesk
	ALLAHABAD_BANK ("Allahabad Bank", "1000"),
	DHANALAXMI_CORPORATE_BANK ("DHANALAXMI BANK CORPORATE", "1126"),
	ANDHRA_CORPORATE_BANK("Andhra Corporate Bank", "1125"),
	ESAF_SMALL_FINANCE_BANK("ESAF SMALL FINANCE BANK", "1127"),
	LAXMI_VILAS_BANK_CORPORATE("Laxmi Vilas Bank - Corporate Net Banking", "1128"),
	PUNJAB_NATIONAL_BANK("Punjab National Bank", "1129"),	
	RATNAKAR_BANK_CORPORATE("RATNAKAR BANK CORPORATE", "1130"),
	SURYODAY_SMALL_FINANCE_BANK("SURYODAY SMALL FINANCE BANK", "1131"),
	SHAMRAO_VITHAL_RETAIL__BANK ("SHAMRAO VITHAL RETAIL BANK", "1132"),
	THANE_BHARAT_SAHAKARI__BANK ("Thane Bharat Sahakari Bank", "1133"),
	TJSB__BANK ("TJSB Bank", "1134"),
	AU_SMALL_FINANCE_BANK ("AU Small Finance Bank", "1135"),
	ZOROASTRIAN_BANK ("Zoroastrian Bank", "1136"),
	DCB_BANK ("DCB BANK", "1105"),
	DENA_BANK ("DENA BANK", "1106"),
	DHANALAXMI_BANK ("DHANALAXMI BANK", "1107"),
	JANTA_SAHAKARI_BANK ("JANTA SAHAKARI BANK", "1108"),
	NKGSB_COOPERATIVE_BANK ("NKGSB CO-OPERATIVE BANK", "1109"),
	PUNJAB_AND_MAHARASHTRA_COOPERATIVE_BANK ("PUNJAB AND MAHARASHTRA COOPERATIVE BANK", "1110"),
	PUNJAB_AND_SIND_BANK ("PUNJAB AND SIND BANK", "1111"),
	SARASWAT_COOPERATIVE_BANK ("SARASWAT COOPERATIVE BANK", "1112"),
	SHAMRAO_VITHAL_COOPERATIVE_BANK ("SHAMRAO VITHAL COOPERATIVE BANK", "1113"),
	TAMILNADU_COOPERATIVE_BANK ("Tamilnadu State Coop Bank", "1114"),
	KALYAN_JANATA_SAHAKARI_BANK ("KALYAN JANATA SAHAKARI BANK", "1116"),
	THE_MEHSANA_URBAN_COOP_BANK_LTD ("THE MEHSANA URBAN CO OP BANK LTD", "1117"),
	BANDHAN_BANK ("BANDHAN BANK", "1118"),
	DIGIBANK_BY_DBS ("DIGIBANK BY DBS", "1119"),
	IDFC_BANK ("IDFC BANK", "1120"),
	BASSIEN_CATHOLIC_COOPERATIVE_BANK ("BASSIEN CATHOLIC CO-OPERATIVE BANK", "1121"),
	PNB_YUVA_BANK ("PNB YUVA BANK", "1122"),
	THE_KALUPUR_COMMERCIAL_COOPERATIVE_BANK_LIMITED ("THE KALUPUR COMMERCIAL COOPERATIVE BANK LIMITED", "1123"),
	EQUITAS_SMALL_FINANCE_BANK ("EQUITAS SMALL FINANCE BANK", "1124"),
	AIRTEL_BANK ("AIRTEL BANK", "1137"),
	//Wallet, existing functionality to get wallet codes changed 18/12/2018, by developer Neeraj yadav
	/*
	PAYTM_WALLET ("PaytmWallet", "PPL"),
	EPAYLATER_WALLET ("EpayLaterWallet", "EPWL"),*/
	
	//Wallets
		DEMO_WALLET ("DEMO Wallet", "100"),
		PAYTM_WALLET ("PAYTM WALLET", "101"),
		MOBIKWIK_WALLET ("MOBIKWIK WALLET", "102"),
		AIRTEL_MONEY_WALLET ("AIRTEL MONEY WALLET", "103"),
		VODAFONE_MPESA_WALLET ("VODAFONE MPESA WALLET", "104"),
		MONEY_ON_MOBILE_WALLET ("MONEY ON MOBILE WALLET", "105"),
		RELIANCE_JIO_WALLET ("RELIANCE JIO WALLET", "106"),
		OLA_MONEY_WALLET ("OLA MONEY WALLET", "107"),
		THE_MOBILE_WALLET ("THE MOBILE WALLET", "108"),
		PNB_WALLET ("PUNJAB NATIONAL BANK WALLET", "109"),
		YES_BANK_WALLET ("YES BANK WALLET", "110"),
		DCB_CIPPY_WALLET ("DCB CIPPY WALLET", "111"),
		ADITYA_BIRLA_WALLET ("ADITYA BIRLA WALLET", "112"),
		FREECHARGE_WALLET ("FREECHARGE WALLET", "113"),
		EBIX_CASH_WALLET ("EBIX CASH WALLET", "114"),
		PHONEPE_WALLET ("PHONEPE WALLET", "115"),
		OXIGEN_WALLET ("OXIGEN WALLET", "116"),
		PAYCASH_WALLET ("PAYCASH WALLET", "117"),
		ICC_CASHCARD_WALLET ("ICC CASHCARD WALLET", "118"),
		EPAYLATER_WALLET ("EPAYLATER WALLET", "119"),
		EZE_CLICK_WALLET ("EZECLICK", "120"),
		JIO_MONEY_WALLET ("JIO MONEY", "121"),
		PAYZAPP_WALLET ("PayZapp", "122"),
		SBIBUDDY_WALLET ("SBI Buddy", "123"),
		Amazon_WALLET ("Amazon Pay", "124"),
       //UPI 
		UPI   ("UPI","UP"),

	//Recurring Payment
	RECURRING_AUTO_DEBIT ("Recurring Auto Debit", "RAD"),
	RECURRING_INVOICE    ("Recurring Invoice", "RIN");

	private final String name;
	private final String code;

	private MopType(String name, String code){
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public String getCode(){
		return code;
	}

	public static MopType getInstance(String name) {
		MopType[] mopTypes = MopType.values();
		for (MopType mopType : mopTypes) {
			if (mopType.getName().equals(name)) {
				return mopType;
			}
		}
		return null;
	}

	// CashFree
	public static List<MopType> getCashFreeCCMops() {
		return getGetMopsFromSystemProp("CASHFREECCMOP");
	}

	public static List<MopType> getCashFreeDCMops() {
		return getGetMopsFromSystemProp("CASHFREEDCMOP");
	}

	// PAYU
	public static List<MopType> getPAYUCCMops() {
		return getGetMopsFromSystemProp("PAYUCCMOP");
	}

	public static List<MopType> getPAYUDCMops() {
		return getGetMopsFromSystemProp("PAYUDCMOP");
	}

	public static List<MopType> getDCMops() {
		return getGetMopsFromSystemProp("DCMOP");
	}

	public static List<MopType> getCCMops() {
		return getGetMopsFromSystemProp("CCMOP");
	}

	public static List<MopType> getWLMops() {
		return getGetMopsFromSystemProp("WALLET");
	}

	public static List<MopType> getFSSDCMops() {
		return getGetMopsFromSystemProp("FSSDCMOP");
	}

	public static List<MopType> getFSSCCMops() {
		return getGetMopsFromSystemProp("FSSCCMOP");
	}

	public static List<MopType> getBARCLAYCCMops() {
		return getGetMopsFromSystemProp("BARCLAYCCMOP");
	}

	public static List<MopType> getCITRUSDCMops() {
		return getGetMopsFromSystemProp("CITRUSDCMOP");
	}

	public static List<MopType> getCITRUSCCMops() {
		return getGetMopsFromSystemProp("CITRUSCCMOP");
	}

	public static List<MopType> getAMEXCCMops() {
		return getGetMopsFromSystemProp("AMEXCCMOP");
	}

	public static List<MopType> getEZEECLICKCCMops() {
		return getGetMopsFromSystemProp("EZEECLICKCCMOP");
	}

	public static List<MopType> getPAYTMWLMops() {
		return getGetMopsFromSystemProp("PAYTMWL");
	}

	public static List<MopType> getMOBIKWIKWLMops() {
		return getGetMopsFromSystemProp("MOBIKWIKWL");
	}

	public static List<MopType> getBILLDESKCCMops() {
		return getGetMopsFromSystemProp("BILLDESKCCMOP");
	}

	public static List<MopType> getBILLDESKDCMops() {

		return getGetMopsFromSystemProp("BILLDESKDCMOP");
	}

	// Start SBI Bank
	public static List<MopType> getSBICCMops() {
		return getGetMopsFromSystemProp("SBICCMOP");
	}

	public static List<MopType> getSBIDCMops() {
		return getGetMopsFromSystemProp("SBIDCMOP");
	}

	// End SBI
	public static List<MopType> getISGPAYCCMops() {
		return getGetMopsFromSystemProp("ISGPAYCCMOP");
	}

	public static List<MopType> getISGPAYDCMops() {
		return getGetMopsFromSystemProp("ISGPAYDCMOP");
	}

	// SafexPay
	public static List<MopType> getSAFEXPAYCCMops() {
		return getGetMopsFromSystemProp("SAFEXPAYCCMOP");
	}

	public static List<MopType> getSAFEXPAYDCMops() {
		return getGetMopsFromSystemProp("SAFEXPAYDCMOP");
	}

	// CyberSource
	// SafexPay
	public static List<MopType> getCYBERSOURCECCMops() {
		return getGetMopsFromSystemProp("CYBERSOURCECCMOP");
	}

	public static List<MopType> getCYBERSOURCEDCMops() {
		return getGetMopsFromSystemProp("CYBERSOURCEDCMOP");
	}

	public static List<MopType> getSAFEXPAYEMIMops() {
		return getGetMopsFromSystemProp("EMISAFEXPAYBANK");
	}

	public static List<MopType> getGetMopsFromSystemProp(String mopsList) {
		PropertiesManager propertiesManager = new PropertiesManager();

		List<String> mopStringList = (List<String>) Helper.parseFields(propertiesManager.getAcquirerMopType(mopsList));

		List<MopType> mops = new ArrayList<MopType>();

		for (String mopCode : mopStringList) {
			MopType mop = getmop(mopCode);
			mops.add(mop);
		}
		return mops;
	}

	public static String getmopName(String mopCode) {
		MopType mopType = MopType.getmop(mopCode);
		if (mopType == null) {
			return "";
		}
		return mopType.getName();
	}

	public static MopType getmop(String mopCode) {
		MopType mopObj = null;
		if (null != mopCode) {
			for (MopType mop : MopType.values()) {
				if (mopCode.equals(mop.getCode().toString())) {
					mopObj = mop;
					break;
				}
			}
		}
		return mopObj;
	}

	public static MopType getInstanceIgnoreCase(String name) {
		MopType[] mopTypes = MopType.values();
		for (MopType mopType : mopTypes) {
			if (mopType.getName().equalsIgnoreCase(name)) {
				return mopType;
			}
		}
		return null;
	}
}
