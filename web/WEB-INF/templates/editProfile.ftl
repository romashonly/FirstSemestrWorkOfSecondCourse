<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Редактировать</title>
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

        <row>
            <div class="mt-4 w-50 mx-auto text-center">
                <h1 class="mb-4"><img src="img/icons8-undefined-32.png"> Редактирование профиля</h1>
            </div>
        </row>
        <form name="redaction" action="edit_profile" method="post" enctype="multipart/form-data">
            <div class="container">
                <div class="row">
                    <div class="col-sm-11 col-sm-offset-4">
                        <div class="input-group ml-5">
                            <input type="text" aria-label="ser_name" class="form-control" placeholder="Фамилия" name="ser_name" value="${user.getSerName()}">
                            <input type="text" aria-label="name" class="form-control" placeholder="Имя" name="name" value="${user.getName()}">
                        </div>
                    </div>
                </div>
            </div>


            <div class="container">
                <div class="row" id="inputInformation">
                    <div class="col-2">
                        <label class="mt-3" for="date">Дата рождения: </label>
                    </div>
                    <div class="col-6">
                        <input class="form-control mt-2 mb-4" type="date" name="age" value="2000-01-03" id="date"
                               size="30%">
                        <input class="form-control mb-4" type="text" name="city" value="${user.getCity()}" id="city" size="55%"
                               placeholder="Город"/>
                        <input class="form-control mb-4" type="text" name="phone_number" value="${user.getPhone_number()}" id="number" size="55%"
                               placeholder="Номер телефона"/>
                        <input class="form-control mb-4" type="text" name="login" value="${user.getLogin()}" id="login" size="55%"
                               placeholder="Логин"/>
                        <input class="form-control mb-4" type="password" name="password" value="${user.getPassword()} id="password" size="55%"
                               placeholder="Пароль"/>

                        <div class="form-group text-center">
                            <label for="my-file-selector">Аватар</label>
                            <label class="btn btn-dark" for="my-file-selector">
                                <input id="my-file-selector" type="file" name="photo" style="display:none"
                                       onchange="$('#upload-file-info').html(this.files[0].name)">
                                Добавить фото
                            </label>
                            <span class='label label-info' id="upload-file-info"></span>

                        </div>
                    </div>
                </div>

                <div class="row justify-content-center mb-4">
                    <div class="col-auto text-center mt-3">
                        <a class="btn btn-dark border-black btn-lg"
                           href="/profile" role="button">Отменить изменения</a>
                    </div>
                    <div class="col-auto text-center mt-3">
                        <button type="submit" class="btn btn-dark btn-lg">Сохранить данные</button>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <#include "html/copyright.html">

</div>
</body>
</html>