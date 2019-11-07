<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Авто</title>
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

        <div class="container-fluid no-gutters">
            <div class="row justify-content-center">
                <div class="col-lg-auto ml-5">
                    <div class="my-4 mx-auto">
                        <div class="row">
                            <div class="col mt-3">
                                <h4 class="card-title">${car.getBrand_car()} ${car.getModel_car()}, ${car.getYear_issue()}</h4>
                            </div>
                            <div class="col mt-3 mr-3 text-right">
                                <h4 class="card-title">${car.getCost()} р.</h4>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-8 col-md-7">
                            <div class="img-border bg-dark mx-auto">
                                <div id="carouselExampleIndicators" class="carousel slide max-w-700px mx-auto"
                                     data-ride="carousel">
                                    <div class="carousel-inner max-h-567px">
                                        <div class="carousel-item active">
                                            <img class="d-block w-100" src="${car.getImage()}" alt="First slide">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row my-4 text-dark justify-content-center">
                                <div class="col-auto">
                                    <p class="card-text"><span class="text-secondary font-weight-bold">Марка: </span>${car.getBrand_car()}</p>
                                </div>
                                <div class="col-auto">
                                    <p class="card-text"><span class="text-secondary font-weight-bold">Модель: </span>${car.getModel_car()}</p>
                                </div>
                                <div class="col-auto">
                                    <p class="card-text"><span
                                                class="text-secondary font-weight-bold">Год выпуска: </span>${car.getYear_issue()}</p>
                                </div>
                            </div>
                            <div class="row my-4 text-dark justify-content-center">
                                <div class="col-auto">
                                    <p class="card-text"><span class="text-secondary font-weight-bold">Цвет: </span>${car.getColor()}</p>
                                </div>
                                <div class="col-auto">
                                    <p class="card-text"><span
                                                class="text-secondary font-weight-bold">Двигатель: </span>${car.getEngine()}</p>
                                </div>
                                <div class="col-auto">
                                    <p class="card-text"><span class="text-secondary font-weight-bold">Пробег: </span>${car.getMileage()} км</p>
                                </div>
                            </div>
                            <div class="row my-4 text-dark justify-content-center">
                                <div class="col-auto">
                                    <p class="card-text"><span
                                                class="text-secondary font-weight-bold">Тип кузова: </span>${car.getBody_type()}</p>
                                </div>
                                <div class="col-auto">
                                    <p class="card-text"><span class="text-secondary font-weight-bold">КПП: </span>${car.getGearBox_type()}</p>
                                </div>
                                <div class="col-auto">
                                    <p class="card-text"><span class="text-secondary font-weight-bold">Привод: </span>${car.getDriveUnit_type()}</p>
                                </div>
                            </div>
                            <div class="row my-4 text-dark justify-content-center">
                                <div class="col-auto">
                                    <p class="card-text"><span class="text-secondary font-weight-bold">Руль: </span>${car.getRudder_type()}</p>
                                </div>
                                <div class="col-auto">
                                    <p class="card-text"><span
                                                class="text-secondary font-weight-bold">Состояние: </span>${car.getCondition_type()}</p>
                                </div>
                            </div>
                        </div>
                        <div class="d-inline-block mt-2 ml-5">
                            <div class="card" style="width: 18rem; background-color: #f0f0f0">
                                <div class="card-body">
                                    <div>
                                        <h5 class="card-title font-weight-bold">Контактное лицо:</h5>
                                    </div>
                                    <div class="mt-3">
                                        <form method="get" action="/profile">
                                            <button type="submit" class="btn btn-link text-dark" name="id" value="${car.getOwner().getId()}"
                                                    style="font-size: 22px">${car.getOwner().getName()}
                                            </button>
                                        </form>
                                    </div>
                                    <form method="get" action="/profile">
                                        <div class="mt-4 mx-auto" style="width: 15rem;">
                                            <button type="submit" class="btn btn-dark card-link w-100" name="id" value="${car.getOwner().getId()}">Написать продавцу
                                                <img
                                                        src="/img/email.png"></button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <form method="post" action="/catalog/car">
                                <div class="mt-4 mx-auto" style="width: 15rem;">
                                    <button type="submit" class="btn btn-dark card-link w-100" name="addToFavorites" value="true">Добавить в избранное <img
                                                src="/img/like.png"></button>
                                </div>
                            </form>
                            <form method="post" action="/catalog/car">
                                <div class="form-group mt-3 border-top border-dark" style="width: 18rem">
                                    <label for="exampleFormControlTextarea1" class="mt-2">Оставить комментарий: </label>
                                    <textarea class="form-control max-h-300px" id="exampleFormControlTextarea1"
                                              rows="3" name="textOfComment"></textarea>
                                </div>
                                <div class="mx-auto" style="width: 15rem;">
                                    <button type="submit" class="btn btn-dark card-link w-100 mb-1">Отправить</button>
                                </div>
                            </form>
                            <div class="form-group mt-3 border-top border-dark" style="width: 18rem">
                                <p class="mt-2">Комментарии: </p>
                            </div>
                            <div class="overflow-y-scroll mb-4 border border-secondary"
                                 style="width: 18rem; height: 280px">

                                <#list comments as comment>
                                    <div class="card font-italic font-size-11pt mt-2 ml-2" style="width: 16rem">
                                        <form method="get" action="profile?id=${comment.getSender().getId()}">
                                            <div class="card-header">
                                                <input name="id" type="hidden">
                                                <button type="submit" class="btn btn-link text-dark font-size-11pt">
                                                    ${comment.getSender().getName()}  ${comment.getSender().getSerName()}
                                                </button>
                                            </div>
                                        </form>
                                        <div class="card-body">
                                            <blockquote class="blockquote mb-0 font-size-11pt">
                                                <p>${comment.getText()}</p>
                                                <footer class="blockquote-footer">22.10.2019 22:53</footer>
                                            </blockquote>
                                        </div>
                                    </div>
                                </#list>
                            </div>
                        </div>
                    </div>
                </div>
<#--                <div class="col">-->
<#--                    <img src="img/orig.png" class="mt-4 ml-4 position-fixed max-w-260px">-->
<#--                </div>-->
                <#include "html/advertising2.html">
            </div>
        </div>
    </div>

    <#include "html/copyright.html">

</div>
</body>
</html>