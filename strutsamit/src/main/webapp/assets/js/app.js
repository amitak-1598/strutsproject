

function load(){
	
	$('#buttonCards').addClass( "active" );
	$('#contentCARDS').addClass( "show" ).removeClass("hide");
	$('#contentNetBanking').addClass( "hide" ).removeClass("show");
	$('#contentUPI').addClass( "hide" ).removeClass("show");
	$('#contentOtherBankUPIGPay').addClass( "hide" ).removeClass("show");
	$('#contentWallets').addClass( "hide" ).removeClass("show");
	
	$('.bankTypeSelect, input[name="bankType"]').change(onChangeBank);
	
	$('#upiSubmitNew').addClass( "disabled" )
	document.getElementById("upiSubmitNew").setAttribute("disabled", "disabled");
	document.getElementById("vpaCheck").setAttribute("disabled", "disabled");
}

function buttonCards(){
	
	$('#buttonCards').addClass( "active" );
	$('#buttonNetBanking').removeClass( "active" );
	$('#buttonUPI').removeClass( "active" );
	$('#buttonUPIGPay').removeClass( "active" );
	$('#buttonWallets').removeClass( "active" ); 
	
	$('#contentCARDS').addClass( "show" ).removeClass("hide");
	$('#contentNetBanking').addClass( "hide" ).removeClass("show");
	$('#contentUPI').addClass( "hide" ).removeClass("show");
	$('#contentOtherBankUPIGPay').addClass( "hide" ).removeClass("show");
	$('#contentWallets').addClass( "hide" ).removeClass("show");
	
};

function chooseCardType(){
	
	$('#contentDebitCARDS').addClass( "show" ).removeClass("hide");
	$('#contentCreditCARDS').addClass( "hide" ).removeClass("show");
}

function buttonNetBanking(){
	
	$('#buttonCards').removeClass( "active" );
	$('#buttonNetBanking').addClass( "active" );
	$('#buttonUPI').removeClass( "active" );
	$('#buttonUPIGPay').removeClass( "active" );
	$('#buttonWallets').removeClass( "active" );
	
	$('#contentCARDS').addClass( "hide" ).removeClass("show");
	$('#contentNetBanking').addClass( "show" ).removeClass("hide");
	$('#contentUPI').addClass( "hide" ).removeClass("show");
	$('#contentOtherBankUPIGPay').addClass( "hide" ).removeClass("show");
	$('#contentWallets').addClass( "hide" ).removeClass("show");
};

function buttonUPI(){
	enableUpiButton();
	$('#upiSubmitNew').addClass( "disabled" )
	document.getElementById("upiSubmitNew").setAttribute("disabled", "disabled");
	
	$('#buttonCards').removeClass( "active" );
	$('#buttonNetBanking').removeClass( "active" );
	$('#buttonUPI').addClass( "active" );
	$('#buttonUPIGPay').removeClass( "active" );
	$('#buttonWallets').removeClass( "active" );
	
	$('#contentCARDS').addClass( "hide" ).removeClass("show");
	$('#contentNetBanking').addClass( "hide" ).removeClass("show");
	$('#contentUPI').addClass( "show" ).removeClass("hide");
	$('#contentOtherBankUPIGPay').addClass( "hide" ).removeClass("show");
	$('#contentWallets').addClass( "hide" ).removeClass("show");
};

function setUPI(upiTyp){
	$('#upiType').val(upiTyp);
}

function buttonUPIGPay(){
	
	$('#buttonCards').removeClass( "active" );
	$('#buttonNetBanking').removeClass( "active" );
	$('#buttonUPI').removeClass( "active" );
	$('#buttonUPIGPay').addClass( "active" );
	$('#buttonWallets').removeClass( "active" );
	
	$('#contentCARDS').addClass( "hide" ).removeClass("show");
	$('#contentNetBanking').addClass( "hide" ).removeClass("show");
	$('#contentUPI').addClass( "hide" ).removeClass("show");
	$('#contentOtherBankUPIGPay').addClass( "show" ).removeClass("hide");
	$('#contentWallets').addClass( "hide" ).removeClass("show");
};

function buttonWallets(){
	
	$('#buttonCards').removeClass( "active" );
	$('#buttonNetBanking').removeClass( "active" );
	$('#buttonUPI').removeClass( "active" );
	$('#buttonUPIGPay').removeClass( "active" );
	$('#buttonWallets').addClass( "active" );
	
	$('#contentCARDS').addClass( "hide" ).removeClass("show");
	$('#contentNetBanking').addClass( "hide" ).removeClass("show");
	$('#contentUPI').addClass( "hide" ).removeClass("show");
	$('#contentOtherBankUPIGPay').addClass( "hide" ).removeClass("show");
	$('#contentWallets').addClass( "show" ).removeClass("hide");
	
};


function onChangeCard(cardType){
	//alert(cardType);
	if(cardType == 'visaCard'){
	
		$('#visaCardActive').addClass( "active" );
		$('#ruPayCardActive').removeClass("active");
		$('#maestroCardActive').removeClass("active");
		$('#masterCardCardActive').removeClass("active");
		$('#americanExpressCardActive').removeClass("active");
		
	}else if(cardType == 'ruPayCard'){
	
		$('#visaCardActive').removeClass( "active" );
		$('#ruPayCardActive').addClass("active");
		$('#maestroCardActive').removeClass("active");
		$('#masterCardCardActive').removeClass("active");
		$('#americanExpressCardActive').removeClass("active");
	}else if(cardType == 'maestroCard'){
	
		$('#visaCardActive').removeClass( "active" );
		$('#ruPayCardActive').removeClass("active");
		$('#maestroCardActive').addClass("active");
		$('#masterCardCardActive').removeClass("active");
		$('#americanExpressCardActive').removeClass("active");
	}else if(cardType == 'masterCardCard'){
	
		$('#visaCardActive').removeClass( "active" );
		$('#ruPayCardActive').removeClass("active");
		$('#maestroCardActive').removeClass("active");
		$('#masterCardCardActive').addClass("active");
		$('#americanExpressCardActive').removeClass("active");
	}else if(cardType == 'americanExpressCard'){
	
		$('#visaCardActive').removeClass( "active" );
		$('#ruPayCardActive').removeClass("active");
		$('#maestroCardActive').removeClass("active");
		$('#masterCardCardActive').removeClass("active");
		$('#americanExpressCardActive').addClass("active");
	}
	
}

var netBankType = '';
function onChangeNetbankDropdwn(bankType){
	netBankType = '';
	netBankType = bankType;
	return bankType;
}

function enableNetbankingButton(){
	if(netBankType == '1005' || netBankType == '1004' || netBankType == '1013' || netBankType == '1012' || netBankType == '1030' ){
		onChangeBank(netBankType);
	}else{
		$('#axisBankActive').removeClass( "active" );
		$('#HDFCBankActive').removeClass("active");
		$('#ICICIBankActive').removeClass("active");
		$('#KotakBankActive').removeClass("active");
		$('#SBIBankActive').removeClass("active");
	}
}

function onChangeBank(bankType){
	netBankType = '';
	var valueAttribute = '[value="' + bankType + '"]';
    $('.bankTypeSelect option' + valueAttribute).prop('selected', true);
    $('input[name="bankType"]' + valueAttribute).prop('checked', true);
	
	if(bankType == '1005'){
	
		$('#axisBankActive').addClass( "active" );
		$('#HDFCBankActive').removeClass("active");
		$('#ICICIBankActive').removeClass("active");
		$('#KotakBankActive').removeClass("active");
		$('#SBIBankActive').removeClass("active");
		
	}else if(bankType == '1004'){
	
		$('#axisBankActive').removeClass( "active" );
		$('#HDFCBankActive').addClass("active");
		$('#ICICIBankActive').removeClass("active");
		$('#KotakBankActive').removeClass("active");
		$('#SBIBankActive').removeClass("active");
		
	}else if(bankType == '1013'){
	
		$('#axisBankActive').removeClass( "active" );
		$('#HDFCBankActive').removeClass("active");
		$('#ICICIBankActive').addClass("active");
		$('#KotakBankActive').removeClass("active");
		$('#SBIBankActive').removeClass("active");
		
	}else if(bankType == '1012'){
	
		$('#axisBankActive').removeClass( "active" );
		$('#HDFCBankActive').removeClass("active");
		$('#ICICIBankActive').removeClass("active");
		$('#KotakBankActive').addClass("active");
		$('#SBIBankActive').removeClass("active");
		
	}else if(bankType == '1030'){
	
		$('#axisBankActive').removeClass( "active" );
		$('#HDFCBankActive').removeClass("active");
		$('#ICICIBankActive').removeClass("active");
		$('#KotakBankActive').removeClass("active");
		$('#SBIBankActive').addClass("active");
		
	}
}

function onChangeUPI(upiType){
	document.getElementById("vpaCheck").removeAttribute("disabled", "disabled");
	
	if(upiType == 'bhimUPI'){
	
		$('#bhimUPIActive').addClass( "active" );
		$('#mobikwikUPIActive').removeClass("active");
		$('#paytmUPIActive').removeClass("active");
		$('#phonePeUPIActive').removeClass("active");
		
	}else if(upiType == 'mobikwikUPI'){
	
		$('#bhimUPIActive').removeClass( "active" );
		$('#mobikwikUPIActive').addClass("active");
		$('#paytmUPIActive').removeClass("active");
		$('#phonePeUPIActive').removeClass("active");
		
	}else if(upiType == 'paytmUPI'){
	
		$('#bhimUPIActive').removeClass( "active" );
		$('#mobikwikUPIActive').removeClass("active");
		$('#paytmUPIActive').addClass("active");
		$('#phonePeUPIActive').removeClass("active");
		
		
	}else if(upiType == 'phonePeUPI'){
	
		$('#bhimUPIActive').removeClass( "active" );
		$('#mobikwikUPIActive').removeClass("active");
		$('#paytmUPIActive').removeClass("active");
		$('#phonePeUPIActive').addClass("active");
		
		
	}
}

var walletUPIType = '';
function onChangeWalletDropdown(walletType){
	walletUPIType = '';
	walletUPIType = walletType;
	return walletUPIType;
}

function enableWalletButton(){
	if(walletUPIType == '102' || walletUPIType == '101' || walletUPIType == '119' || walletUPIType == '107' || walletUPIType == '116' || walletUPIType == '113' ){
		onChangeWallet(walletUPIType);
	}else{
		$('#mobikwikWalletActive').removeClass( "active" );
		$('#freechargeWalletActive').removeClass( "active" );
		$('#olaMoneyWalletActive').removeClass( "active" );
	}
}



function onChangeWallet(walletType){
	var walletUPIType = '';
	
	var valueAttribute = '[value="' + walletType + '"]';
	//alert(valueAttribute);
    $('.walletTypeSelect option' + valueAttribute).prop('selected', true);
    $('input[name="walletType"]' + valueAttribute).prop('checked', true);
	
	if(walletType == '102'){
		
		$('#mobikwikWalletActive').addClass( "active" );
		$('#freechargeWalletActive').removeClass( "active" );
		$('#olaMoneyWalletActive').removeClass( "active" );
		
	}else if(walletType == '113'){
		
		$('#mobikwikWalletActive').removeClass( "active" );
		$('#freechargeWalletActive').addClass( "active" );
		$('#olaMoneyWalletActive').removeClass( "active" );
		
	}else if(walletType == '107'){
		
		$('#mobikwikWalletActive').removeClass( "active" );
		$('#freechargeWalletActive').removeClass( "active" );
		$('#olaMoneyWalletActive').addClass( "active" );
		
	}
}




/*
function enableWalletButton(){
	
} */