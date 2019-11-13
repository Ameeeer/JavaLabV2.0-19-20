<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>InnerPage</title>
    <link rel="stylesheet" href="css/main.css" type="text/css">
    <link rel="stylesheet" href="css/bootstrap.css" type="text/css">
</head>
<body class="mainPage">
<div id="controlPanel_1">
    <div id="">
        <form action="/login" role="form" method="get" style="max-height: 92px">
            <div class="form-group">
                <input type="email" id="email" name="emailToCheck" class="rounded-top rounded-bottom col-2"
                       placeholder="Email">
            </div>
            <div class="form-group">
                <input type="password" id="password" name="passwordToCheck" class="rounded-top rounded-bottom col-2"
                       placeholder="Password">
            </div>
            <div>
                <input type="submit" value="SignIn" id="signIn"
                       class="rounded-top rounded-bottom btn btn-outline-success signInButton">
            </div>
        </form>
        <form action="/registration" method="get" style="max-height: 100px">
            <div>
                <input type="submit" value="Registration" id="signUp"
                       class="rounded-top rounded-bottom btn btn-outline-success registrateButton">
            </div>
        </form>
    </div>
</div>
</body>
</html>