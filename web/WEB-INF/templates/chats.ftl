<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Chatlist</title>
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
            <div class="col-lg-7 col-md-6 mt-4">
                <#list chats as chat>
                    <div class="row w-100 mx-auto text-center">
                        <div class="col">
                            <div class="card mt-3" style="width: 22rem;">
                                <div class="card-body">
                                    <div class="d-flex mb-3 ml-3">
                                        <#if chat.getUser_first().getId() == user.getId()>
                                            <img class="img-circle" src="${chat.getUser_second().getAvatar()}" style="width: 50px; height: auto;">
                                            <div class="ml-4 my-auto text-dark">
                                                <h5>
                                                    ${chat.getUser_second().getSerName()} ${chat.getUser_second().getName()}
                                                </h5>
                                            </div>
                                        <#else>
                                            <img class="img-circle" src="${chat.getUser_first().getAvatar()}" style="width: 50px; height: auto;">
                                            <div class="ml-4 my-auto text-dark">
                                                <h5>
                                                    ${chat.getUser_first().getSerName()} ${chat.getUser_first().getName()}
                                                </h5>
                                            </div>
                                        </#if>
                                    </div>
                                    <form method="get" action="/chats">
                                        <button type="submit" class="btn btn-dark" name="id" value="${chat.getId()}">Открыть чат</button>
                                    </form>
                                </div>
                            </div>
                        </div>
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