/////disable back button ////
/*{
    history.pushState(null, null, 'surchargePaymentPage.jsp');
    window.addEventListener('popstate', function(event) {
    history.pushState(null, null, 'surchargePaymentPage.jsp');
    });
}*/
var arr = [];
var arrD = [];
var arrEX = [];
var arrEXinput = [];
var CheckedRadioCredit;
var CheckedRadioDebit;
var selectcorrectcardtype;
var cvvlen3;
var cvvlen4;
var cvvlenDD3;
var amexFlag=false; 

function getCreditForm(){
	if (document.getElementById('debitCard')!= null){
	  document.getElementById("cardNumber").placeholder = document.getElementById("cardPlaceHolderCC").value;
	  cvvlen3=document.getElementById("cvvTextImage").value; 
	  cvvlen4=document.getElementById("cvvTextImageAX").value;
	  var form =   document.getElementById('creditCard');
	  var elements =  form.elements;  
	  var counter = 0;
	  for(var i = 0; i < elements.length; i++) {
	   if(elements[i].type.toLowerCase() == 'radio') {
	    arr[counter] = elements[i]
	    counter++;
	  	      }
	  		}
		}
	}

function getDebitForm(){
	if (document.getElementById('debitCard')!= null)	{
	
	  document.getElementById("dccardNumber").placeholder = document.getElementById("cardPlaceHolderCC").value;
	  var form =   document.getElementById('debitCard');
	  var elements =  form.elements;  
	  var counter = 0;
	  for(var i = 0; i < elements.length; i++) {
	   if(elements[i].type.toLowerCase() == 'radio') {
	    arrD[counter] = elements[i]
	    counter++;
	   			}
	  		}
		}
	}
function validateCredit() {
	var checked = false;
	for (k = 0; k < arr.length; k++) {
		if (arr[k].checked == true) {
			checked = true;
			CheckedRadioCredit= arr[k].value;
			break;
			}
	}
	if(CheckedRadioCredit=='AX'){
		var cvvTextAX = document.getElementById('cvvtext');
		cvvTextAX.innerHTML = cvvlen4;
		cvvTextAX.className = "transparent";
		document.getElementById('cvvNumber').value="";
		document.getElementById('cvvNumber').maxLength = 4;
	}
	else{
		var cvvText = document.getElementById('cvvtext');
		cvvText.innerHTML = cvvlen3;
		cvvText.className = "transparent";
		document.getElementById('cvvNumber').value="";
		document.getElementById('cvvNumber').maxLength = 3;	
	}

	if (checked==false) {
		var cardTypeMessage = document.getElementById("cardTypeMsg").value; 
		document.getElementById("radioValidate").innerHTML = cardTypeMessage;
		return false;
	}else{
		document.getElementById("radioValidate").innerHTML = "";
		return true;
	}

}
function enablePayButton(v){
	//  for(j=0;j<arr.length;j++){
		 // if(arr[j].checked==true){
			 if (CheckedRadioCredit=="EZ")
				  {
				  document.getElementById('ccSubmit').disabled=false;
				  document.getElementById('ccSubmit').className = "btn-orange";
				  document.getElementById('divCardNumber').style.display = "none";
				  document.getElementById('divExpiryMonth').style.display = "none";
				  document.getElementById('divExpiryYear').style.display = "none";
				  document.getElementById('divPassword').style.display = "none";
				  document.getElementById('divCvv').style.display = "none";
				  document.getElementById('divSaveCard').style.display = "none";
				  document.getElementById('divCardType').style.display = "none";
				  document.getElementById('divName').style.display = "none";
				  document.getElementById('whitetransbgsize').className = "whitetransbgnew";
				  document.getElementById('cardsaveflag').value="false";
				  }
			 else{
				 document.getElementById('divCardNumber').style.display = "";
				  document.getElementById('divExpiryMonth').style.display = "";
				  document.getElementById('divExpiryYear').style.display = "";
				  document.getElementById('divPassword').style.display = "";
				  document.getElementById('divCvv').style.display = "";
				  document.getElementById('divSaveCard').style.display = "";
				  document.getElementById('divCardType').style.display = "";
				  document.getElementById('divName').style.display = "";
				  document.getElementById('whitetransbgsize').className = "whitetransbg";
			  if (document.getElementById('cardName').value != "" && document.getElementById('cardNumber').value != ""  && document.getElementById('cvvNumber').value != "" && document.getElementById('demo1').innerHTML !="Select Correct Card Type" && document.getElementById('ccExpiryMonth').selectedIndex != 0 && document.getElementById('ccExpiryYear').selectedIndex != 0 &&  document.getElementById("demo").innerHTML != "Enter valid Card number" && document.getElementById('cvvNumber').value.length >= 3 && document.getElementById('chkExpiry').innerHTML !="Enter valid expiry date")  
				{
				   if (CheckedRadioCredit=="AX" &&  document.getElementById('cvvNumber').value.length == 4) {
					    document.getElementById('ccSubmit').disabled=false;
						document.getElementById('ccSubmit').className = "btn-orange";
					}
				  else {							
						document.getElementById('ccSubmit').disabled=true;
						document.getElementById('ccSubmit').className = "btn-orange disabled";
					}
				 
				  if (CheckedRadioCredit!="AX" &&  document.getElementById('cvvNumber').value.length == 3) {
					  document.getElementById('ccSubmit').disabled=false;
					  document.getElementById('ccSubmit').className = "btn-orange";
					}
				  
				}
				else {							
					document.getElementById('ccSubmit').disabled=true;
					document.getElementById('ccSubmit').className = "btn-orange disabled";
				}
			 }
		 // } if cond close
		  
	//  } for lop close
	    
}
function checkCard(){
	var str,str0,str1;
	str=document.getElementById('cardNumber').value;
	str0 = str.charAt(0);
	str1 = str.charAt(0)+ str.charAt(1);
	if ( str != "") {
	if (CheckedRadioCredit=="VI"){
		if (str0 == 4){
			document.getElementById('demo1').innerHTML="";	
		}	
		else{
			document.getElementById('demo1').innerHTML=selectcorrectcardtype;	
			 return false;
		}
		}
	else if (CheckedRadioCredit=="MC"){
		if ( str0 == 5){
			document.getElementById('demo1').innerHTML="";	
		}
		else{
		document.getElementById('demo1').innerHTML=selectcorrectcardtype;	
		 return false;
		}
	}
	else if (CheckedRadioCredit=="AX"){
		if  (str1 == 34 || str1 == 37){
			document.getElementById('demo1').innerHTML="";	
		}
		else{
			document.getElementById('demo1').innerHTML=selectcorrectcardtype;	
			 return false;
		}
	}
	else if (CheckedRadioCredit=="DN" ){
		if (str1 == 30 || str1 == 36 || str1 == 38 ){
			document.getElementById('demo1').innerHTML="";	
		}
		else{
		document.getElementById('demo1').innerHTML=selectcorrectcardtype;	
		 return false;
		}
	}
	else{
		document.getElementById('demo1').innerHTML="";	
		 return true;
	}
	}
	
      }

function checkLuhn(input) // same for cc/dc
{
  var sum = 0;
  var cnumber =input.value.replace(/\s/g,'');
  var numdigits = cnumber.length;
  var parity = numdigits % 2;
  for(var i=0; i < numdigits; i++) {
    var digit = parseInt(cnumber.charAt(i))
    if(i % 2 == parity) digit *= 2;
    if(digit > 9) digit -= 9;
    sum += digit;
  }
  var result=( (sum % 10) == 0);
//  alert(result);
  if (result==false)
	  {
	  if (input.id == "cardNumber" &&  document.getElementById("demo")!= null ){
		  var entrValidcardnumber = document.getElementById("validCardDetail").value;
		  document.getElementById("demo").innerHTML = entrValidcardnumber;
		  return false;
	  }
  else
	  if (input.id == "dccardNumber" &&  document.getElementById("demoD")!= null ){
		{
		  var entrValidcardnumberD = document.getElementById("validCardDetailD").value;
		  document.getElementById("demoD").innerHTML = entrValidcardnumberD;
		  return false;
		  }
	  }
	  }
  else{
	  try {
	  document.getElementById("demo").innerHTML = "";
	  document.getElementById("demoD").innerHTML = "";
	  }
	  catch(err) {}
	  	 return true;
  }
  
}
function CheckExpiry (){
	var today, someday;
	var exMonth=document.getElementById("ccExpiryMonth").value;
	var exYear=document.getElementById("ccExpiryYear").value;
	today = new Date();
	someday = new Date();
	someday.setFullYear(exYear, exMonth, 1);
	if (document.getElementById('ccExpiryYear').selectedIndex != 0){
	if (someday < today) {
		var entrValidexpirydate = document.getElementById("validExpiryDate").value;
		document.getElementById("chkExpiry").innerHTML = entrValidexpirydate;
	    return false;
	    
	}
	else {
		document.getElementById("chkExpiry").innerHTML = "";
    return true;	}
	
}

}
function Show() // same for cc/dc
{
	x = event.clientX + document.body.scrollLeft; // get the mouse left position
	y = event.clientY + document.body.scrollTop + 35; // get the mouse top position 
	cvvtext.style.display="block"; // display the pop-up
	cvvtext.style.left = x; // set the pop-up's left
	cvvtext.style.top = y; // set the pop-up's top
}
function Hide() // same for cc/dc
{
	cvvtext.style.display="none"; // hide the pop-up

}
function isCharacterKey(event) 
{
	var k;
     document.all ? k = event.keyCode : k = event.which;
     return ((k > 64 && k < 91) || (k > 96 && k < 123)  ||(k==8));
}
function noSpace(event,inputName){ // same for cc/dc
	  var str,str0,cardName;
	  cardName=inputName.id
	  str=inputName.value;
	  str0=str.charAt(0);
	  if (str0 == ""){ 
		  cardName.value="";
	  //return false;
	     }
	  else {
	   if (event.charCode==32)
	    {
		   if (cardName=="cardName"){
		   document.getElementById('cardName').value=str + ' ';
		   }
		   else  if (cardName=="dccardName"){
			  document.getElementById('dccardName').value=str + ' ';
		   }
	    }
	      }
	 }
function isNumberKeyCard(evt)
{ selectcorrectcardtype=document.getElementById("correctCardType").value;
	validateCredit();
	var str,str0,str1;
	str=evt.value;
	str0 = str.charAt(0);
	str1 = str.charAt(0)+ str.charAt(1);
	if (str0 == 3 || str0 == 4 || str0 == 5|| str0 == 6 || str1=="VI" || str1=="MC" || str1=="AX" || str1=="DN"){
		} 
	else{
	document.getElementById('cardNumber').value="";
	return false;
	}
	if (CheckedRadioCredit=="VI"){
		if (str0 == 4 || str1 == "VI"){
			document.getElementById('demo1').innerHTML="";	
		}	
		else{
			document.getElementById('demo1').innerHTML=selectcorrectcardtype;	
			 return false;
		}
			
		}
	else if (CheckedRadioCredit=="MC"){
		if ( str0 == 5 || str1=="MC"){
			document.getElementById('demo1').innerHTML="";	
		}
		else{
		document.getElementById('demo1').innerHTML=selectcorrectcardtype;	
		 return false;
		}
	}
	else if (CheckedRadioCredit=="AX"){
		if  (str1 == 34 || str1 == 37 || str1 == "AX"){
			document.getElementById('demo1').innerHTML="";	
		}
		else{
			document.getElementById('demo1').innerHTML=selectcorrectcardtype;	
			 return false;
		}
		
	}
	else if (CheckedRadioCredit=="DN" ){
		if (str1 == 30 || str1== 38 || str1== "DN"){
			document.getElementById('demo1').innerHTML="";	
		}
		else{
		document.getElementById('demo1').innerHTML=selectcorrectcardtype;	
		 return false;
		}
	}
	else{
		document.getElementById('demo1').innerHTML="";	
		 return true;
	}
}
function submitCCform() {
		 document.getElementById('ccSubmit').disabled = true;
		 document.getElementById('ccSubmit').className = "btn-orange disabled";
		 return true;
		}

function submitDCform() {
	 document.getElementById('dcSubmit').disabled = true;
	 document.getElementById('dcSubmit').className = "btn-orange disabled";
	 return true;
	}

function isNumberKey(evt) //for both cc/dc
{
   var charCode = (evt.which) ? evt.which : event.keyCode
  
   if (charCode > 31 && (charCode < 48 || charCode > 57))
      return false;

   return true;
}

function validateDebit() {
	
	var checked = false;
	
	for (k = 0; k < arrD.length; k++) {
		if (arrD[k].checked == true) {
			checked = true;
			CheckedRadioDebit= arrD[k].value;
			break;
		}
	}
if(CheckedRadioDebit!=null){
       	var cvvTextDD = document.getElementById('cvvtextDD');			
		cvvTextDD.innerHTML = cvvlen3;
		cvvTextDD.className = "transparent";
		document.getElementById('cvvNumber').value="";
		document.getElementById('cvvNumber').maxLength = 3;
		
	}

	else{
		document.getElementById("cvvNumber").innerHTML = "";
		var cardTypeMessageDC = document.getElementById("cardTypeMsgDC").value; 
		document.getElementById("radioValidateDebit").innerHTML = cardTypeMessageDC;
		return false;
		}

	if (checked==false) {
		var cardTypeMessageDC = document.getElementById("cardTypeMsgDC").value; 
		document.getElementById("radioValidateDebit").innerHTML = cardTypeMessageDC;
		return false;		
	}else{
		document.getElementById("radioValidateDebit").innerHTML = "";
		if (CheckedRadioDebit!="MS"){
		document.getElementById("maestroNoteDspan").innerHTML ="";
		}
		return true;
	}
	  			
		}
function enablePayButtonDebit(v){
	
	for(j=0;j<arrD.length;j++){
		  if(arrD[j].checked==true){
			 
			  if ((CheckedRadioDebit =='MS') && document.getElementById("demoD").innerHTML != "Enter valid Card number" ){
				  if (document.getElementById('dccardNumber').value != "" && document.getElementById('dccardName').value != "" && document.getElementById('chkExpiryDD').innerHTML !="Enter valid expiry date" ){
					  
					  document.getElementById('dcSubmit').disabled=false;
						document.getElementById('dcSubmit').className = "btn-orange";
				  }
				  else{
						document.getElementById('dcSubmit').disabled=true;
						document.getElementById('dcSubmit').className = "btn-orange disabled";
				  }
			  }
			  else  {
				 if (document.getElementById('dccardName').value != "" && document.getElementById('dccvvNumber').value != "" && document.getElementById('dccardNumber').value != ""  &&  document.getElementById('demoD1').innerHTML !="Select Correct Card Type"  && document.getElementById("demoD").innerHTML != "Enter valid Card number" && document.getElementById('dccvvNumber').value != ""   && document.getElementById('dcExpiryMonth').selectedIndex != 0  && document.getElementById('dcExpiryYear').selectedIndex != 0 )
				 {
					 document.getElementById('dcSubmit').disabled = false;
					 document.getElementById('dcSubmit').className = "btn-orange ";
						 }
				 else{
						document.getElementById('dcSubmit').disabled = true;
						document.getElementById('dcSubmit').className = "btn-orange disabled";
				  }
		}
		  }
}	  
	}
function MaestroNote()
{
	 var maestroNoteD = document.getElementById("maestroNoteD").value;
	document.getElementById("maestroNoteDspan").innerHTML = maestroNoteD;
}

function checkCardDebit(){
	var str,str0,str1;
	str=document.getElementById('dccardNumber').value;
	str0 = str.charAt(0);
	str1 = str.charAt(0)+ str.charAt(1);		
	if ( str != "") {
	
	if (CheckedRadioDebit=="VI"){
		if ( str0 == 4 ){
			document.getElementById('demoD1').innerHTML="";	
			document.getElementById("maestroNoteDspan").innerHTML = "";
		}
		else {
		document.getElementById('demoD1').innerHTML=selectcorrectcardtype;
		 return false;
		}
			}
	else if (CheckedRadioDebit=="MC"){
		if (str0 == 5){
			document.getElementById('demoD1').innerHTML="";	
			document.getElementById("maestroNoteDspan").innerHTML = "";
		}
		else{
		document.getElementById('demoD1').innerHTML=selectcorrectcardtype;
		 return false;
		}
	}
	else if (CheckedRadioDebit=="MS"){
		if (str0 == 6){
			document.getElementById('demoD1').innerHTML="";	
		} 
		else{
		document.getElementById('demoD1').innerHTML=selectcorrectcardtype;
		 return false;
		}
	}
	
	else{
		document.getElementById('demoD1').innerHTML="";	
		document.getElementById("maestroNoteDspan").innerHTML = "";
	}
	
	}
}

function CheckExpiryDC (){
	var today, someday;
	var exMonth=document.getElementById("dcExpiryMonth").value;
	var exYear=document.getElementById("dcExpiryYear").value;
	today = new Date();
	someday = new Date();
	someday.setFullYear(exYear, exMonth, 1);
	if (document.getElementById('dcExpiryYear').selectedIndex != 0){
	if (someday < today) {
		var entrValidexpirydateD = document.getElementById("validExpiryDateD").value;
		document.getElementById("chkExpiryDD").innerHTML = entrValidexpirydateD;
	    return false;
	}
	else {
		document.getElementById("chkExpiryDD").innerHTML = "";
    return true;	}
	}
}
function isNumberKeyCardDebit(evt)
{
	selectcorrectcardtype=document.getElementById("correctCardType").value;
	validateDebit();

	var str,str0,str1;
	str=evt.value;

	str0 = str.charAt(0);
	str1 = str.charAt(0)+ str.charAt(1);
	if (str0== 3 || str0==4 || str0==5|| str0==6 || str1=="VI" || str1=="MC" || str1=="MS" ){
	}
	else{
		document.getElementById('dccardNumber').value="";
		return false;
		}
	if (CheckedRadioDebit=="VI"){
	if ( str0 == 4 || str1 == "VI" ){
		document.getElementById('demoD1').innerHTML="";	
	}
	else {
	document.getElementById('demoD1').innerHTML=selectcorrectcardtype;
	 return false;
	}
		}
	else if (CheckedRadioDebit=="MC"){
	if (str0 == 5 || str1 == "MC" ){
		document.getElementById('demoD1').innerHTML="";	
	}
	else{
	document.getElementById('demoD1').innerHTML=selectcorrectcardtype;
	 return false;
	}
	}
	else if (CheckedRadioDebit=="MS"){
	if (str0 == 6 ||str1 == "MS" ){
		document.getElementById('demoD1').innerHTML="";	
			} 
	else{
	document.getElementById('demoD1').innerHTML=selectcorrectcardtype;
	 return false;
	}
}

else{
	document.getElementById('demoD1').innerHTML="";	
}

}
///////////////////////netbanking/////////////
function onChange(id){
	var value= document.getElementById(id).value;
	if (value=="SBI"){
		ddlNetbanking.options[ddlNetbanking.selectedIndex].text = "State Bank of India";
		ddlNetbanking.options[ddlNetbanking.selectedIndex].value="1030";
		}
	else if(value=="HDFC")
	{		ddlNetbanking.options[ddlNetbanking.selectedIndex].text = "Hdfc Bank";
	ddlNetbanking.options[ddlNetbanking.selectedIndex].value="1004";
	} 
	else if(value=="ICICI")
	{		ddlNetbanking.options[ddlNetbanking.selectedIndex].text = "Icici Bank";
	ddlNetbanking.options[ddlNetbanking.selectedIndex].value="1013";
	}
	else if(value=="AXIS")
	{		ddlNetbanking.options[ddlNetbanking.selectedIndex].text = "Axis Bank";
	ddlNetbanking.options[ddlNetbanking.selectedIndex].value="1005";
	}
	else if(value=="CITI")
	{		ddlNetbanking.options[ddlNetbanking.selectedIndex].text = "Citi Bank";
	ddlNetbanking.options[ddlNetbanking.selectedIndex].value="1010";
	}
	else if(value=="KOTAK")
	{	ddlNetbanking.options[ddlNetbanking.selectedIndex].text = "Kotak Bank";
	ddlNetbanking.options[ddlNetbanking.selectedIndex].value="1012";
	}

}	
function onChangeNetbankDropdwn(value){
	
	if (value=="1030"){
		document.getElementById('sbi').checked  = true;
		}
	else if(value=="1004")
	{		document.getElementById('hdfc').checked  = true;
	} 
	else if( value=="1013")
	{		document.getElementById('icici').checked  = true;
	}
	else if(value=="1005")
	{		document.getElementById('axis').checked  = true;
	}
	else if( value=="1010")
	{		document.getElementById('citi').checked  = true;
	}
	else if( value=="1012")
	{		document.getElementById('kotak').checked  = true;
	}
	else{
		document.getElementById('sbi').checked  = false;
		document.getElementById('hdfc').checked  = false;
		document.getElementById('icici').checked  = false;
		document.getElementById('axis').checked  = false;
		document.getElementById('citi').checked  = false;
		document.getElementById('kotak').checked  = false;
	}

}	
function enableNetbankingButton()
{
	if (document.getElementById('ddlNetbanking').selectedIndex.Text == "Select Bank"){
	 document.getElementById('nbSubmit').disabled = true;
	 document.getElementById('nbSubmit').className = "btn-orange disabled";
	}
	else{
		document.getElementById('nbSubmit').disabled = false;
	    document.getElementById('nbSubmit').className = "btn-orange";
	 }
	}
/////////////////Wallet////////////////
function enableWalletButton()
{
	 document.getElementById('walletSubmit').disabled = false;
	document.getElementById('walletSubmit').className = "btn-orange";
	}
//////////////Express Pay////////////
function getExForm(){
	if (document.getElementById("exCard")!= null )
		{
	  var form =   document.getElementById('exCard');
	  var elements =  form.elements;  
	  var counter = 0;
	  var counterCvv = 0;
	  for(var i = 0; i < elements.length; i++) {
	   if(elements[i].type.toLowerCase() == 'radio') {
	    arrEX[counter] = elements[i]
	    counter++;
	      }
	   if(elements[i].type.toLowerCase() == 'password') {
		    arrEXinput[counterCvv] = elements[i]
		    counterCvv++;
		      }
	  		}
		}
	}
function handleClick(myRadio) {
    var divName = 'hideCVV' + myRadio.value;
    var cvvTextbox = 'cvvNumber' + myRadio.value;
    var cancelButton ='deleteButton'+ myRadio.value;
    var exCardNumberstr ='exCardNumber'+ myRadio.value;
    var delbutton ='delbutton'+ myRadio.value;
	  
    for (j =0; j < arrEX.length; j++) {

		if (myRadio === arrEX[j]) {
			// document.getElementById(divName).className = "showbx";
			document.getElementById(cvvTextbox).disabled = false;
			document.getElementById(cancelButton).disabled = false;
			document.getElementById(delbutton).disabled = false;
		} else {
			arrEXinput[j].value = "";
		}
	}
   if  ((document.getElementById(exCardNumberstr).value).substr(0, 2)== 37 || (document.getElementById(exCardNumberstr).value).substr(0, 2)== 34)
	   {
		document.getElementById(cvvTextbox).maxLength = 4;
		amexFlag=true;
		}
   else{
	   document.getElementById(cvvTextbox).maxLength = 3;
	   amexFlag=false;
   }
  }
function enableExButton(v){
	if (arrEX=="MS"){
		document.getElementById('exSubmit').disabled=false;
		document.getElementById('exSubmit').className = "btn-orange";
	}
	else{
		
	if (amexFlag==true){
		if (v.value.length >=4){
			 document.getElementById('exSubmit').disabled=false;
			 document.getElementById('exSubmit').className = "btn-orange";
		}
	}
	else
	{
	if (v.value.length >=3){
		 document.getElementById('exSubmit').disabled=false;
		 document.getElementById('exSubmit').className = "btn-orange";
	}
	}
	}
}
function disableEnterPress(e)
{ var key;      
     if(window.event)
          key = window.event.keyCode; //IE
     else
          key = e.which; //firefox    
     return (key != 13);
}
