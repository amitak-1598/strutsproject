<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta http-equiv="refresh" content="899;url=index" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <title>Login</title>

  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="webapp/../assets/css/bootstrap.min.css" />

  <!-- Font Awesome CSS -->
  <link rel="stylesheet" href="webapp/../assets/plugins/fontawesome/css/fontawesome.min.css" />
  <link rel="stylesheet" href="webapp/../assets/plugins/fontawesome/css/all.min.css" />

  <!-- Custom CSS -->
  <link rel="stylesheet" href="webapp/../assets/css/style.css" />

  <!-- reCAPTCHA -->
  <script src="https://www.google.com/recaptcha/api.js"></script>

  <script>
    if (self === top) {
      document.body.style.display = "block";
    } else {
      top.location = self.location;
    }

    function ValidCaptcha() {
      // Add client-side captcha validation logic here
      return true;
    }
  </script>
</head>

<body>
  <div class="main-wrapper login-body">
    <div class="login-wrapper">
      <div class="container">

        <img class="img-fluid logo-dark mb-2" src="webapp/../images/logo.png" alt="Logo" />

        <div class="loginbox">
          <div class="login-right">
            <div class="login-right-wrap">
              <h1>Login</h1>
              <p class="account-subtitle">Access to our dashboard</p>

              <form method="post" onsubmit="return ValidCaptcha();">
                <br />
                <div class="form-group">
                  <label class="form-control-label" for="emailId">Username</label>
                  <input type="text" class="form-control" id="emailId" name="emailId"
                    placeholder="Enter your username" autocomplete="off" required />
                  <span id="error2"></span>
                </div>

                <div class="form-group">
                  <label class="form-control-label" for="password">Password</label>
                  <div class="pass-group">
                    <input type="password" class="form-control pass-input" id="password" name="password"
                      placeholder="Enter your password" autocomplete="off" required />
                  </div>
                </div>

                <div class="g-recaptcha" data-sitekey="6LfouiUqAAAAAN8fHjvb5GrgEkdPWC2fioTpuEQc"></div>

                <div class="form-group">
                  <div class="row">
                    <div class="col-6 text-right">
                      <a class="forgot-link" href="forgetPassword">Forgot Password?</a>
                    </div>
                  </div>
                </div>

                <button type="submit" class="btn btn-lg btn-block btn-primary">LOGIN</button>
              </form>

            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- Scripts -->
  <script src="webapp/../assets/js/jquery-3.5.1.min.js"></script>
  <script src="webapp/../assets/js/popper.min.js"></script>
  <script src="webapp/../assets/js/bootstrap.min.js"></script>
  <script src="webapp/../assets/js/feather.min.js"></script>
  <script src="webapp/../assets/js/script.js"></script>
</body>
</html>

