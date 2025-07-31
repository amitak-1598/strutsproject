var OffusList = [];

var Offus = function(merchant, currency, payment_type, mop, transaction_type,
		acquirer, onUsFlag, paymentsRegion, cardHolderType) {
	this.merchant = merchant;
	this.currency = currency;
	this.paymentType = payment_type;
	this.mopType = mop;
	this.transactionType = transaction_type;
	this.acquirerMap = acquirer;
	this.onUsFlag = onUsFlag;
	this.cardHolderType = cardHolderType;
	this.paymentsRegion = paymentsRegion;
	this.appId = merchant;

};

var getOffUs = function() {
	var ou_row;
	var ou_currency = [];
	var ou_txnType = [];
	var ou_paymentType = [];
	var ou_mopType = [];
	var ou_Acquirer = [];
	var errormessage = "";
	var acquirerMessage = "";
	var CurrentOffusListLength;
	var match;
	var matchNumber = 0;
	var onUsFlag = true;
	var OffusListTemp = [];
	var cardHolderType;
	var paymentsRegion;
	var appId;

	if ($('#offus_section input[name="currency"]:checked').length < 1) {
		errormessage = errormessage + "Please choose at least one Currency\n";
	} else {
		$('#offus_section input[name="currency"]:checked').each(function() {
			ou_currency.push($(this).val());
		});
	}

	if ($('#offus_section input[name="txnType"]:checked').length < 1) {
		errormessage = errormessage
				+ "Please choose at least one Transection Type\n";
	} else {
		$('#offus_section input[name="txnType"]:checked').each(function() {
			ou_txnType.push($(this).val());
		});
	}

	if ($('#offus_section input[name="paymentType"]:checked').length < 1) {
		errormessage = errormessage
				+ "Please choose at least one Payment Type\n";
	} else {
		$('#offus_section input[name="paymentType"]:checked').each(function() {
			ou_paymentType.push($(this).val());
		});
	}

	if ($('#offus_section input[name="mopType"]:checked').length < 1) {
		errormessage = errormessage
				+ "Please choose at least one Transection Mop Type\n";
	} else {
		$('#offus_section input[name="mopType"]:checked').each(function() {
			ou_mopType.push($(this).val());
		});
	}

	if ($('.AcquirerList:checked').length < 1) {
		errormessage = errormessage + "Please choose at least one Acquirer\n";
	} else if ($('.AcquirerList input[name^="Acquirer"]:checked').length < $('.AcquirerList > [class^="Acquirer"]').length) {
		acquirerMessage = "Please Choose Acquirer preference or remove unused preference!";
	} else {
		var acq_pref_count = 1;
		$('.AcquirerList:checked').each(function() {
			ou_Acquirer.push(acq_pref_count + "-" + $(this).val());
			// alert(ou_Acquirer);
			acq_pref_count++;
		});
	}

	merchant = $('#offus_merchant').val();

	// SET REGION
	if (!document.getElementById("Internationalbox").checked
			&& !document.getElementById("Domesticbox").checked) {
		alert("Select a Region");
		return false;
	}

	if (document.getElementById("Internationalbox").checked) {
		paymentsRegion = "INTERNATIONAL";
	}

	if (document.getElementById("Domesticbox").checked) {
		paymentsRegion = "DOMESTIC";
	}

	merchant = $('#offus_merchant').val();

	// SET
	if (!document.getElementById("B2Cbox").checked
			&& !document.getElementById("B2Bbox").checked) {
		alert("Select a Type");
		return false;
	}

	if (document.getElementById("B2Cbox").checked) {
		cardHolderType = "B2C";
	}

	if (document.getElementById("B2Bbox").checked) {
		cardHolderType = "B2B";
	}

	if (errormessage != "") {
		swal({
			title : "",
			text : errormessage
		});
	} else if (acquirerMessage != "") {
		swal({
			title : "",
			text : acquirerMessage
		});
	} else {

		CurrentOffusListLength = OffusList.length;

		for (var ou_currency_key = 0; ou_currency_key < ou_currency.length; ou_currency_key++) {

			for (var ou_paymentType_key = 0; ou_paymentType_key < ou_paymentType.length; ou_paymentType_key++) {

				for (var ou_mopType_key = 0; ou_mopType_key < ou_mopType.length; ou_mopType_key++) {

					for (var ou_txnType_key = 0; ou_txnType_key < ou_txnType.length; ou_txnType_key++) {
						console.log(ou_Acquirer);
						onUsFlag = false;

						ou_row = new Offus(merchant,
								ou_currency[ou_currency_key],
								ou_paymentType[ou_paymentType_key],
								ou_mopType[ou_mopType_key],
								ou_txnType[ou_txnType_key], ou_Acquirer
										.join(', '), onUsFlag, cardHolderType,
								paymentsRegion);

						match = false;
						for (var a = 0; a < CurrentOffusListLength; a++) {
							if ((ou_row.merchant === OffusList[a].merchant || ou_row.merchant != "ALL MERCHANTS"
									&& OffusList[a].merchant == "ALL MERCHANTS")
									&& (ou_row.currency === OffusList[a].currency)
									&& (ou_row.paymentType === OffusList[a].paymentType)
									&& (ou_row.mopType === OffusList[a].mopType)
									&& (ou_row.transactionType === OffusList[a].transactionType)
									&& (ou_row.onUsFlag === OffusList[a].onUsFlag)
									&& (ou_row.cardHolderType === OffusList[a].cardHolderType)
									&& (ou_row.paymentsRegion === OffusList[a].paymentsRegion)) {
								match = true;
								matchNumber++;
							}
						}
						if (match == false) {
							OffusListTemp.push(ou_row);
						}

					}

				}

			}

		}

		$('#offus_section input[type="checkbox"]:checked')
				.removeAttr('checked');

	}

	if (matchNumber > 0) {
		swal({
			type : "info",
			title : matchNumber + " Match found in records!",
			type : "info"
		});
	}

	if (OffusListTemp.length > 0) {
		var listData = {
			values : OffusListTemp
		};
		var data1 = "";
		data1 = data1.concat("{", "\"", "listData", "\"", ":", JSON
				.stringify(OffusListTemp), "}");
		var token = document.getElementsByName("token")[0].value;
		$.ajax({
			type : "POST",
			url : "onusoffusRulesSetup",
			dataType : "json",
			contentType : "application/json; charset=utf-8",
			data : data1,
			success : function(data) {
				if (OffusListTemp.length > 0) {
					var response = data.response;
					swal({
						title : OffusListTemp.length + ' Rule Created!',
						type : "success"
					}, function() {
						window.location.reload();
					});
				}
			},
			error : function(data) {
				alert("Error, mapping not saved!!!! ");
				window.location.reload();
			}
		})
	}

};

// ///////////////////////////////////////////
// ///////Get On Us Functions Start Here////////
// ///////////////////////////////////////////

var getOnUs = function() {
	var ou_row;
	var ou_currency = [];
	var ou_txnType = [];
	var ou_paymentType = [];
	var ou_mopType = [];
	var ou_Acquirer = [];
	var errormessage = "";
	var CurrentOffusListLength;
	var match;
	var matchNumber = 0;
	var onUsFlag = true;
	var OffusListTemp = [];
	var merchant;

	if ($('#onus_section input[name="currency"]:checked').length < 1) {
		errormessage = errormessage + "Please choose at least one Currency\n";
	} else {
		$('#onus_section input[name="currency"]:checked').each(function() {
			ou_currency.push($(this).val());
		});
	}

	if ($('#onus_section input[name="txnType"]:checked').length < 1) {
		errormessage = errormessage
				+ "Please choose at least one Transection Type\n";
	} else {
		$('#onus_section input[name="txnType"]:checked').each(function() {
			ou_txnType.push($(this).val());
		});
	}

	if ($('#onus_section input[name="paymentType"]:checked').length < 1) {
		errormessage = errormessage
				+ "Please choose at least one Payment Type\n";
	} else {
		$('#onus_section input[name="paymentType"]:checked').each(function() {
			ou_paymentType.push($(this).val());
		});
	}

	if ($('#onus_section input[name="mopType"]:checked').length < 1) {
		errormessage = errormessage
				+ "Please choose at least one Transection Mop Type\n";
	} else {
		$('#onus_section input[name="mopType"]:checked').each(function() {
			ou_mopType.push($(this).val());
		});
	}

	if ($('.AcquirerList:checked').length < 1) {
		errormessage = errormessage + "Please choose at least one Acquirer\n";
	} else if ($('.AcquirerList input[name^="Acquirer"]:checked').length < $('.AcquirerList > [class^="Acquirer"]').length) {
		acquirerMessage = "Please Choose Acquirer preference or remove unused preference!";
	} else {
		var acq_pref_count = 1;
		$('.AcquirerList:checked').each(function() {
			ou_Acquirer.push(acq_pref_count + "-" + $(this).val());
			// alert(ou_Acquirer);
			acq_pref_count++;
		});
	}

	merchant = $('#onus_merchant').val();

	if (errormessage != "") {
		swal({
			title : "",
			text : errormessage
		});
	} else {

		CurrentOffusListLength = OffusList.length;

		for (var ou_currency_key = 0; ou_currency_key < ou_currency.length; ou_currency_key++) {

			for (var ou_paymentType_key = 0; ou_paymentType_key < ou_paymentType.length; ou_paymentType_key++) {

				for (var ou_mopType_key = 0; ou_mopType_key < ou_mopType.length; ou_mopType_key++) {

					for (var ou_txnType_key = 0; ou_txnType_key < ou_txnType.length; ou_txnType_key++) {

						for (var ou_Acquirer_key = 0; ou_Acquirer_key < ou_Acquirer.length; ou_Acquirer_key++) {

							ou_row = new Offus(merchant,
									ou_currency[ou_currency_key],
									ou_paymentType[ou_paymentType_key],
									ou_mopType[ou_mopType_key],
									ou_txnType[ou_txnType_key],
									ou_Acquirer[ou_Acquirer_key], onUsFlag)
							match = false;
							for (var a = 0; a < CurrentOffusListLength; a++) {
								if ((ou_row.merchant === OffusList[a].merchant || ou_row.merchant != "ALL MERCHANTS"
										&& OffusList[a].merchant == "ALL MERCHANTS")
										&& (ou_row.currency === OffusList[a].currency)
										&& (ou_row.paymentType === OffusList[a].paymentType)
										&& (ou_row.mopType === OffusList[a].mopType)
										&& (ou_row.transactionType === OffusList[a].transactionType)
										&& (ou_row.acquirerMap === OffusList[a].acquirerMap)
										&& (ou_row.onUsFlag === OffusList[a].onUsFlag)) {
									match = true;
									matchNumber++;
								}
							}
							if (match == false) {
								OffusListTemp.push(ou_row);
							}

						}

					}

				}

			}

		}

		$('#onus_section input:checked').removeAttr('checked');

	}

	if (matchNumber > 0) {
		swal({
			title : "",
			text : matchNumber + " Match found in records!",
			type : "info"
		});
	}
	if (OffusListTemp.length > 0) {
		var listData = {
			values : OffusListTemp
		};
		var data1 = "";
		data1 = data1.concat("{", "\"", "listData", "\"", ":", JSON
				.stringify(OffusListTemp), "}");
		var token = document.getElementsByName("token")[0].value;
		$.ajax({
			type : "POST",
			url : "onusoffusRulesSetup",
			dataType : "json",
			contentType : "application/json; charset=utf-8",
			data : data1,
			success : function(data) {
				if (OffusListTemp.length > 0) {
					var response = data.response;
					swal({
						title : OffusListTemp.length + ' Rule Created!',
						type : "success"
					}, function() {
						window.location.reload();
					});
				}
			},
			error : function(data) {
				alert("Error, mapping not saved!!!! ");
				window.location.reload();
			}
		})
	}
};

// ///////////////////////////////////////////
// ///////Get On Us Functions End Here////////
// ///////////////////////////////////////////

$(document)
		.ready(
				function() {
					$('.card-list-toggle').on('click', function() {
						$(this).toggleClass('active');
						$(this).next('.card-list').slideToggle();
					});
					$
							.ajax({
								type : "GET",
								url : "getRulesList",
								success : function(data) {
									OffusList = data.routerRules;
									if (OffusList.length > 0) {
										for (var i = 0; i < OffusList.length; i++) {
											if (OffusList[i].onUsFlag == false) {
												$('.offus_table').show();
												$('.offus_table')
														.append(
																'<tr class="boxtext"><td align="left" valign="middle">'
																		+ OffusList[i].acquirerMap
																		+ '</td><td align="left" valign="middle">'
																		+ OffusList[i].currency
																		+ '</td><td align="left" valign="middle">'
																		+ OffusList[i].paymentType
																		+ '</td><td align="left" valign="middle">'
																		+ OffusList[i].mopType
																		+ '</td><td align="left" valign="middle">'
																		+ OffusList[i].transactionType
																		+ '</td><td align="left" valign="top">'
																		+ OffusList[i].cardHolderType
																		+ '</td><td align="left" valign="middle">'
																		+ OffusList[i].paymentsRegion
																		+ '</td><td align="left" valign="middle">'
																		+ '<button type="button" class="btn btn-danger remove-row" data-id="'
																		+ OffusList[i].id
																		+ '">&times; Remove</button></td></tr>')

											} else {
												$('.onus_table').show();
												$('.onus_table')
														.append(
																'<tr class="boxtext"><td align="left" valign="middle">'
																		+ OffusList[i].currency
																		+ '</td><td align="left" valign="middle">'
																		+ OffusList[i].paymentType
																		+ '</td><td align="left" valign="middle">'
																		+ OffusList[i].mopType
																		+ '</td><td align="left" valign="middle">'
																		+ OffusList[i].transactionType
																		+ '</td><td align="left" valign="middle">'
																		+ OffusList[i].acquirerMap
																		+ '</td><td align="left" valign="top">'
																		+ '<button type="button" class="btn btn-danger remove-row" data-id="'
																		+ OffusList[i].id
																		+ '">&times; Remove</button></td></tr>')
											}
										}
									}
								},
								error : function(data) {
									// alert("Error, mapping not saved!!!! ");
									window.location.reload();
								}
							});
					// Remove
					$('.product-spec')
							.on(
									'click',
									'.remove-row',
									function(events) {
										var index = $(this).attr('data-id');
										var token = document
												.getElementsByName("token")[0].value;
										swal(
												{
													title : "Are you sure want to delete this Rule?",
													type : "warning",
													showCancelButton : true,
													confirmButtonColor : "#DD6B55",
													confirmButtonText : "Yes, delete it!",
													closeOnConfirm : false
												},
												function(isConfirm) {
													if (!isConfirm)
														return;
													$
															.ajax({
																type : "POST",
																url : "deleteRouterRule",
																timeout : 0,
																data : {
																	"id" : index,
																	"token" : token,
																	"struts.token.name" : "token",
																},
																success : function(
																		data) {
																	var response = data.response;
																	swal(
																			{
																				title : 'Rule Deleted!',
																				type : "success"
																			},
																			function() {
																				$(
																						this)
																						.closest(
																								'tr')
																						.remove();
																				window.location
																						.reload();
																			});
																},
																error : function(
																		data) {
																	window.location
																			.reload();
																}
															});
												});
										// $(this).closest('tr').remove();
									});

					var acquirerCount = 1;
					var checkCount = 0;
					var checkedAcquirer = [];
					var acquirerCopy = $('.AcquirerList').html();
					var acquirerClone;
					var acquirerRemoveBtn = '<button type="button" class="btn btn-danger acquirerRemoveBtn">Remove</button>';
					var cloneIndex = $(".AcquirerList > [class^='Acquirer']").length;

					$('#offus_section input[name="Acquirer1"]').live('click',
							function() {
								$('.Acquirer1 .acquirerRemoveBtn').remove();
								$('.acquirerCloneBtn').show();
							});

					$('.acquirerCloneBtn')
							.live(
									'click',
									function() {
										$(
												'.Acquirer' + cloneIndex
														+ ' .acquirerRemoveBtn')
												.remove();
										checkedAcquirer.push($(
												'#offus_section input[name="Acquirer'
														+ cloneIndex
														+ '"]:checked').val());
										// alert(checkedAcquirer.join(', ')); //
										// Alert
										cloneIndex++;
										acquirerClone = acquirerCopy.replace(
												/1/g, cloneIndex);
										$('.AcquirerList')
												.append(acquirerClone);
										for (var k = 0; k < checkedAcquirer.length; k++) {
											$(
													".Acquirer"
															+ cloneIndex
															+ ' input[type="radio"]')
													.each(
															function() {
																if ($(this)
																		.val() == checkedAcquirer[k]) {
																	$(this)
																			.attr(
																					"disabled",
																					"true");
																}
															});
										}
										$(".Acquirer" + cloneIndex).append(
												acquirerRemoveBtn);
										$('.acquirerCloneBtn').hide();
										$(
												'#offus_section input[name="Acquirer'
														+ cloneIndex + '"]')
												.live(
														'click',
														function() {
															if ($(this).attr(
																	'name') === 'Acquirer'
																	+ cloneIndex) {
																$(
																		'.acquirerCloneBtn')
																		.show();
															}
														});
									})

					$('#offus_section .AcquirerList input[type="radio"]')
							.live(
									'click',
									function() {
										var indexElem = parseInt($(this).attr(
												'name').replace("Acquirer", "")) - 1;
										// alert(typeof
										// checkedAcquirer[indexElem]); // Alert
										if (checkedAcquirer[indexElem] !== undefined) {
											checkedAcquirer[indexElem] = $(this)
													.val();
											// alert("changed"); // Alert
											// alert(checkedAcquirer.join(',
											// ')); // Alert
											// alert(indexElem); // Alert
											// alert(cloneIndex); // Alert

											while (indexElem < cloneIndex) {
												$(
														".Acquirer"
																+ (indexElem + 1)
																+ ' input[type="radio"]')
														.removeAttr("disabled");
												for (var k = 0; k < indexElem; k++) {
													$(
															".Acquirer"
																	+ (indexElem + 1)
																	+ ' input[type="radio"]')
															.each(
																	function() {
																		if ($(
																				this)
																				.val() == checkedAcquirer[k]) {
																			$(
																					this)
																					.removeAttr(
																							"checked");
																			$(
																					this)
																					.attr(
																							"disabled",
																							"true");
																		}
																	});
												}
												indexElem++
											}

										}
									});

					$('.acquirerRemoveBtn').live(
							'click',
							function() {
								checkedAcquirer.pop();
								alert(checkedAcquirer.join()); // Alert
								if (cloneIndex > 1) {
									$(this).parent().remove();
									$('.acquirerCloneBtn').show();
									cloneIndex--;
									if (cloneIndex > 1) {
										$(".Acquirer" + cloneIndex).append(
												acquirerRemoveBtn);
									}
								}
							})
				});
//

function addacq() {

	var itm = document.getElementById("wwctrl_Acquirer1");
	var cln = itm.cloneNode(true);
	document.getElementById("wwctrl_Acquirer2").appendChild(cln);
}

function showAdd() {
	document.getElementById("addButton").style.display = "block";
}

function enableSavaButton(event) {
	console.log(event.target.checked);
	if (event.target.checked) {
		document.getElementById("offus_submit").disabled = false;
		console.log("if");
	} else {
		document.getElementById("offus_submit").disabled = true;
		console.log("else");
	}

}

function reset() {
	document.getElementById('reset').reset();
}
