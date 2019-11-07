<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Главная страница</title>
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
                <div class="w-100 border border-secondary my-4 p-4" style="border-radius: 3px; background-color: #f0f0f0">
                    <form method="get" action="/catalog">
                        <div class="row w-100 justify-content-center">
                            <div class="col-auto text-center">
                                <div class="form-group" style="width: 350px;">
                                    <label class="text-center" for="mark">Марка автомобиля</label>
                                    <select class="form-control" id="mark" name="brandCar">
                                        <option>lada</option>
                                        <option>BMW</option>
                                        <option>Audi</option>
                                        <option>Mercedes</option>
                                        <option>Volvo</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-auto text-center">
                                <div class="form-group" style="width: 350px;">
                                    <label class="text-center" for="model">Модель</label>
                                    <select class="form-control" id="model" name="modelCar">
                                        <option>BMW</option>
                                        <option>S-class</option>
                                        <option>E-class</option>
                                        <option>C-class</option>
                                        <option>X1</option>
                                        <option>X3</option>
                                        <option>X5</option>
                                        <option>X6</option>
                                        <option>X7</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row w-100 justify-content-center">
                            <div class="col-auto text-center">
                                <label class="text-center" for="costBelov">Цена от:</label>
                                <input class="form-control" style="width: 350px"
                                       id="costBelov" type="text" placeholder="Введите цену" name="minCost">
                            </div>
                            <div class="col-auto text-center">
                                <label class="text-center" for="costAbove">Цена до:</label>
                                <input class="form-control" style="width: 350px"
                                       id="costAbove" type="text" placeholder="Введите цену" name="maxCost">
                            </div>
                        </div>
                        <div class="col-6 w-25 mx-auto text-center ">
                            <button type="submit" class="btn btn-dark mx-1 btn-lg px-10 mt-4">Применить</button>
                        </div>
                    </form>
                </div>

                <#list cars as car>
                    <div class="card my-2">
                        <form action="/catalog/car" method="get">
                            <div class="row">
                                <div class="col-4 outer">
                                    <div class="inner">
                                        <img class="card-img w-100 h-auto max-w-260px max-h-165px" src="${car.getImage()}"
                                             alt="Card image cap">
                                    </div>
                                </div>
                                <div class="col-8 card-body d-inline-block">
                                    <button type="submit" class="btn btn-link card-title text-dark h-auto"
                                            style="font-size: 20px" name="id" value="${car.getId()}">${car.getBrand_car()} ${car.getModel_car()}
                                    </button>
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
                </#list>
            </div>
            <#include "html/advertising2.html">
        </div>
    </div>

    <#include "html/copyright.html">

</div>
</body>
</html>