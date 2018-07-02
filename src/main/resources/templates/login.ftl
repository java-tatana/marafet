<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
Введите учетные данные для входа

<@l.login "/login" />
<a href="/registration">Зарегистрироваться</a>
</@c.page>
