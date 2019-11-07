<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Профиль</title>
    <#include "html/link.html">
</head>
<body style="font-family: 'Fira Sans', sans-serif;">
<div class="wrapper">
    <div class="content">

        <#if isAnonim == true>
            <#include "html/navbarAnonim.html">
        <#else>
            <#include "html/navbarUser.html">
        </#if>

        <div class="container">
            <div class="row">
                <div class="col-6">
                    <div class="col-md pt-5 ml-4">
                        <h3>Профиль</h3>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-6">
                    <div class="col-md mt-5 font-20px ml-4">
                        <p style="word-spacing: 1.1em">${user.getSerName()} ${user.getName()}</p>
                    </div>
                </div>
                <div class="col-6">
                    <div class="mt-5">
                <#if other_user == "false" >
                        <a href="/edit_profile" class="btn btn-dark">Редактировать профиль</a>
                <#else>
                        <form method="post" >
                            <button type="submit" class="btn btn-dark"">Написать сообщение <img
                                        src="img/email.png">
                            </button>
                        </form>
                </#if>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-6 mx-auto max-w-50">
                    <div class="col-md mt-4 font-18px">
                        <div class="text-center">
                            <img class="mb-4" src="${user.getAvatar()}" width="450px">
                        </div>
                        <#if other_user == "false" >
                            <div class="text-center">
                                <a href="/post_car" class="btn btn-dark mb-4">Добавить новое объявление <img src="img/icons8-add-new-24.png"></a>
                            </div>
                        </#if>
                    </div>
                </div>
                <div class="col-6 mx-auto max-w-50">
                    <div class="col-md mt-4 font-18px">
                        <p style="word-spacing: 1.2em">Город: ${user.getCity()}</p>
                    </div>
                    <div class="col-md mt-5 font-18px">
                        <p style="word-spacing: 1.2em">Телефон: ${user.getPhone_number()}</p>
                    </div>
                    <div class="col-md mt-5 font-18px">
                        <p style="word-spacing: 1.2em">Логин: ${user.getLogin()}</p>
                    </div>
                    <div class="col-md mt-5 font-18px">
                        <p style="word-spacing: 1.2em">Дата рождения: ${user.getDate_birth()}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <#include "html/copyright.html">
</div>
</body>
</html>