function getMapping() {
	var merchantId = document.getElementById("merchants").value;
	var acquirer = document.getElementById("acquirer").value;

	if (merchantId == "" && acquirer == "") {
		return false;
	}
	if (acquirer == "" || merchantId == "") {
		document.getElementById("err").style.display = "block";
		return false;
	}

	if (merchantId != "" && acquirer != "") {
		var token = document.getElementsByName("token")[0].value;
		$.ajax({
			type : "POST",
			url : "mopSetUpDisplay",
			data : {
				merchantEmailId : merchantId,
				acquirer : acquirer,
				token : token,
				"struts.token.name" : "token"
			},
			success : function(data) {
				refresh();
				var mainDiv = document.getElementById('id+checkBoxes');
				mainDiv.style.display = "block";
				var map = data.mappedString;
				var accountCurrencyArray = data.currencyString;
				if (map == null) {
					if (data.response != null) {
						alert(data.response);
						return false;
					} else {
						alert("Network error please try again later!!");
					}
				} else if (map == "") {
					return false;
				}
				for (j = 0; j < accountCurrencyArray.length; j++) {
					selectCurrency(accountCurrencyArray[j]);
				}
				var tokens = map.split(",");
				for (i = 0; i < tokens.length; i++) {
					var token = tokens[i];
					selectCheckBoxes(token);
				}
			}
		});
	}
}

function selectCurrency(accountCurrencyObj) {
	var currencyCode = accountCurrencyObj.currencyCode;
	var currencyCheckBoxId = "";
	currencyCheckBoxId = currencyCheckBoxId.concat("id+", currencyCode);
	var fieldsDivId = ("boxdiv" + currencyCheckBoxId);
	var currencyCheckbox = document.getElementById(currencyCheckBoxId);
	
	if (currencyCheckbox == null) {
		return;
	}
	var fieldsDiv = document.getElementById(fieldsDivId);
	
	var passwordTextBox = document.getElementById("idpassword+"
			.concat(currencyCode));
	var txnKeyTextBox = document.getElementById("idtxnkey+"
			.concat(currencyCode));
	var merchantIdTextBox = document.getElementById("idmerchantid+"
			.concat(currencyCode));
	var threeDFlag = document.getElementById("id3dflag+".concat(currencyCode));

	passwordTextBox.value = accountCurrencyObj.password;
	txnKeyTextBox.value = accountCurrencyObj.txnKey;
	merchantIdTextBox.value = accountCurrencyObj.merchantId;

	currencyCheckbox.checked = true;
	if(accountCurrencyObj.directTxn){
		threeDFlag.checked = true;
	}
	fieldsDiv.style.display = "block";
}

function selectCheckBoxes(token) {
	var splittedToken = token.split("-");

	var masterCheckBoxId = "";
	masterCheckBoxId = masterCheckBoxId.concat(splittedToken[0], "box")

	var masterCheckbox = document.getElementById(masterCheckBoxId);
	masterCheckbox.checked = true;
	var masterDiv = document.getElementById(splittedToken[0]);
	masterDiv.style.display = "block";

	if (splittedToken.length == 3) {
		var mopDivId = "";
		mopDivId = mopDivId.concat(splittedToken[0], "-", splittedToken[1]);
		var mopDiv = document.getElementById(mopDivId);
		mopDiv.style.display = "block";

		var mopCheckBoxId = "id+";
		mopCheckBoxId = mopCheckBoxId.concat(mopDivId);
		var mopCheckBox = document.getElementById(mopCheckBoxId);
		mopCheckBox.checked = true;
	}
	var txnCheckbox = document.getElementById(token);
	if (null != txnCheckbox) {
		txnCheckbox.checked = true;
	} else {
		alert("Conflict with mapping token fetched and present checkboxes");
	}

}

// to Hide all the checkboxes and disable if no mapping present
function refresh() {
	var creditCard = document.getElementById('Credit Cardbox');
	if (creditCard != null) {
		creditCard.checked = false;
		deselectAllCheckboxesWithinDiv('Credit Card');
		hideAllCheckboxesWithinDiv('Credit Card');
	}

	var debitCard = document.getElementById('Debit Cardbox');
	if (debitCard != null) {
		debitCard.checked = false;
		deselectAllCheckboxesWithinDiv('Debit Card');
		hideAllCheckboxesWithinDiv('Debit Card');
	}

	var netBanking = document.getElementById('Net Bankingbox');
	if (netBanking != null) {
		netBanking.checked = false;
		deselectAllCheckboxesWithinDiv('Net Banking');
		hideAllCheckboxesWithinDiv('Net Banking');
	}

	var wallet = document.getElementById('Walletbox');
	if (wallet != null) {
		wallet.checked = false;
		deselectAllCheckboxesWithinDiv('Wallet');
		hideAllCheckboxesWithinDiv('Wallet');
	}
	
	var UPI = document.getElementById('UPIbox');
	if (UPI != null) {
		UPI.checked = false;
		deselectAllCheckboxesWithinDiv('UPI');
		hideAllCheckboxesWithinDiv('UPI');
	}
	
	var mainDiv = document.getElementById('id+checkBoxes');
	mainDiv.style.display = "none";

}

function hideAllCheckboxesWithinDiv(eleId) {
	var ele = document.getElementById(eleId);
	ele.style.display = "none";
}

function deselectAllCheckboxesWithinDiv(eleId) {
	var collection = document.getElementById(eleId).getElementsByTagName(
			'INPUT');

	for (var x = 0; x < collection.length; x++) {
		if (collection[x].type.toUpperCase() == 'CHECKBOX')
			collection[x].checked = false;
	}
}

function selectAllCheckboxesWithinDiv(eleId) {
	var checkbox = document.getElementById('id+selectAllButton').checked;
	if (checkbox) {
		var collection = document.getElementById(eleId).getElementsByTagName(
				'INPUT');

		for (var x = 0; x < collection.length; x++) {
			if (collection[x].type.toUpperCase() == 'CHECKBOX')
				collection[x].checked = true;
		}
	} else {
		deselectAllCheckboxesWithinDiv(eleId);
	}
}

function selectAllCheckboxesWithinDivWallet(eleId) {
	var checkbox = document.getElementById('id+selectAllButtonWallet').checked;
	if (checkbox) {
		var collection = document.getElementById(eleId).getElementsByTagName('INPUT');

		for (var x = 0; x < collection.length; x++) {
			if (collection[x].type.toUpperCase() == 'CHECKBOX')
				collection[x].checked = true;
		}
	} else {
		deselectAllCheckboxesWithinDiv(eleId);
	}
}
