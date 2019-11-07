<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Вы действительно хотите выйти?</title>
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
            <div class="mt-300px w-50 mx-auto text-center">
                <h1 class="mb-4"><img src="img/icons8-exit-32.png">Вы успешно зарегистрировались</h1>
            </div>
            <div class="row w-100 justify-content-center">
                <form method="get" action="/catalog">
                    <div class="col-auto text-center mb-4">
                        <button type="submit" class="btn btn-dark mx-1 btn-lg px-10 mt-4">Ок</button>
                    </div>
                </form>
            </div>
        </row>
    </div>

    <#include "html/copyright.html" >

</div>
</body>
</html>