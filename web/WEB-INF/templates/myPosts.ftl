<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Мои объявления</title>
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

            <div class="row w-100">
                <#include "html/advertising1.html">
                <div class="col-lg-7 col-md-6">
                    <div class="row mt-5 mb-5">
                        <div class="col">
                            <h5><img src="img/icons8-owner-32.png" style="width: 24px"> Мои объявления</h5>
                        </div>
                        <div class="col text-right">
                            <a href="/post_car" class="btn btn-dark">Добавить новое объявление <img src="img/icons8-add-new-24.png"></a>
                        </div>

                    </div>

                    <#list myCars as car>
                        <div class="card my-2">
                            <form>
                                <div class="row">
                                    <div class="col-4 outer">
                                        <div class="inner"><img class="card-img w-100 h-auto max-w-260px max-h-165px"
                                                                src="${car.getImage()}" alt="Card image cap"></div>
                                    </div>
                                    <div class="col-8 card-body d-inline-block">
                                        <button type="submit" class="btn btn-link card-title text-dark h-auto"
                                                style="font-size: 20px">${car.getBrand_car()} ${car.getModel_car()}</button>
                                        <div class="mb-3">
                                            <h6 class="card-text text-secondary">Год выпуска: ${car.getYear_issue()}</h6>
                                        </div>
                                        <div class="row mb-3 text-secondary">
                                            <div class="col">
                                                <p class="card-text">${car.getBody_type()}</p>
                                            </div>
                                            <div class="col">
                                                <p class="card-text">${car.getEngine()}</p>
                                            </div>
                                            <div class="col">
                                                <p class="card-text">Пробег: ${car.getMileage()} км</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col">
                                            </div>
                                            <div class="col">
                                            </div>
                                            <div class="col pr-5">
                                                <h6 class="card-text text-right">Цена: ${car.getCost()} р.</h6>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </#list>

                <#include "html/advertising2.html">

            </div>
        </div>
    </div>

    <#include "html/copyright.html">

</div>
</body>
</html>