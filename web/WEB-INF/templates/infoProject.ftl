<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>О проекте</title>
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
            <div class="col-6">
                <h2 class="mt-4 mb-4 text-center">О проекте</h2>
                <div class="mb-2">
                    <p>&nbsp;&nbsp;&nbsp;&nbsp;Ромкин авто сайт - автомобильный сайт в Рунете, через который ежегодно
                        продаётся более полутора миллионов
                        подержанных машин. Создан в 1996 году Михаилом Рогальским. В 2014 году был выкуплен компанией
                        «Яндекс».
                        Весной 2016 года в состав Auto.ru вошёл агрегатор «Яндекс.Авто», затем под этим именем была
                        запущена
                        информационно-развлекательная платформа для автомобилей.</p>
                </div>
                <div class="text-center">
                    <img src="img/infoImage.jpg" style="width: 700px">
                </div>
                <div class="mt-4">
                    <p>«Ромкин авто сайт» — один из крупнейших в стране автомобильных классифайдов. Только за 2017 год
                        через доску объявлений
                        прошли 1,5 миллиона подержаных и новых автомобилей[16]. В 2015 году газета «Коммерсантъ»
                        приводила слова
                        директора ИА «Автодилер-Петербург» Михаила Чаплыгина, который отмечал, что на «Авто.ру» завязан
                        российский
                        рынок оценки подержанных авто: дилеры ориентируются на статистику сайта, определяя цены на
                        машины</p>
                </div>
            </div>
            <#include "html/advertising2.html">
        </div>
    </div>

    <#include "html/copyright.html">

</div>
</body>
</html>