<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
    <script src="http://code.jquery.com/jquery-3.3.1.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap-grid.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap-reboot.css">
    <link rel="stylesheet" href="extra-styles.css">
    <script src="../js/bootstrap.bundle.js"></script>
    <script src="../js/bootstrap.js"></script>
</head>
<body style="font-family: 'Fira Sans', sans-serif;">
<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <a href="/" class="navbar-brand">Ромкин авто сайт</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar7">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="navbar-collapse collapse justify-content-stretch" id="navbar7">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link text-white mr-lg-4" href="#">Каталог</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white mr-lg-4" href="#">Продать</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white mr-lg-4" href="#">О проекте</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="#">Войти</a>
            </li>
        </ul>
    </div>
</nav>
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