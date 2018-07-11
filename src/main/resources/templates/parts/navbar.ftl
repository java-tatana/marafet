<#include "security.ftl">
<#import "login.ftl" as l>

<nav class="navbar navbar-expand-lg navbar navbar-dark bg-dark">
    <a class="navbar-brand" href="/">
        <img src="/static/logo.svg" width="30" height="30" class="d-inline-block align-top" alt="">
        Marafet
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>


    <div class="collapse navbar-collapse ml-5" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/main">Счета</a>
            </li>
            <#--<#if isAdmin??>-->
            <li class="nav-item">
                <a class="nav-link" href="/user">Список пользователей</a>
            </li>
            <#--</#if>-->
                <#--<#if user??>-->
            <li class="nav-item">
                <a class="nav-link" href="/user/profile">Профиль</a>
            </li>
                <#--</#if>-->
        </ul>
        <i class="fas fa-user-circle mr-3 fa-2x e-icon--light"></i>
        <div class="navbar-text mr-3">name</div>
        <@l.logout/>

    </div>
</nav>