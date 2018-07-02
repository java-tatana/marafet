<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<div>
  <@l.logout/>
</div>

<div>
    <b>Добавить новую транзакцию</b>
    <form method="post" action="/transactions/${account.id}">
        <input type="date" name="date" placeholder="Выберите дату">
        <input type="number" name="sum" placeholder="Введите сумму">
        <input type="text" name="description" placeholder="Введите описание">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Сохранить</button>
    </form>
</div>

<div>Список транзакций</div>
    <#list transactions as transaction>
    <div>
        <b>${transaction.id}</b>
        <strong>${transaction.date}</strong>
        <span>${transaction.description}</span>
        <i>${transaction.sum}</i>
    </div>

</#list>

<a href="/main">Назад</a>

</@c.page>