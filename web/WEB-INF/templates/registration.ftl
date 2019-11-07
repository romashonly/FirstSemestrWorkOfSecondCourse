<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
    <#include "html/link.html">
</head>
<body style="font-family: 'Fira Sans', sans-serif;">
    <#if isAnonim == true>
        <#include "html/navbarAnonim.html">
    <#else>
        <#include "html/navbarUser.html">
    </#if>
<row>
    <div class="mt-5 w-50 mx-auto text-center">
        <h1 class="mb-4"><img src="img/iconadd.png"> Регистрация</h1>
    </div>
</row>
<form name="registration" action="/registration" method="post" enctype="multipart/form-data">

    <div class="container">
        <div class="row">
            <div class="col-sm-11 col-sm-offset-4">
                <div class="input-group ml-5">
                    <input type="text" aria-label="Last name" name="name" class="form-control" placeholder="Фамилия">
                    <input type="text" aria-label="First name" name="serName" class="form-control" placeholder="Имя">
                </div>
            </div>
        </div>




        <div class="row" id="inputInformation">
            <div class="col-2">
                <label class="mt-3" for="date">Дата рождения: </label>
            </div>
            <div class="col-6">
                <input class="form-control mt-2 mb-4" type="date" name="date_birth" value="2000-01-01" id="date" size="30%">
                <input class="form-control mb-4" type="text" name="city" id="city" size="55%" placeholder="Город"/>
                <input class="form-control mb-4" type="text" name="phone_number" id="number" size="55%" placeholder="Номер телефона"/>
                <input class="form-control mb-4" type="text" name="login" id="login" size="55%" placeholder="Логин"/>
                <input class="form-control mb-4" type="password" name="password" id="password" size="55%"
                       placeholder="Пароль"/>
                <form>
                    <div class="form-group">
                        <label for="my-file-selector">Аватар</label>
                        <label class="btn btn-dark" for="my-file-selector">
                            <input id="my-file-selector" type="file" name="photo" style="display:none"
                                   onchange="$('#upload-file-info').html(this.files[0].name)">
                            Добавить фото
                        </label>
                        <span class='label label-info' id="upload-file-info"></span>

                    </div>
                </form>
            </div>
        </div>



        <div class="row">
            <div class="col-sm"></div>
            <div class="col-sm">
                <input class="btn btn-dark border-black mx-1 btn-lg px-10 mt-4" type="submit" value="Зарегистрироваться" >
            </div>
            <div class="col-sm"></div>
        </div>

    </div>
</form>

</body>
</html>