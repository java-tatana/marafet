<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<div class="mb-4">
    Введите учетные данные для входа
</div>
    <@l.login "/login"  />
</@c.page>
