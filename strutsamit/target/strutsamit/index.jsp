<%@ page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.project.strutsamit.util.PropertiesManager"%>
<%@page import="com.project.strutsamit.common.UserDao"%>
<%@page import="java.net.URL"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="refresh" content="899;url=index" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<link rel="shortcut icon" href="/strutsamit/images/logo.png" />
<title>payvang - Priority Login</title>

<script src="../js/login.js"></script>

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="/strutsamit/assets/css/bootstrap.min.css" />

<!-- Fontawesome CSS -->
<link rel="stylesheet"
	href="/strutsamit/assets/plugins/fontawesome/css/fontawesome.min.css" />
<link rel="stylesheet"
	href="/strutsamit/assets/plugins/fontawesome/css/all.min.css" />

<!-- Main CSS -->
<link rel="stylesheet" href="/strutsamit/assets/css/style.css" />

<!-- reCAPTCHA -->
<script src="https://www.google.com/recaptcha/api.js"></script>

<script>
	if (self == top) {
		var theBody = document.getElementsByTagName('body')[0];
		if (theBody != null) {
			theBody.style.display = "block";
		}
	} else {
		top.location = self.location;
	}
</script>
</head>

<body>
	<div class="main-wrapper login-body">
		<div class="login-wrapper">
			<div class="container">

				<img class="img-fluid logo-dark mb-2"
					src="/strutsamit/images/logo.png" alt="Logo" />

				<div class="loginbox">
					<div class="login-right">
						<div class="login-right-wrap">
							<h1>Login</h1>
							<p class="account-subtitle">Access to our dashboard</p>

							<s:form  action="login" method="post"
								onselectstart="return false" oncontextmenu="return false;">
								<s:token />
								<br />
								<span id="merchantEnviroment"></span>
								<div>
									<span><s:actionmessage /></span>
								</div>

								<div class="form-group">
									<label class="form-control-label" for="emailId">Username</label>
									<input type="text" name="emailId" class="form-control"
										id="emailId" placeholder="Enter your username"
										autocomplete="off" required onkeypress="emailCheck()" /> <span
										id="error2"></span>
								</div>

								<div class="form-group">
									<label class="form-control-label" for="username">Password</label>
									<div class="pass-group">
										<input type="password" name="password"
											class="form-control pass-input" id="password"
											placeholder="Enter your password" autocomplete="off" required
											onkeypress="passCheck()" /> <span class=""></span> <span
											id="error2"></span>
									</div>
								</div>

								<div class="rederror" id="error3"></div>

								<div class="g-recaptcha"
									data-sitekey="6LfouiUqAAAAAN8fHjvb5GrgEkdPWC2fioTpuEQc"></div>

								<div class="form-group">
									<div class="row">
										<div class="col-6 text-right">
											<a class="forgot-link" href="forgetPassword">Forgot
												Password?</a>
										</div>
									</div>
								</div>

								<div>
									<s:fielderror class="redvalid">
										<s:param>userId</s:param>
										<s:param>password</s:param>
									</s:fielderror>

									<s:submit cssClass="btn btn-lg btn-block btn-primary"
										key="submit" value="LOGIN" onclick="return ValidCaptcha();" />
								</div>
							</s:form>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

	<!-- Scripts -->
	<script src="/strutsamit/assets/js/jquery-3.5.1.min.js"></script>
	<script src="/strutsamit/assets/js/popper.min.js"></script>
	<script src="/strutsamit/assets/js/bootstrap.min.js"></script>
	<script src="/strutsamit/assets/js/feather.min.js"></script>
	<script src="/strutsamit/assets/js/script.js"></script>
</body>
</html>
