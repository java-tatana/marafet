<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>

<div class="mb-5"><h2>Список транзакций </h2></div>


    <a class="btn btn-primary mb-3" data-toggle="collapse" href="#addTransaction" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Добавить новую транзакцию
    </a>

    <div class="collapse" id="addTransaction">
        <div class="form-group mt-3">
            <form method="post" action="/transactions/${account.id}" class="form-inline">
                <div class='input-group date col-sm-2'>
                    <input type='date' name="date" class="form-control" placeholder="Выберите дату"/>
                </div>

                <input class="form-control" type="number" name="sum" placeholder="Введите сумму">
            <#--<input type="file" name="file">-->
                <div class="form-group">
                    <select name="category" class="custom-select ml-2">
                        <option selected>Выберите категорию</option>
                        <#list categories as category>
                          <option value="${category}">${category}</option>
                        </#list>
                    </select>
                </div>

                <div class="form-group">
                    <select name="type" class="custom-select ml-2">
                        <option selected>Выберите тип</option>
                        <#list types as type>
                          <option value="${type}">${type}</option>
                        </#list>
                    </select>
                </div>

                <input class="form-control ml-2 col-sm-5" type="text" name="description" placeholder="Введите описание">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button class="btn btn-primary mb-2" type="submit">Сохранить</button>
            </form>

        </div>
    </div>


    <#if transactions??>
        <#assign a=0>
    <table class="table">
    <thead class="thead-dark">
        <#if transactions??>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Дата</th>
            <th scope="col">Сумма</th>
            <th scope="col">Описание</th>
            <th scope="col">Тип</th>
        </tr>
        </thead>
        </#if>
    <#list transactions as transaction>
        <#assign a=a+1>
        <tbody>
        <#if transaction.type = "INCOME">
            <tr class="table-success">
        <#else>
        <tr class="table-danger">
        </#if>
            <th scope="row">${a}</th>
            <td>${transaction.date}</td>
            <td>${transaction.sum} ${account.currency}</td>
            <td>${transaction.description}</td>
            <td>${transaction.type}</td>
        </tr>
        </tbody>
    </#list>
    </table>

    <#else>
   <div class="alert alert-info" role="alert">
       У вас нет транзакций для этого счета!
   </div>
    </#if>


<a href="/main">Назад</a>

</@c.page>