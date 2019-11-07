<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
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
                <a href="/chats" class="btn btn-dark card-link mt-4 ml-4">Назад <img
                            src="img/icons8-delete-last.png"></a>
            </div>
            <div class="col mb-4">
                <div class="d-flex mt-4 border-dark border-top border-left border-right p-1 bg-dark row"
                     style="width: 650px">
                    <#if chat.getUser_first().getId() == user.getId()>
                        <img class="img-circle" src="${chat.getUser_second().getAvatar()}" style="width: 50px; height: auto;">
                        <div class="ml-4 my-auto text-white">
                            <h5>
                                ${chat.getUser_second().getSerName()} ${chat.getUser_second().getName()}
                            </h5>
                        </div>
                    <#else>
                        <img class="img-circle" src="${chat.getUser_first().getAvatar()}" style="width: 50px; height: auto;">
                        <div class="ml-4 my-auto text-white">
                            <h5>
                                ${chat.getUser_first().getSerName()} ${chat.getUser_first().getName()}
                            </h5>
                        </div>
                    </#if>
                </div>
                <div class="chat-block overflow-y-scroll border border-dark p-2 row align-items-end" style="height: 450px">

                    <#list messages as message>
                        <#if chat.getUser_first().getId() == user.getId()>
                            <#if message.getSender().getId() == chat.getUser_first().getId()>
                                <div class="text-right w-100">
                                    <span style="font-size: 10pt">${message.getDate_sending()}</span>
                                    <p class="speech">${message.getText()}</p>
                                </div>
                            <#else>
                                <div class="text-left w-100">
                                    <p class="speech">${message.getText()}</p>
                                    <span style="font-size: 10pt">${message.getDate_sending()}</span>
                                </div>
                            </#if>
                        <#else>
                            <#if message.getSender().getId() == chat.getUser_second().getId()>
                                <div class="text-right w-100">
                                    <span style="font-size: 10pt">${message.getDate_sending()}</span>
                                    <p class="speech">${message.getText()}</p>
                                </div>
                            <#else>
                                <div class="text-left w-100">
                                    <p class="speech">${message.getText()}</p>
                                    <span style="font-size: 10pt">${message.getDate_sending()}</span>
                                </div>
                            </#if>
                        </#if>
                    </#list>
                </div>
                <div class=" row border-dark border-bottom border-left border-right p-1 bg-dark"
                     style="width: 650px; height: 79px">
                    <div class="d-flex">
                        <form method="post" action="/chats">
                            <div class="float-left">
                                <textarea class="form-control max-h-70px my-auto w-509px"
                                          id="exampleFormControlTextarea1"
                                          rows="3" name="newText">
                                </textarea>
                            </div>
                            <div class="float-left">
                                <button type="submit" class="btn btn-light h-50 ml-1 mt-3" name="id_chat" value="${chat.getId()}">
                                    Отправить
                                    <img src="img/email.png">
                                </button>
                            </div>
                        </form>
                    </div>
                    <div>

                    </div>
                </div>
            </div>
            <div class="col">
            </div>
        </div>
    </div>

    <#include "html/copyright.html">

</div>
</body>
</html>