<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<div>
  <@l.logout/>
    <span><a href="/user"> User list</a></span>
</div>

<div>
    <b>Добавить новый счет</b>
    <form method="post" action="addAccount">
        <input type="text" name="currency" placeholder="Введите валюту">
        <input type="number" name="sum" placeholder="Введите сумму">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Сохранить</button>
    </form>
</div>

<div>Список счетов</div>
<form method="get" action="/main">
    <input type="text" name="filter" value="${filter}">
    <button type="submit">Найти</button>
</form>
<#list accounts as account>
    <div>
        <b>${account.id}</b>
        <strong>${account.getUser().getUsername()}</strong>
        <span>${account.currency}</span>
        <i>${account.sum}</i>
        <a href="/transactions/${account.id}">Посмотреть</a>
    </div>
<#else>
У вас не счетов

</#list>
</@c.page>