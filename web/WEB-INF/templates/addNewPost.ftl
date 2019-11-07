<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Новое объявление</title>
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
            <div class="col-md">
                <h4 class="text-center mt-5 mr-4"><img src="img/icons8-add-new-ad-32.png" style="width: 24px"> Новое объявление</h4>
            </div>
            <div class="row w-100">
                <div class="w-100 text-center">
                    <form enctype="multipart/form-data" method="post">
                        <div class="my-4 mx-auto" style="width: 700px">
                            <p>Загрузите ваши фотографии</p>
                            <p><input type="file" name="image" class="filestyle" data-classButton="btn btn-dark ml-2"
                                      data-classIcon="icon-plus"
                                      data-buttonText="Выбрать файлы" multiple accept="image/*,image/jpeg">
                        </div>
                        <div class="row">
                            <div class="col-sm">
                                <div class="form-group" style="width: 330px">
                                    <label for="brand">Марка</label>
                                    <select class="form-control" id="brand" name="brand_car">
                                        <option>Lada(ВАЗ)</option>
                                        <option>BMW</option>
                                        <option>Mercedes</option>
                                        <option>Audi</option>
                                        <option>Volkswagen</option>
                                    </select>
                                </div>
                                <div class="form-group" style="width: 330px">
                                    <label for="model">Модель</label>
                                    <select class="form-control" id="model" name="model_car">
                                        <option>BMW</option>
                                        <option>S-class</option>
                                        <option>E-class</option>
                                        <option>C-class</option>
                                        <option>Lada(ВАЗ)</option>
                                        <option>BMW</option>
                                        <option>X1</option>
                                        <option>X3</option>
                                        <option>X5</option>
                                        <option>X6</option>
                                        <option>X7</option>
                                    </select>
                                </div>
                                <div class="form-group" style="width: 330px">
                                    <label for="gearbox">Коробка передач</label>
                                    <select class="form-control" id="gearbox" name="gearBox_type">
                                        <option>Lada(ВАЗ)</option>
                                        <option>BMW</option>
                                    </select>
                                </div>
                                <div class="form-group" style="width: 330px">
                                    <label for="bodyType">Тип кузова</label>
                                    <select class="form-control" id="bodyType" name="body_type">
                                        <option>Lada(ВАЗ)</option>
                                        <option>BMW</option>
                                    </select>
                                </div>
                                <div class="form-group" style="width: 330px">
                                    <label for="driveUnit">Привод</label>
                                    <select class="form-control" id="driveUnit" name="driveUnit_type">
                                        <option>Lada(ВАЗ)</option>
                                        <option>BMW</option>
                                    </select>
                                </div>
                                <div class="form-group" style="width: 330px">
                                    <label for="rudderType">Руль</label>
                                    <select class="form-control" id="rudderType" name="rudder_type">
                                        <option>Lada(ВАЗ)</option>
                                        <option>BMW</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm">
                                <div class="form-group" style="width: 330px">
                                    <label for="yearOfIssue">Год выпуска</label>
                                    <select class="form-control" id="yearOfIssue" name="year_issue">
                                        <option>Lada(ВАЗ)</option>
                                        <option>BMW</option>
                                    </select>
                                </div>
                                <div class="form-group" style="width: 330px">
                                    <label for="color">Цвет</label>
                                    <input type="text" class="form-control" id="color" placeholder="" name="color">
                                </div>
                                <div class="row">
                                    <div class="col-auto">
                                        <div class="form-group" style="width: 90px">
                                            <label for="engine">Объем дв.</label>
                                            <input type="text" class="form-control" id="engine" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-auto">
                                        <div class="form-group" style="width: 100px">
                                            <label for="engine2">Тип</label>
                                            <input type="text" class="form-control" id="engine2" placeholder="" name="engine">
                                        </div>
                                    </div>
                                    <div class="col-auto">
                                        <div class="form-group" style="width: 80px">
                                            <label for="engine3">Мощность</label>
                                            <input type="text" class="form-control" id="engine3" placeholder="">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group" style="width: 330px">
                                    <label for="milleage">Пробег, км</label>
                                    <input type="text" class="form-control" id="milleage" placeholder="" name="mileage">
                                </div>
                                <div class="form-group" style="width: 330px">
                                    <label for="condition">Состояние</label>
                                    <input type="text" class="form-control" id="condition" placeholder="" name="condition_type">
                                </div>
                                <div class="form-group" style="width: 330px">
                                    <label for="cost">Цена</label>
                                    <input type="text" class="form-control" id="cost" placeholder="" name="cost">
                                </div>
                            </div>
                        </div>
                        <div class="my-4 text-center">
                            <button type="submit" class="btn btn-dark">Создать объявление</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <#include "html/copyright.html">

</div>
</body>
</html>