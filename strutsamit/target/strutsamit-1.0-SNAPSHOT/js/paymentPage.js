/////disable back button ////
{
	history.pushState(null, null, 'paymentPage.jsp');
	window.addEventListener('popstate', function(event) {
		history.pushState(null, null, 'paymentPage.jsp');
	});
}
var arr = [], paymentOptionString, adsImgUrl, adsImglinkUrl, merchantLogo, isIE = /*@cc_on!@*/false || !!document.documentMode, checkApiCallToGpayVal, checkDbEntryVal, cardNumber, pageInfoObj, merchantCurrencyCode, tempCardBin = "", alreadyPopulated = false, ccCopy = "Charges Applicable : 1.8 % plus Applicable Taxes for all domestic Credit Cards.", dcCopy = "Charges Applicable : No charges applicable on transaction amount upto INR 2000. 0.9% plus Applicable Taxes for all transactions above INR 2000.", pcCopy = "Charges Applicable : 1.8 % plus Applicable Taxes for all domestic Prepaid Cards.", upiCopy = "Charges Applicable : No charges Applicable on transaction amount upto INR 2000. INR 10 plus Applicable Taxes for all transactions above INR 2000.", autoDebitCopy = "Charges Applicable : PG Charges INR 90 plus applicable taxes.", iMudraCopy = "Charges Applicable : INR 10 plus Applicable Taxes.", xhrUpi, xhrUpiPay, vpaOldval = null, currentTokenIdSaveCard, oldValue, oldCursor, regexCardNo = new RegExp(
	/^\d{0,19}$/g), keyCodesBack;

var arrEX = [];
var arrEXinput = [];
var arrEXdelButton = [];
var cvvlen3;
var cvvlen4;
var cvvlenDD3;
var amexFlag = false;
var maestroFlag = false;
xhrUpi = null;
xhrUpiPay = null;
xhttp1 = null;
merchantCurrencyCode = "356",

	function setMopType(control) {

		var cardValue = control.value;
		var cardValue1, cardValue2, cardValue3, cardValue4;

		cardValue1 = cardValue.charAt(0);
		cardValue2 = cardValue.charAt(0) + cardValue.charAt(1);
		cardValue3 = cardValue.charAt(0) + cardValue.charAt(1) + cardValue.charAt(2);
		cardValue4 = cardValue.charAt(0) + cardValue.charAt(1) + cardValue.charAt(2) + cardValue.charAt(3);
		amexFlag = false;
		maestroFlag = false;
		if (cardValue1 == "4") {
			document.getElementById('mopType').value = "VI"; //visa
		} else if (cardValue2 == "51" || cardValue2 == "52" || cardValue2 == "53" || cardValue2 == "54" || cardValue2 == "55") {
			document.getElementById('mopType').value = "MC"; //master
		} else if (cardValue4 == "5018" || cardValue4 == "5020" || cardValue4 == "5038" || cardValue4 == "5612" || cardValue4 == "5893" || cardValue4 == "6304" || cardValue4 == "6759" || cardValue4 == "6220" || cardValue4 == "0604" || cardValue4 == "6390" || cardValue4 == "6799") {
			document.getElementById('mopType').value = "MS"; //maestro
			maestroFlag = true;
			MaestroNote();
		} else if (cardValue2 == "34" || cardValue2 == "37") {
			document.getElementById('mopType').value = "AX"; //amex
			amexFlag = true;
		} else if (cardValue2 == "36" || cardValue2 == "38") {
			document.getElementById('mopType').value = "DN"; //diners
		} else if (cardValue2 == "60" || cardValue2 == "65") {
			document.getElementById('mopType').value = "RU"; //rupay
		} else if (cardValue3 == "508" || cardValue3 == "653" || cardValue3 == "652") {
			document.getElementById('mopType').value = "RU"; //rupay
		} else if (cardValue3 == "300" || cardValue3 == "301" || cardValue3 == "302" || cardValue3 == "303" || cardValue3 == "304" || cardValue3 == "305") {
			document.getElementById('mopType').value = "DN"; //diners
		}

		cvvlen3 = document.getElementById("cvvTextImage").value;
		cvvlen4 = document.getElementById("cvvTextImageAX").value;

		// set cvv length
		if (cardValue2 == "34" || cardValue2 == "37") {
			var cvvTextAX = document.getElementById('cvvtext');
			cvvTextAX.innerHTML = cvvlen4;
			cvvTextAX.className = "transparent";
			document.getElementById('cvvNumber').value = "";
			document.getElementById('cvvNumber').maxLength = 4;
		}
		else {
			document.getElementById('cvvNumber').value = "";
			document.getElementById('cvvNumber').maxLength = 3;

			if (document.getElementById('creditCard') != null) {
				var cvvText = document.getElementById('cvvtext');
				cvvText.innerHTML = cvvlen3;
				cvvText.className = "transparent";
			}
		}
	}
function checkCardSupported() {
	var notSupportedCard = document.getElementById("cardTypeMsg").value;

	if (document.getElementById('mopType').value == "VI" && !(document.getElementById('checkmopTypeVI'))) {
		document.getElementById('cardSupported').innerHTML = notSupportedCard;
	}
	else if (document.getElementById('mopType').value == "MC" && !(document.getElementById('checkmopTypeMC'))) {
		document.getElementById('cardSupported').innerHTML = notSupportedCard;
	}
	else if (document.getElementById('mopType').value == "MS" && !(document.getElementById('checkmopTypeMS'))) {
		document.getElementById('cardSupported').innerHTML = notSupportedCard;
	}
	else if (document.getElementById('mopType').value == "AX" && !(document.getElementById('checkmopTypeAX'))) {
		document.getElementById('cardSupported').innerHTML = notSupportedCard;
	}
	else if (document.getElementById('mopType').value == "DN" && !(document.getElementById('checkmopTypeDN'))) {
		document.getElementById('cardSupported').innerHTML = notSupportedCard;
	}
	else if (document.getElementById('mopType').value == "RU" && !(document.getElementById('checkmopTypeRU'))) {
		document.getElementById('cardSupported').innerHTML = notSupportedCard;
	}
	else {
		document.getElementById('cardSupported').innerHTML = "";
	}
}

/*function enablePayButton(v) {
	 if ((maestroFlag == true) && document.getElementById("demo").innerHTML != "Enter valid Card number") { //check for maestro card
         if (document.getElementById('cardNumber').value != "" && document.getElementById('cardName').value != "" && document.getElementById('chkExpiry').innerHTML != "Enter valid expiry date") {

             document.getElementById('ccSubmit').disabled = false;
             document.getElementById('ccSubmit').className = "btn-orange";
         } else {
             document.getElementById('ccSubmit').disabled = true;
             document.getElementById('ccSubmit').className = "btn-orange disabled";
         }
     } else{
	        if (document.getElementById('cardName').value != "" && document.getElementById('cardNumber').value != "" && document.getElementById('cvvNumber').value != ""  && document.getElementById('ccExpiryMonth').selectedIndex != 0 && document.getElementById('ccExpiryYear').selectedIndex != 0 && document.getElementById("demo").innerHTML != "Enter valid Card number" && document.getElementById('cvvNumber').value.length >= 3 && document.getElementById('chkExpiry').innerHTML != "Enter valid expiry date" && document.getElementById('cardSupported').innerHTML != "This card is no longer supported") {
            if (amexFlag == true && document.getElementById('cvvNumber').value.length == 4) {
                document.getElementById('ccSubmit').disabled = false;
                document.getElementById('ccSubmit').className = "btn-orange";
            } 
            else {							
				document.getElementById('ccSubmit').disabled=true;
				document.getElementById('ccSubmit').className = "btn-orange disabled";
			}
            if (amexFlag == false &&  document.getElementById('cvvNumber').value.length == 3) {
				  document.getElementById('ccSubmit').disabled=false;
				  document.getElementById('ccSubmit').className = "btn-orange";
				}
        }
        else {
            document.getElementById('ccSubmit').disabled = true;
            document.getElementById('ccSubmit').className = "btn-orange disabled";
        }
    }
}*/


function checkLuhn(input) // same for cc/dc
{
	var sum = 0;
	var cnumber = input.value.replace(/\s/g, '');
	var numdigits = cnumber.length;
	var parity = numdigits % 2;
	for (var i = 0; i < numdigits; i++) {
		var digit = parseInt(cnumber.charAt(i))
		if (i % 2 == parity) digit *= 2;
		if (digit > 9) digit -= 9;
		sum += digit;
	}
	var result = ((sum % 10) == 0);
	if (result == false) {
		if (input.id == "cardNumber" && document.getElementById("demo") != null) {
			var entrValidcardnumber = document.getElementById("validCardDetail").value;
			document.getElementById("demo").innerHTML = entrValidcardnumber;
			return false;
		}

	} else {
		try {
			document.getElementById("demo").innerHTML = "";
		} catch (err) { }
		return true;
	}

}

function CheckExpiry() {
	var today, someday;
	var exMonth = document.getElementById("ccExpiryMonth").value;
	var exYear = document.getElementById("ccExpiryYear").value;
	today = new Date();
	someday = new Date();
	someday.setFullYear(exYear, exMonth, 1);
	if (document.getElementById('ccExpiryYear').selectedIndex != 0) {
		if (someday < today) {
			var entrValidexpirydate = document.getElementById("validExpiryDate").value;
			document.getElementById("chkExpiry").innerHTML = entrValidexpirydate;
			return false;

		} else {
			document.getElementById("chkExpiry").innerHTML = "";
			return true;
		}

	}

}

function Show() // for cc
{
	x = event.clientX + document.body.scrollLeft; // get the mouse left position
	y = event.clientY + document.body.scrollTop + 35; // get the mouse top position 
	cvvtext.style.display = "block"; // display the pop-up
	cvvtext.style.left = x; // set the pop-up's left
	cvvtext.style.top = y; // set the pop-up's top
}

function Hide() //  for cc
{
	cvvtext.style.display = "none"; // hide the pop-up
}

function isCharacterKey(event) {
	var k;
	document.all ? k = event.keyCode : k = event.which;
	return ((k > 64 && k < 91) || (k > 96 && k < 123) || (k == 8));
}

function noSpace(event, inputName) { // same for cc/dc
	var str, str0, cardName;
	cardName = inputName.id
	str = inputName.value;
	str0 = str.charAt(0);
	if (str0 == "") {
		cardName.value = "";
		//return false;
	} else {
		if (event.charCode == 32) {
			if (cardName == "cardName") {
				document.getElementById('cardName').value = str + ' ';
			}
		}
	}
}

function submitCCform() {
	document.getElementById('ccSubmit').disabled = true;
	document.getElementById('ccSubmit').className = "btn-orange disabled";
	document.getElementById('ccCancelButton').style.display = "none";
	return true;
}

function isNumberKey(evt) //for both cc/dc
{
	var charCode = (evt.which) ? evt.which : event.keyCode

	if (charCode > 31 && (charCode < 48 || charCode > 57))
		return false;

	return true;
}


function MaestroNote() {
	var maestroNoteD = document.getElementById("maestroNoteD").value;
	document.getElementById("maestroNoteDspan").innerHTML = maestroNoteD;
}

///////////////////////netbanking/////////////
function onChange(id) {
	var value = document.getElementById(id).value;
	if (value == "SBI") {
		ddlNetbanking.options[ddlNetbanking.selectedIndex].text = "State Bank of India";
		ddlNetbanking.options[ddlNetbanking.selectedIndex].value = "1030";
	} else if (value == "HDFC") {
		ddlNetbanking.options[ddlNetbanking.selectedIndex].text = "Hdfc Bank";
		ddlNetbanking.options[ddlNetbanking.selectedIndex].value = "1004";
	} else if (value == "ICICI") {
		ddlNetbanking.options[ddlNetbanking.selectedIndex].text = "Icici Bank";
		ddlNetbanking.options[ddlNetbanking.selectedIndex].value = "1013";
	} else if (value == "AXIS") {
		ddlNetbanking.options[ddlNetbanking.selectedIndex].text = "Axis Bank";
		ddlNetbanking.options[ddlNetbanking.selectedIndex].value = "1005";
	} else if (value == "CITI") {
		ddlNetbanking.options[ddlNetbanking.selectedIndex].text = "Citi Bank";
		ddlNetbanking.options[ddlNetbanking.selectedIndex].value = "1010";
	} else if (value == "KOTAK") {
		ddlNetbanking.options[ddlNetbanking.selectedIndex].text = "Kotak Bank";
		ddlNetbanking.options[ddlNetbanking.selectedIndex].value = "1012";
	}

}

function onChangeNetbankDropdwn(value) {

	if (value == "1030") {
		document.getElementById('sbi').checked = true;
	} else if (value == "1004") {
		document.getElementById('hdfc').checked = true;
	} else if (value == "1013") {
		document.getElementById('icici').checked = true;
	} else if (value == "1005") {
		document.getElementById('axis').checked = true;
	} else if (value == "1010") {
		document.getElementById('citi').checked = true;
	} else if (value == "1012") {
		document.getElementById('kotak').checked = true;
	} else {
		if (document.getElementById('sbi') != null) {
			document.getElementById('sbi').checked = false;
		}
		if (document.getElementById('hdfc') != null) {
			document.getElementById('hdfc').checked = false;
		}
		if (document.getElementById('icici') != null) {
			document.getElementById('icici').checked = false;
		}
		if (document.getElementById('axis') != null) {
			document.getElementById('axis').checked = false;
		}
		if (document.getElementById('citi') != null) {
			document.getElementById('citi').checked = false;
		}
		if (document.getElementById('kotak') != null) {
			document.getElementById('kotak').checked = false;
		}
	}

}

function enableNetbankingButton() {
	if (document.getElementById('ddlNetbanking').selectedIndex.Text == "Select Bank") {
		document.getElementById('nbSubmit').disabled = true;
		document.getElementById('nbSubmit').className = "btn-orange disabled";
	} else {
		document.getElementById('nbSubmit').disabled = false;
		document.getElementById('nbSubmit').className = "btn-orange";
	}
}

function submitNBform() {
	document.getElementById('nbSubmit').disabled = true;
	document.getElementById('nbSubmit').className = "btn-orange disabled";
	document.getElementById('netCancelButton').style.display = "none";
	return true;
}
/////////////////Wallet////////////////
function onChangeWallet(id) {
	var value = document.getElementById(id).value;

	if (value == "MOBIKWIK") {
		ddlWallet.options[ddlWallet.selectedIndex].text = "MOBIKWIK WALLET";
		ddlWallet.options[ddlWallet.selectedIndex].value = "102";
	} else if (value == "PAYTM") {
		ddlWallet.options[ddlWallet.selectedIndex].text = "PAYTM WALLET";
		ddlWallet.options[ddlWallet.selectedIndex].value = "101";
	} else if (value == "EPAYLATER") {
		ddlWallet.options[ddlWallet.selectedIndex].text = "EPAYLATER WALLET";
		ddlWallet.options[ddlWallet.selectedIndex].value = "119";
	}

	else if (value == "OLAMONEY") {
		ddlWallet.options[ddlWallet.selectedIndex].text = "OLA MONEY WALLET";
		ddlWallet.options[ddlWallet.selectedIndex].value = "107";
	}

	else if (value == "OXIGEN") {
		ddlWallet.options[ddlWallet.selectedIndex].text = "OXIGEN WALLET";
		ddlWallet.options[ddlWallet.selectedIndex].value = "116";
	}

	else if (value == "FREECHARGE") {
		ddlWallet.options[ddlWallet.selectedIndex].text = "FREECHARGE WALLET";
		ddlWallet.options[ddlWallet.selectedIndex].value = "113";
	}
}

function onChangeWalletDropdown(value) {

	if (value == "102") {
		document.getElementById('mobikwikWallet').checked = true;
	} else if (value == "101") {
		document.getElementById('paytmWallet').checked = true;
	} else if (value == "119") {
		document.getElementById('epayLaterWallet').checked = true;
	}
	else if (value == "107") {
		document.getElementById('olaWallet').checked = true;
	} else if (value == "116") {
		document.getElementById('oxigenWallet').checked = true;
	} else if (value == "113") {
		document.getElementById('freechargeWallet').checked = true;
	}
	else {
		if (document.getElementById('mobikwikWallet') != null) {
			document.getElementById('mobikwikWallet').checked = false;
		}
		if (document.getElementById('paytmWallet') != null) {
			document.getElementById('paytmWallet').checked = false;
		}
		if (document.getElementById('epayLaterWallet') != null) {
			document.getElementById('epayLaterWallet').checked = false;
		}

		if (document.getElementById('olaWallet') != null) {
			document.getElementById('olaWallet').checked = false;
		}
		if (document.getElementById('oxigenWallet') != null) {
			document.getElementById('oxigenWallet').checked = false;
		}
		if (document.getElementById('freechargeWallet') != null) {
			document.getElementById('freechargeWallet').checked = false;
		}
	}
}

function enableWalletButton() {
	if (document.getElementById('ddlWallet').selectedIndex.Text == "Select Wallet") {
		document.getElementById('wlSubmit').disabled = true;
		document.getElementById('wlSubmit').className = "btn-orange disabled";
	} else {
		document.getElementById('wlSubmit').disabled = false;
		document.getElementById('wlSubmit').className = "btn-orange";
	}
}
function submitWLform() {
	document.getElementById('wlSubmit').disabled = true;
	document.getElementById('wlSubmit').className = "btn-orange disabled";
	document.getElementById('wtCancelButton').style.display = "none";
	return true;
}
//////////////Express Pay////////////
function getExForm() {
	if (document.getElementById("exCard") != null) {
		var form = document.getElementById('exCard');
		var elements = form.elements;
		var counter = 0;
		var counterCvv = 0;
		var counterDelButton = 0;
		for (var i = 0; i < elements.length; i++) {
			if (elements[i].type.toLowerCase() == 'radio') {
				arrEX[counter] = elements[i]
				counter++;
			}
			if (elements[i].type.toLowerCase() == 'password') {
				arrEXinput[counterCvv] = elements[i]
				counterCvv++;
			}
			if (elements[i].type.toLowerCase() == 'button') {
				arrEXdelButton[counterDelButton] = elements[i]
				counterDelButton++;
			}
		}
	}
}

function handleClick(myRadio) {
	var divName = 'hideCVV' + myRadio.value;
	var cvvTextbox = 'cvvNumber' + myRadio.value;
	var cancelButton = 'deleteButton' + myRadio.value;
	var exCardNumberstr = 'exCardNumber' + myRadio.value;
	var delbutton = 'delbutton' + myRadio.value;

	for (j = 0; j < arrEX.length; j++) {

		if (myRadio === arrEX[j]) {
			document.getElementById(cvvTextbox).disabled = false;
			document.getElementById(cancelButton).disabled = false;
			document.getElementById(delbutton).disabled = false;
		} else {
			arrEXinput[j].value = "";
			arrEXinput[j].disabled = true;
			arrEXdelButton[j].disabled = true;
		}
	}
	if ((document.getElementById(exCardNumberstr).value).substr(0, 2) == 37 || (document.getElementById(exCardNumberstr).value).substr(0, 2) == 34) {
		document.getElementById(cvvTextbox).maxLength = 4;
		amexFlag = true;
	} else {
		document.getElementById(cvvTextbox).maxLength = 3;
		amexFlag = false;
	}
}

function enableExButton(v) {
	if (document.getElementById("cardSupportedEX").innerHTML == "This card is no longer supported") {
		document.getElementById('exSubmit').disabled = true;
		document.getElementById('exSubmit').className = "btn-orange disabled";
	}
	else {
		if (arrEX == "MS") {
			document.getElementById('exSubmit').disabled = false;
			document.getElementById('exSubmit').className = "btn-orange";
		} else {

			if (amexFlag == true) {
				if (v.value.length >= 4) {
					document.getElementById('exSubmit').disabled = false;
					document.getElementById('exSubmit').className = "btn-orange";
				}
				else {
					document.getElementById('exSubmit').disabled = true;
					document.getElementById('exSubmit').className = "btn-orange disabled";
				}
			} else {
				if (v.value.length >= 3) {
					document.getElementById('exSubmit').disabled = false;
					document.getElementById('exSubmit').className = "btn-orange";
				}
				else {
					document.getElementById('exSubmit').disabled = true;
					document.getElementById('exSubmit').className = "btn-orange disabled";
				}
			}
		}
	}
}
function submitEXform() {
	document.getElementById('exSubmit').disabled = true;
	document.getElementById('exSubmit').className = "btn-orange disabled";
	document.getElementById('exCancelButton').style.display = "none";
	return true;
}
function disableEnterPress(e) {
	var key;
	if (window.event)
		key = window.event.keyCode; //IE
	else
		key = e.which; //firefox    
	return (key != 13);
}

//////////////UPI//////////////////////////////
function enableUpiButton() {
	//alert('null');
	/*if (document.getElementById('vpaCheck').value === null) {
		document.getElementById('upiSubmit').disabled = true;
		document.getElementById('upiSubmit').className = "btn-orange disabled";
	} else {
		document.getElementById('upiSubmit1').disabled = false;
		document.getElementById('upiSubmit1').className = "btn-orange ";
	}*/
	var vpaCheckVal = "";
	if ( document.getElementById('vpaCheck').value.length  === 0 && !$("input[name='upiType']:checked").val() ) {
		//alert('null');
		$('#upiSubmitNew').addClass( "disabled" )
		document.getElementById("upiSubmitNew").setAttribute("disabled", "disabled");
		
	} else if(document.getElementById('vpaCheck').value.length != 0 && $("input[name='upiType']:checked").val()){
		
		vpaCheckVal = document.getElementById('vpaCheck').value;
		//alert('filled');
		//alert(vpaCheckVal);
		$('#upiSubmitNew').removeClass( "disabled" )
		document.getElementById("upiSubmitNew").removeAttribute("disabled", "disabled");
	}
}

function submitUPform() {
	document.getElementById('upiSubmit1').disabled = true;
	document.getElementById('upiSubmit1').className = "btn-orange disabled";
	document.getElementById('upCancelButton').style.display = "none";
	return true;
}

function upiValidate() {
	var upi = document.getElementById("vpaCheck").value;
	data = new FormData();
	//data.append('token',token);
	data.append('upi', upi);
	xhrUpi = new XMLHttpRequest();
	xhrUpi.open('POST', 'verifyUpi', true);
	xhrUpi.onload = function() {
	var obj = JSON.parse(this.response);



	};
	xhrUpiPay.send(data);
}





function submitUpiForm() {
	if (document.getElementById('vpaCheck').classList.contains('redLine')) {
		return false;
	} else if (isValidVpaOnFocusOut()) {
		document.getElementById("loading").style.display = "block"
		var upiNameProvided = 'dummy';
		var upiNumberProvided = document.getElementById("vpaCheck").value;
		var paymentType = "UP";
		var mopType = "UP";
		var amount = document.getElementById('totalAmount').innerHTML;
		var currencyCode = merchantCurrencyCode;
		// var token = document.getElementsByName("customToken")[0].value;
		vpaOldval = upiNumberProvided;
		document.getElementById('upi-sbmt').classList.remove("payActive");
		upiSubmit(upiNameProvided, upiNumberProvided, paymentType, mopType,
			amount, currencyCode)
	} else {
		isValidVpaOnFocusOut();
	}

}

function upiSubmit(upiNameProvided, upiNumberProvided, paymentType, mopType,
	amount, currencyCode) {
	//  var token = document.getElementsByName("customToken")[0].value,
	status = "", pgRefNum = "", responseCode = "", responseMessage = "",
		txnType = "", pgRefNum = "", data = new FormData(),
		myMap = new Map();
	// data.append('token',token);
	data.append('vpa', upiNumberProvided);
	data.append('upiCustName', upiNameProvided);
	data.append('paymentType', paymentType);
	data.append('mopType', mopType);
	data.append('amount', amount);
	data.append('currencyCode', currencyCode);
	xhrUpiPay = new XMLHttpRequest();
	xhrUpiPay.open('POST', 'upiPay', true);
	xhrUpiPay.onload = function() {
		var obj = JSON.parse(this.response);
		document.getElementById('loading').style.display = "block";
		document.getElementById('approvedNotification').style.display = "block";
		//var obj = this.response;
		if (null != obj) {
			transactionStatus = obj.transactionStatus;
			pgRefNum = obj.pgRefNum;
			responseCode = obj.responseCode;
			responseMessage = obj.responseMessage;
			txnType = obj.txnType;
			pgRefNum = obj.pgRefNum;
			RETURN_URL = obj.RETURN_URL;

			myMap = obj.responseFields;
		}

		if (responseCode == "366") {
			document.getElementById('approvedNotification').style.display = "none";
			document.getElementById('loading').style.display = "none";
			document.getElementById('red1').style.display = "block";
			document.getElementById('vpaCheck').classList.add("redLine");
			document.getElementById('upi-sbmt').classList.remove("payActive");
			return false;
		}

		else if (responseCode == "000") {
			if (transactionStatus == "Sent to Bank") {
				verifyUpiResponseReceived(pgRefNum, RETURN_URL);
			} else {
				document.getElementById('approvedNotification').style.display = "none";
				document.getElementById("loading").style.display = "none";
				var form = document.getElementById("axisbankresponse");
				form.action = myMap.RETURN_URL;
				for (key in myMap) {
					form.innerHTML += ('<input type="hidden" name="' + key
						+ '" value="' + myMap[key] + '">');
				}
				document.getElementById("axisbankresponse").submit();
			}
		} else {
			document.getElementById('approvedNotification').style.display = "none";
			document.getElementById("loading").style.display = "none";
			var form = document.getElementById("axisbankresponse");
			form.action = myMap.RETURN_URL;

			if (myMap.encdata) {
				form.innerHTML += ('<input type="hidden" name="encdata" value="'
					+ myMap.encdata + '">');
			} else {
				for (key in myMap) {
					form.innerHTML += ('<input type="hidden" name="' + key
						+ '" value="' + myMap[key] + '">');
				}
			}

			document.getElementById("axisbankresponse").submit();
		}
	};
	xhrUpiPay.send(data);
}


var reqCount = 0;
function verifyUpiResponseReceived(pgRefNum, RETURN_URL) {
	// var token = document.getElementsByName("customToken")[0].value,
	data = new FormData();

	//data.append('token',token);
	data.append('pgRefNum', pgRefNum);
	data.append('RETURN_URL', RETURN_URL);

	xhrUpi = new XMLHttpRequest();
	xhrUpi.open('POST', 'verifyStatus', true);
	xhrUpi.onload = function() {

		if (this == null) {
			sleep(10000);
			verifyUpiResponseReceived(pgRefNum, RETURN_URL);
		}
		var obj = JSON.parse(this.response);

		if (null != obj) {
			var field = "";
			var myMap = new Map();
			var trans = "";
			trans = obj.transactionStatus;
			myMap = obj.responseFields;

			if (trans == "Sent to Bank") {
				sleep(10000);
				/*if (reqCount == 0) {
					sleep(10000);
					reqCount = reqCount + 1;
				} else {
					sleep(10000);
				}*/
				verifyUpiResponseReceived(pgRefNum, RETURN_URL);
			} else {
				document.getElementById('approvedNotification').style.display = "none";
				document.getElementById("loading").style.display = "none";
				var form = document.getElementById("axisbankresponse");
				form.action = myMap.RETURN_URL;

				if (myMap.encdata) {
					form.innerHTML += ('<input type="hidden" name="encdata" value="'
						+ myMap.encdata + '">');
				} else {
					for (key in myMap) {
						form.innerHTML += ('<input type="hidden" name="' + key
							+ '" value="' + myMap[key] + '">');
					}
				}
				document.getElementById("axisbankresponse").submit();
			}
		} else {
			sleep(10000);
			verifyUpiResponseReceived(pgRefNum, RETURN_URL);
		}
	};
	xhrUpi.send(data);
}

//QRCODE genrate
function genrateQrCode() {
	var upiNameProvided = 'dummy';
	var upiNumberProvided = document.getElementById("vpaCheck").value;
	var paymentType = "UP";
	var mopType = "UP";
	var amount = document.getElementById('totalAmount').innerHTML;
	var currencyCode = merchantCurrencyCode;
	vpaOldval = upiNumberProvided;
	document.getElementById('upi-sbmt').classList.remove("payActive");
	qrCodeSubmit(upiNameProvided, upiNumberProvided, paymentType, mopType,
		amount, currencyCode)
}
function qrCodeSubmit(upiNameProvided, upiNumberProvided, paymentType, mopType,
	amount, currencyCode) {
	//  var token = document.getElementsByName("customToken")[0].value,
	status = "", pgRefNum = "", responseCode = "", responseMessage = "",
		txnType = "", pgRefNum = "", data = new FormData(),
		myMap = new Map();
	// data.append('token',token);
	data.append('vpa', upiNumberProvided);
	data.append('upiCustName', upiNameProvided);
	data.append('paymentType', paymentType);
	data.append('mopType', mopType);
	data.append('amount', amount);
	data.append('currencyCode', currencyCode);
	xhrUpiPay = new XMLHttpRequest();
	xhrUpiPay.open('POST', 'qrCodeGenrator', true);
	xhrUpiPay.onload = function() {
		var obj = JSON.parse(this.response);
		document.getElementById("qrcodeDiv").innerHTML = obj.BHARTQRCODE;
		var srcValue = "//chart.apis.google.com/chart?cht=qr&chs=250x250&chl=" + obj.BHARTQRCODE;
		$('#qrcodeDiv').attr('src', srcValue);
		if (null != obj) {
			transactionStatus = obj.transactionStatus;
			pgRefNum = obj.pgRefNum;
			responseCode = obj.responseCode;
			responseMessage = obj.responseMessage;
			txnType = obj.txnType;
			pgRefNum = obj.pgRefNum;
			RETURN_URL = obj.RETURN_URL;
			myMap = obj.responseFields;
		}
		if (responseCode == "366") {
			document.getElementById('approvedNotification').style.display = "none";
			document.getElementById('loading').style.display = "none";
			document.getElementById('red1').style.display = "block";
			document.getElementById('vpaCheck').classList.add("redLine");
			document.getElementById('upi-sbmt').classList.remove("payActive");
			return false;
		}

		else if (responseCode == "000") {
			if (transactionStatus == "Sent to Bank") {
				verifyQRCODEResponseReceived(pgRefNum, RETURN_URL);
			} else {
				var form = document.getElementById("axisbankresponse");
				form.action = myMap.RETURN_URL;
				for (key in myMap) {
					form.innerHTML += ('<input type="hidden" name="' + key + '" value="' + myMap[key] + '">');
				}
				document.getElementById("axisbankresponse").submit();
			}
		} else {
			var form = document.getElementById("axisbankresponse");
			form.action = myMap.RETURN_URL;

			if (myMap.encdata) {
				form.innerHTML += ('<input type="hidden" name="encdata" value="'
					+ myMap.encdata + '">');
			} else {
				for (key in myMap) {
					form.innerHTML += ('<input type="hidden" name="' + key
						+ '" value="' + myMap[key] + '">');
				}
			}

			document.getElementById("axisbankresponse").submit();
		}
	};
	xhrUpiPay.send(data);
}

//BhartQRCODE
var reqCount1 = 0;
function verifyQRCODEResponseReceived(pgRefNum, RETURN_URL) {
	data = new FormData();
	data.append('pgRefNum', pgRefNum);
	data.append('RETURN_URL', RETURN_URL);
	xhrUpi = new XMLHttpRequest();
	xhrUpi.open('POST', 'verifyStatus', true);
	xhrUpi.onload = function() {

		if (this == null) {
			sleep(10000);
			verifyQRCODEResponseReceived(pgRefNum, RETURN_URL);
		}
		var obj = JSON.parse(this.response);

		if (null != obj) {
			var field = "";
			var myMap = new Map();
			var trans = "";
			trans = obj.transactionStatus;
			myMap = obj.responseFields;

			if (trans == "Sent to Bank") {
				if (reqCount1 == 0) {
					sleep(10000);
					reqCount1 = reqCount1 + 1;
				} else {
					sleep(10000);
				}
				verifyQRCODEResponseReceived(pgRefNum, RETURN_URL);
			} else {
				var form = document.getElementById("axisbankresponse");
				form.action = myMap.RETURN_URL;

				if (myMap.encdata) {
					form.innerHTML += ('<input type="hidden" name="encdata" value="'
						+ myMap.encdata + '">');
				} else {
					for (key in myMap) {
						form.innerHTML += ('<input type="hidden" name="' + key
							+ '" value="' + myMap[key] + '">');
					}
				}
				document.getElementById("axisbankresponse").submit();
			}
		} else {
			sleep(10000);
			verifyQRCODEResponseReceived(pgRefNum, RETURN_URL);
		}
	};
	xhrUpi.send(data);
}


function myCancelAction(param) {
	if (param) {
		document.querySelector('#approvedNotification .cancelBtn').disabled = true;
	}
	if (xhrUpiPay) {
		xhrUpiPay.abort();
	}
	if (xhrUpi) {
		xhrUpi.abort();
	}
	top.location = "txncancel";
}

function restrictKeyVpa(event, Element) {
	var key = event.keyCode, spaceKey = 32, leftKey = 37, rightKey = 39, deleteKey = 46, backspaceKey = 8, tabKey = 9, point = 190, subtract = 189, subtractMoz = 173;
	if (event.key == "!" || event.key == "#" || event.key == "$"
		|| event.key == "%" || event.key == "^" || event.key == "&"
		|| event.key == "*" || event.key == "(" || event.key == ")"
		|| event.key == ">" || event.key == "_") {
		return false;
	}
	return ((key >= 48 && key <= 57) || (key >= 33 && key <= 39)
		|| (key >= 65 && key <= 90) || (key >= 96 && key <= 105)
		|| key == backspaceKey || key == tabKey || key == leftKey
		|| key == rightKey || key == deleteKey || key == point
		|| key == subtract || key == subtractMoz || key == 12 || key == 40
		|| key == 45 || key == 109 || key == 110);
}
function isValidVpaBoolean() {
	var vpaRegex = /[A-Za-z0-9][A-Za-z0-9.-]*@[A-Za-z]{2,}$/;
	var vpaElement = document.getElementById("vpaCheck");
	var vpaValue = vpaElement.value.trim();
	if (!vpaValue.match(vpaRegex)) {
		return false;
	} else {
		return true;
	}
}
function isValidVpa() {
	var vpaRegex = /[A-Za-z0-9][A-Za-z0-9.-]*@[A-Za-z]{2,}$/;
	var vpaElement = document.getElementById("vpaCheck");
	var vpaValue = vpaElement.value.trim();
	var vpaCheck = document.getElementById('vpaCheck');

	vpaCheck.classList.remove("redLine");
	document.getElementById('red1').style.display = 'none';
	document.getElementById('enterVpa').style.display = 'none';

	if (!vpaValue.match(vpaRegex)) {
		return false;
	} else {
		return true;
	}
}
function isValidVpaOnFocusOut() {
	var vpaRegex = /[A-Za-z0-9][A-Za-z0-9.-]*@[A-Za-z]{2,}$/;
	var vpaElement = document.getElementById("vpaCheck");
	var vpaValue = (vpaElement.value).trim();
	if (!vpaValue) {
		document.getElementById('enterVpa').style.display = "block";
		document.getElementById('red1').style.display = "none";
		vpaElement.classList.add("redLine");
		return false;
	}
	if (!vpaValue.match(vpaRegex)) {
		vpaElement.classList.add("redLine");
		document.getElementById('red1').style.display = "block";
		document.getElementById('enterVpa').style.display = "none";
		return false;
	}
	return true;
}

function enableButton() {
	var upiSbmtBtn = document.getElementById('upi-sbmt');
	var checkVpaError = document.getElementById('vpaCheck').classList
		.contains('redLine');
	var newVpaValue = (document.getElementById('vpaCheck').value).trim();
	if (isValidVpaBoolean() && !checkVpaError) {
		upiSbmtBtn.classList.add("payActive");
	} else {
		upiSbmtBtn.classList.remove("payActive");
	}
}



function sleep(delay) {
	var start = new Date().getTime();
	while (new Date().getTime() < start + delay);
}

function showStuff(currentElement, paymentType) {
	var tabBoxAry = document.getElementsByClassName('tabBox'), getCurrentDataId = currentElement
		.getAttribute('data-id'), tabLi = document
			.getElementsByClassName('tabLi'), orderfootDetails1 = document
				.getElementById('orderfootDetails1'), orderfootDetails2 = document
					.getElementById('orderfootDetails2');

	for (i = 0; i < tabBoxAry.length; i++) {
		tabBoxAry[i].classList.add("hideBox");
	}
	for (j = 0; j < tabLi.length; j++) {
		tabLi[j].classList.remove("activeLi");
	}

	document.getElementById(getCurrentDataId).classList.remove("hideBox");
	currentElement.classList.add("activeLi");
	document.getElementById('debit_cards').style.display = "none";
	document.getElementById('credit_cards').style.display = "none";
	document.getElementById('prepaid_cards').style.display = "none";

	switch (paymentType) {
		case "upi":
			orderfootDetails1.innerHTML = upiCopy;
			orderfootDetails2.innerHTML = upiCopy;
			break;

	}
	addConvenienceFee(paymentType);
	formReset();
}

function formReset() {

	document.getElementById('userMoptypeIcon').src = "../image/bc.png"
	document.querySelector('.cardNumber').value = "";
	document.getElementById("paymentDate").value = "";
	document.getElementById("cvvNumber").value = "";
	document.getElementById("cardName").value = "";
	document.getElementById('checkStartNo').style.display = 'none';
	document.getElementById('validCardCheck').style.display = 'none';
	document.getElementById('notSupportedCard').style.display = 'none';
	document.getElementById('validExpDate').style.display = 'none';
	document.getElementById('cvvValidate').style.display = 'none';
	document.getElementById('nameError').style.display = 'none';
	document.getElementById('emptyCardNumber').style.display = 'none';
	document.getElementById('emptyExpiry').style.display = 'none';
	document.getElementById('emptyCvv').style.display = 'none';
	document.getElementById('confirm-purchase').classList
		.remove("actvBtnCreditDebit");

	document.querySelector('.cardNumber').classList.remove("redLine");
	document.getElementById("paymentDate").classList.remove("redLine");
	document.getElementById("cvvNumber").classList.remove("redLine");
	document.getElementById("cardName").classList.remove("redLine");

	var ccMobIcon = document.getElementsByClassName('ccMobIcon');
	var dcMobIcon = document.getElementsByClassName('dcMobIcon');

	for (element = 0; element < ccMobIcon.length; element++) {
		ccMobIcon[element].classList.remove("opacityMob");
	}
	for (element = 0; element < dcMobIcon.length; element++) {
		dcMobIcon[element].classList.remove("opacityMob");
	}

	document.getElementById("vpaCheck").value = "";
	document.getElementById('red1').style.display = 'none';
	document.getElementById('enterVpa').style.display = 'none';
	document.getElementById('upi-sbmt').classList.remove("payActive");
	document.getElementById("vpaCheck").classList.remove("redLine");

	document.getElementById('googlePayNum').value = "";
	document.getElementById('googlePayInvalidNo').style.display = "none";
	document.getElementById('googlePayEnterPhone').style.display = "none";
	document.getElementById('googlePayNum').classList.remove('redLine');
	document.getElementById('googlePayBtn').classList.remove("payActive");

	document.getElementById('radioError').style.display = 'none';
	document.getElementById('cvvErrorSav').style.display = 'none';
	document.getElementById('invalidCvvErrorSav').style.display = 'none';
	var visaRadio = document.getElementsByClassName('visaRadio'), savDetailsCvv = document
		.getElementsByClassName('savDetailsCvv'), saveCardDetails = document
			.getElementsByClassName('saveCardDetails'), cvvPlaceholder = document
				.getElementsByClassName('cvvPlaceholder');
	document.getElementById('exSubmit').classList.remove("payActive");
	document.getElementById("cardsaveflag1").checked = false;
	for (var i = 0; i < visaRadio.length; i++) {
		savDetailsCvv[i].value = '';
		savDetailsCvv[i].disabled = true;
		visaRadio[i].checked = false;
		saveCardDetails[i].classList.remove("selectedSaveDetails");
		cvvPlaceholder[i].style.display = "block";
	}
	if (document.getElementsByClassName('saveCardDetails').length) {
		saveCardDetails[0].classList.add("selectedSaveDetails");
		savDetailsCvv[0].disabled = false;
		visaRadio[0].checked = true;
	}

}




//GPAY

function submitGooglePayForm() {
	var googlePayNum = (document.getElementById('googlePayNum').value).trim();
	//var token = document.getElementsByName("customToken")[0].value;
	document.getElementById('googlePayPhoneNo').value = googlePayNum;
	document.getElementById('googlePayNum').classList.remove('redLine');
	document.getElementById('googlePayInvalidNo').style.display = "none";
	document.getElementById('googlePayEnterPhone').style.display = "none";
	if (googlePayNum) {
		if (googlePayNum.length == 10) {

		} else {
			document.getElementById('googlePayInvalidNo').style.display = "block";
			document.getElementById('googlePayNum').classList.add('redLine');
			return false;
		}
	} else {
		document.getElementById('googlePayEnterPhone').style.display = "block";
		document.getElementById('googlePayNum').classList.add('redLine');
		return false;
	}

	document.getElementById('loading').style.display = "block";
	var data = new FormData();
	data.append('vpaPhone', "+91" + googlePayNum);
	data.append('mopType', 'GP');
	data.append('paymentType', 'UP');
	//data.append('token', token);
	var xhttp = new XMLHttpRequest();
	xhttp.open("POST", "upiGpayPay", true);
	xhttp.onload = function() {
		var obj = JSON.parse(this.response);
		document.getElementById('loading').style.display = "block";
		document.getElementById('approvedNotification').style.display = "block";

		if (null != obj) {
			transactionStatus = obj.transactionStatus;
			pgRefNum = obj.pgRefNum;
			responseCode = obj.responseCode;
			responseMessage = obj.responseMessage;
			txnType = obj.txnType;
			pgRefNum = obj.pgRefNum;
			RETURN_URL = obj.RETURN_URL;
			myMap = obj.responseFields;
		}

		if (responseCode == "366") {
			document.getElementById('approvedNotification').style.display = "none";
			document.getElementById('loading').style.display = "none";
			alert('Please enter a valid VPA');
			document.getElementById('googlePayNum').value = '';
			document.getElementById('googlePayBtn').classList
				.remove("payActive");
			return false;
		} else if (responseCode == "017") {
			document.getElementById('approvedNotification').style.display = "none";
			document.getElementById('loading').style.display = "none";
			alert('We are unable to reach GPay servers, please try again later!');
			document.getElementById('googlePayNum').value = '';
			document.getElementById('googlePayBtn').classList
				.remove("payActive");
			return false;
		}
		else if (responseCode == "000") {
			if (transactionStatus == "Sent to Bank") {
				checkApiCallToGpay(pgRefNum, RETURN_URL);
			} else {
				var form = document.getElementById("axisbankresponse");
				form.action = myMap.RETURN_URL;
				for (key in myMap) {
					form.innerHTML += ('<input type="hidden" name="' + key + '" value="' + myMap[key] + '">');
				}
				document.getElementById("googlePayResponseForm").submit();
			}
		}


		else {
			document.getElementById('approvedNotification').style.display = "none";
			document.getElementById("loading").style.display = "none";
			var form = document.getElementById("googlePayResponseForm");
			form.action = myMap.RETURN_URL;

			if (myMap.encdata) {
				form.innerHTML += ('<input type="hidden" name="encdata" value="'
					+ myMap.encdata + '">');
			} else {
				for (key in myMap) {
					form.innerHTML += ('<input type="hidden" name="' + key
						+ '" value="' + myMap[key] + '">');
				}
			}
			document.getElementById("googlePayResponseForm").submit();
		}
	};
	xhttp.send(data);
}
var reqGpayCount = 0;
function checkApiCallToGpay(gpRefNum, RETURN_URL) {
	// var token1 = document.getElementsByName("customToken")[0].value;
	var data1 = new FormData();
	data1.append('pgRefNum', gpRefNum);
	data1.append('RETURN_URL', RETURN_URL);

	xhttp1 = new XMLHttpRequest();
	xhttp1.open("POST", "verifyUpiGpayResponse", true);
	xhttp1.onload = function() {
		if (this == null) {
			sleep(1000);
			checkApiCallToGpay(pgRefNum, RETURN_URL);
		}
		var obj = JSON.parse(this.response);

		if (null != obj) {
			var field = "";
			var myMap = new Map();
			var trans = "";
			trans = obj.transactionStatus;
			myMap = obj.responseFields;

			if (trans == "Sent to Bank") {
				sleep(1000);
				/*if (reqGpayCount == 0) {
					sleep(1000);
					reqGpayCount = reqGpayCount + 1;
				} else {
					sleep(1000);
				}*/
				checkApiCallToGpay(pgRefNum, RETURN_URL);
			} else {
				document.getElementById('approvedNotification').style.display = "none";
				document.getElementById("loading").style.display = "none";
				var form = document.getElementById("googlePayResponseForm");
				form.action = myMap.RETURN_URL;

				if (myMap.encdata) {
					form.innerHTML += ('<input type="hidden" name="encdata" value="'
						+ myMap.encdata + '">');
				} else {
					for (key in myMap) {
						form.innerHTML += ('<input type="hidden" name="' + key
							+ '" value="' + myMap[key] + '">');
					}
				}
				document.getElementById("googlePayResponseForm").submit();
			}
		} else {
			sleep(1000);
			checkApiCallToGpay(pgRefNum, RETURN_URL);
		}
	};
	xhttp1.send(data1);

}





function numOnly(event, Element) {
	var key = event.keyCode,
		spaceKey = 32,
		leftKey = 37,
		rightKey = 39,
		deleteKey = 46,
		backspaceKey = 8,
		tabKey = 9,
		maxlengthCheck = Number(Element.getAttribute('maxlength'));

	if (event.key == "!" || event.key == "@" || event.key == "#" || event.key == "$" || event.key == "%" || event.key == "^" || event.key == "&" || event.key == "*" || event.key == "(" || event.key == ")") {
		return false;
	}
	if (maxlengthCheck) {
		if (Element.value.length == maxlengthCheck) {
			if (key == backspaceKey || key == tabKey || key == leftKey || key == rightKey || key == deleteKey) {
				return true;
			} else {
				return false;
			}
		}
	}
	return ((key >= 48 && key <= 57) || (key >= 96 && key <= 105) || key == backspaceKey || key == tabKey || key == leftKey || key == rightKey || key == deleteKey);
}

function googlePayNumCheck(getThis) {
	var googlePayNumLength = getThis.value.length,
		googlePayBtn = document.getElementById('googlePayBtn');

	document.getElementById('googlePayEnterPhone').style.display = "none";
	document.getElementById('googlePayInvalidNo').style.display = "none";
	document.getElementById('googlePayNum').classList.remove('redLine');

	if (googlePayNumLength >= 10) {
		googlePayBtn.classList.add("payActive");
	} else {
		googlePayBtn.classList.remove("payActive");
	}
}
function checkPhoneNo(element) {
	var phoneNoLength = (element.value).trim().length;
	if (phoneNoLength) {
		if (phoneNoLength == 10) {
			document.getElementById('googlePayInvalidNo').style.display = "none";
			document.getElementById('googlePayNum').classList.remove('redLine');
		} else {
			document.getElementById('googlePayInvalidNo').style.display = "block";
			document.getElementById('googlePayNum').classList.add('redLine');
		}
	} else {
		document.getElementById('googlePayEnterPhone').style.display = "block";
		document.getElementById('googlePayNum').classList.add('redLine');
	}
}