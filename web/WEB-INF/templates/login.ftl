<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <#include "html/link.html">
</head>
<body style="font-family: 'Fira Sans', sans-serif;">

<div class="wrapper">
    <div class="content">
        <#include "html/navbarAnonim.html">
        <row>
            <form method="Post" action="/login">
                <div class="mt-300px w-50 mx-auto text-center">
                    <h1 class="mb-4"><img src="img/icons8-login-32.png"> Авторизация</h1>
                </div>
                <div class="col-6 w-25 mx-auto text-center">
                    <input class="form-control mt-5" type="text" name="login" id="login" size="55%"
                           placeholder="Логин"/>
                    <input class="form-control mt-4" type="password" name="password" id="password" size="55%"
                           placeholder="Пароль"/>
                </div>
                <div class="form-check col-6 w-25 mx-auto text-center mt-4">
                    <input type="checkbox" name="remember_me" class="form-check-input" id="exampleCheck1">
                    <label class="form-check-label" for="exampleCheck1">Запомнить меня</label>
                </div>
                <div class="col-6 w-25 mx-auto text-center mb-4">
                    <button type="submit" class="btn btn-dark mx-1 btn-lg px-10 mt-4">Войти</button>
                </div>
            </form>
            <form  method="get" action="/registration">
                <div class="col-6 w-25 mx-auto text-center mb-4">
                    <button type="submit" class="btn btn-dark mx-1 btn-lg px-10 mt-4">Регистрация</button>
                </div>
            </form>
        </row>
    </div>

    <#include "html/copyright.html">
</div>
</body>
</html>