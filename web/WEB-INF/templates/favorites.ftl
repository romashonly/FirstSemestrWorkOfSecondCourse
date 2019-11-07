<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Избранное</title>
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
            <div class="col">
                <img src="img/image.gif" class="mt-4 ml-5 position-fixed">
            </div>
            <div class="col-lg-7 col-md-6">
                <div class="text-center mt-5 mb-4">
                    <h5><img src="/img/like.png"> Избранные объявления</h5>
                </div>

                <#list favorites as favorite>
                    <div class="card my-2">
                        <form>
                            <div class="row">
                                <div class="col-4 outer">
                                    <div class="inner"><img class="card-img w-100 h-auto max-w-260px max-h-165px"
                                                            src="${favorite.getCar().getImage()}" alt="Card image cap"></div>
                                </div>
                                <div class="col-8 card-body d-inline-block">
                                    <button type="submit" class="btn btn-link card-title text-dark h-auto"
                                            style="font-size: 20px">${favorite.getCar().getBrand_car()} ${favorite.getCar().getModel_car()}</button>
                                    <div class="mb-3">
                                        <h6 class="card-text text-secondary">Год выпуска: ${favorite.getCar().getYear_issue()}</h6>
                                    </div>
                                    <div class="row mb-3 text-secondary">
                                        <div class="col">
                                            <p class="card-text">${favorite.getCar().getBody_type()}</p>
                                        </div>
                                        <div class="col">
                                            <p class="card-text">${favorite.getCar().getEngine()}</p>
                                        </div>
                                        <div class="col">
                                            <p class="card-text">Пробег: ${favorite.getCar().getMileage()} км</p>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col">
                                        </div>
                                        <div class="col">
                                        </div>
                                        <div class="col pr-5">
                                            <h6 class="card-text text-right">Цена: ${favorite.getCar().getCost()} р.</h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </#list>
            </div>
            <div class="col">
                <img src="img/orig.png" class="mt-4 ml-3 position-fixed">
            </div>
        </div>
    </div>

    <#include "html/copyright.html">
</div>
</body>
</html>