<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="shortcut icon" href="/strutsamit/images/logo.png" />
<title>Dashboard</title>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/series-label.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script src="https://code.highcharts.com/modules/accessibility.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.1/css/select2.min.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.1/js/select2.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		// Initialize select2
		$("#merchant").select2();
		$("#currency").select2();
	});
</script>
<style>
.navbar-side {
	
}
</style>
<script type="text/javascript">
	//to show new loader --Neeraj
	$.ajaxSetup({
		global : false,
		beforeSend : function() {
			toggleAjaxLoader();
		},
		complete : function() {
			toggleAjaxLoader();
		}
	});
</script>

<script>
	$(document).ready(function() {
		handleChange();

	});
	function handleChange() {
		lineChart();
		var currentDate = new Date();
		var first = currentDate.getDate();
		var last = currentDate.getDate() + 1;
		var dateFrom = new Date(currentDate.setDate(first));
		var dateTo = new Date(currentDate.setDate(last));
		statistics(dateFrom, dateTo);

	}
</script>
<script>
	$(document).ready(function() {
		$('.newteds button').click(function() {
			$('.newteds button').removeClass('btnActive');
			$(this).addClass('btnActive');
		});

		$("#buttonDay").click(function(env) {

			var currentDate = new Date();
			var first = currentDate.getDate();
			var last = currentDate.getDate() + 1;
			var dateFrom = new Date(currentDate.setDate(first));
			var dateTo = new Date(currentDate.setDate(last));
			statistics(dateFrom, dateTo);

		});

		$("#buttonWeekly").click(function(env) {
			var currentDate = new Date();
			var first = currentDate.getDate();
			var last = first - 6;
			var dateTo = new Date(currentDate.setDate(first));
			var dateFrom = new Date(currentDate.setDate(last));
			statistics(dateFrom, dateTo);

		});

		$("#buttonMonthly").click(function(env) {
			var currentDate = new Date();
			var first = currentDate.getDate();
			var last = first - 30;
			var dateTo = new Date(currentDate.setDate(first));
			var dateFrom = new Date(currentDate.setDate(last));
			statistics(dateFrom, dateTo);

		});

		$("#buttonYearly").click(function(env) {

			var currentDate = new Date();
			var first = currentDate.getDate();
			var last = first - 365;
			var dateTo = new Date(currentDate.setDate(first));
			var dateFrom = new Date(currentDate.setDate(last));
			statistics(dateFrom, dateTo);

		});

	});
</script>

<script type="text/javascript">
	function statistics(dateFrom, dateTo) {
		var token = document.getElementsByName("token")[0].value;
		$
				.ajax({
					url : "statisticsAction",
					type : "POST",
					data : {
						dateFrom : dateFrom,
						dateTo : dateTo,
						emailId : document.getElementById("merchant").value,
						currency : document.getElementById("currency").value,
						token : token,
						"struts.token.name" : "token",
					},
					success : function(data) {
						document.getElementById("dvTotalSuccess").innerHTML = data.statistics.totalSuccess;
						document.getElementById("dvApprovedAmount").innerHTML = data.statistics.approvedAmount;
						document.getElementById("dvTotalFailed").innerHTML = data.statistics.totalFailed;
						document.getElementById("dvTotalFailedAmount").innerHTML = data.statistics.totalFailedAmount;
						document.getElementById("dvTotalInvalid").innerHTML = data.statistics.totalInvalid;
						document.getElementById("dvtotalInvalidAmount").innerHTML = data.statistics.totalInvalidAmount;
						document.getElementById("dvTotalRefunded").innerHTML = data.statistics.totalRefunded;
						document.getElementById("dvRefundedAmount").innerHTML = data.statistics.refundedAmount;

					}
				});

	}

	function lineChart() {
		var token = document.getElementsByName("token")[0].value;
		$.ajax({
			url : "lineChartAction",
			type : "POST",
			data : {
				emailId : document.getElementById("merchant").value,
				currency : document.getElementById("currency").value,
				token : token,
				"struts.token.name" : "token",
			},
			success : function(data) {
				var a = [];
				var b = [];
				var c = [];
				var pieChartList = data.pieChart;
				for (var i = 0; i < pieChartList.length; i++) {
					var piechart = pieChartList[i];
					var success = parseInt(piechart.totalSuccess);
					var refund = parseInt(piechart.totalRefunded);
					var failled = parseInt(piechart.totalFailed);
					a.push(success);
					b.push(refund);
					c.push(failled);

				}
				$(function() {
					$('#container').highcharts(
							{
								title : {
									text : '',
									x : -20
								//center
								},
								subtitle : {
									text : '',
									x : -20
								},
								xAxis : {
									categories : [ '1', '2', '3', '4', '5',
											'6', '7', '8', '9', '10', '11',
											'12', '13', '14', '15', '16', '17',
											'18', '19', '20', '21', '22', '23',
											'24', '25', '26', '27', '28', '29',
											'30', '31' ]
								},
								yAxis : {
									title : {
										text : 'Number Of Transaction'
									},
									plotLines : [ {
										value : 0,
										width : 1,
										color : '#808080'
									} ]
								},
								tooltip : {
									valueSuffix : ''
								},
								legend : {
									layout : 'vertical',
									align : 'right',
									verticalAlign : 'middle',
									borderWidth : 0
								},
								series : [ {
									name : 'Total Success',
									data : a
								}, {
									name : 'Total Refunded',
									data : b
								}, {
									name : 'Total Failed',
									data : c
								} ]
							});
				});
			}
		});
	}
</script>
</head>
<body onload="handleChange();">

	<!-- Page Wrapper -->
	<section class="pagetop">
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<div class="pagetitle">
						<s:if
							test="%{#session.USER.UserType.name()=='ADMIN' || #session.USER_TYPE.name()=='SUPERADMIN'}">
							<h1 class="page-headerr">Dashboard</h1>
						</s:if>

						<s:else>
							<h1 class="page-headerr">Dashboard</h1>
						</s:else>
					</div>
				</div>
				<div class="col-md-4">
					<div class="allmerchantsection">
						<s:select name="merchants" class="form-control" id="merchant"
							headerKey="ALL MERCHANTS" headerValue="ALL MERCHANTS"
							listKey="emailId" listValue="businessName" list="merchantList"
							autocomplete="off" onchange="handleChange();" />
					</div>
				</div>
				<div class="col-md-2">
					<div class="currencymainpage">
						<s:select name="currency" id="currency" list="currencyMap"
							class="form-control" onchange="handleChange();" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12 text-center">
					<div class="mainpagebtnnav">
						<button type="button" id="buttonDay"
							class="newround btnActive newButton">
							<span>Day</span>
						</button>
						<button type="button" id="buttonWeekly" class="newround newButton">
							<span>Week</span>
						</button>
						<button type="button" id="buttonMonthly"
							class="newround newButton">
							<span>Month</span>
						</button>
						<button type="button" id="buttonYearly" class="newround newButton">
							<span>Year</span>
						</button>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xl-3 col-sm-6 col-12">
					<div class="card">
						<div class="card-body">
							<div class="dash-widget-header">
								<span class="dash-widget-icon bg-1"> <i
									class="fa fa-thumbs-up"></i>
								</span>
								<div class="dash-count">
									<div class="dash-title">Total Captured</div>
									<div class="dash-counts">
										<p id="dvTotalSuccess">0</p>
										<s:property value="%{statistics.totalSuccess}" />
									</div>
								</div>
							</div>

							<div class="boxbottomddata">
								<p>
									Approved Amount <span id="dvApprovedAmount"
										class="analyse-amount"> <s:property
											value="%{statistics.approvedAmount}" />
									</span>
								</p>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xl-3 col-sm-6 col-12">
					<div class="card">
						<div class="card-body">
							<div class="dash-widget-header">
								<span class="dash-widget-icon bg-2"> <i
									class="fa fa-thumbs-down"> </i>
								</span>
								<div class="dash-count">
									<div class="dash-title">Total Failed</div>
									<div class="dash-counts">
										<p class="card-title" id="dvTotalFailed">0</p>
										<s:property value="%{statistics.totalFailed}" />
									</div>
								</div>
							</div>

							<div class="boxbottomddata">
								<p>
									Failed Amount <span id="dvTotalFailedAmount"
										class="analyse-amount"> <s:property
											value="%{statistics.totalFailedAmount}" />
									</span>
								</p>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xl-3 col-sm-6 col-12">
					<div class="card">
						<div class="card-body">
							<div class="dash-widget-header">
								<span class="dash-widget-icon bg-3"> <i
									class="fa fa-exclamation-triangle"> </i>
								</span>
								<div class="dash-count">
									<div class="dash-title">Total Invalid</div>
									<div class="dash-counts">
										<p class="card-title" id="dvTotalInvalid">0</p>
										<s:property value="%{statistics.totalInvalid}" />
									</div>
								</div>

							</div>

							<div class="boxbottomddata">
								<p>
									Invalid Amount <span id="dvtotalInvalidAmount"
										class="analyse-amount"> <s:property
											value="%{statistics.totalInvalidAmount}" />
									</span>
								</p>
							</div>

						</div>
					</div>
				</div>
				<div class="col-xl-3 col-sm-6 col-12">
					<div class="card">
						<div class="card-body">
							<div class="dash-widget-header">
								<span class="dash-widget-icon bg-4"> <i
									class="fa fa-share"> </i>
								</span>
								<div class="dash-count">
									<div class="dash-title">Total Refund</div>
									<div class="dash-counts">
										<p class="card-title" id="dvTotalRefunded">0</p>
										<s:property value="%{statistics.totalRefunded}" />
									</div>
								</div>
							</div>
							<div class="boxbottomddata">
								<p>
									Refunded Amount <span id="dvRefundedAmount"
										class="analyse-amount"> <s:property
											value="%{statistics.refundedAmount}" />
									</span>
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>




	<div class="row">
		<div class="col-xl-7 d-flex">
			<div class="card flex-fill">
				<div class="card-header">
					<div class="d-flex justify-content-between align-items-center">
						<h5 class="card-title">Sale Transactions</h5>

					</div>
				</div>
				<div class="card-body">


					<div id="container"></div>
				</div>
			</div>
		</div>
		<div class="col-xl-5 d-flex">
			<div class="card flex-fill">
				<div class="card-header">
					<div class="d-flex justify-content-between align-items-center">
						<h5 class="card-title">Payment Method</h5>
					</div>
				</div>
				<div class="card-body">
					<div id="invoice_chart"></div>
					<div class="text-center text-muted">
						<div class="row">
							<div class="col-4">
								<div class="mt-4">
									<p class="mb-2 text-truncate">
										<i class="fas fa-circle text-primary mr-1"></i>Credit Card
									</p>
									<h5>₹ 2,132</h5>
								</div>
							</div>
							<div class="col-4">
								<div class="mt-4">
									<p class="mb-2 text-truncate">
										<i class="fas fa-circle text-success mr-1"></i> Debit Card
									</p>
									<h5>₹ 1,763</h5>
								</div>
							</div>
							<div class="col-4">
								<div class="mt-4">
									<p class="mb-2 text-truncate">
										<i class="fas fa-circle text-danger mr-1"></i> NetBanking
									</p>
									<h5>₹ 973</h5>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>