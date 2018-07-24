<#import "parts/common.ftl" as c>


<@c.page>
<div class="form-row" xmlns="http://www.w3.org/1999/html">
    <div class="form-group col-md-6">
        <form method="get" action="/main" class="form-inline">
            <input type="text" name="filter" class="form-control" value="${filter?ifExists}"
                   placeholder="Поиск по валюте">
            <button class="btn btn-primary ml-2" type="submit">Найти</button>
        </form>
    </div>
</div>

<a class="btn btn-primary mb-3" data-toggle="collapse" href="#addAccount" role="button" aria-expanded="false"
   aria-controls="collapseExample">
    Добавить счет
</a>
<div class="collapse <#if account??>show</#if>" id="addAccount">
    <div class="form-group mt-3">
        <form method="post" action="addAccount" class="form-inline">

            <div class="form-row">
                <div class="col-md-6 mb-3">
                    <input class="form-control ${(titleError??)?string('is-invalid', '')}"
                           type="text" name="title" placeholder="Введите название"
                           value<#if account??>${account.title}</#if>/>
                     <#if titleError??>
                    <div class="invalid-feedback">
                        ${titleError}
                    </div>
                     </#if>
                </div>
            </div>

            <div class="col-md-3 mb-3">
                <input class="form-control ${(sumError??)?string('is-invalid', '')} ml-2"
                       type="number" name="sum" placeholder="Введите сумму"
                       value<#if account??>${account.sum}</#if>/>
                <#if sumError??>
                <div class="invalid-feedback">
                    Введите сумму
                </div>
                </#if>
            </div>

            <div class="col-md-3 mb-3">
                <input class="form-control ml-2 ${(currencyError??)?string('is-invalid', '')}"
                       type="text" name="currency" placeholder="Введите валюту"
                       value<#if account??>${account.currency}</#if>/>
                <#if currencyError??>
                    <div class="invalid-feedback">
                        ${currencyError}
                    </div>
                </#if>
            </div>

            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button class="btn btn-primary ml-2" type="submit">Сохранить</button>
        </form>
    </div>
</div>

    <#if accounts??>
<div class="card-columns">
    <#list accounts as account>
        <div class="card bg-light my-3">
            <div class="card-header bg-secondary">
                <#if account.title??>
                    ${account.title}
                <#else>
                Noname
                </#if>
                <button type="button" class="close">
                    <a href="/main/${account.id}">
                        <span class="far fa-trash-alt fa-xs fa-pull-right  e-icon--light"></span>
                    </a>
                </button>
            </div>
            <div class="card-body m-2">
                <span class="text-uppercase">${account.currency}</span>
                <i class="font-weight-bold">${account.sum}</i>
                <a class="ml-5" href="/transactions/${account.id}">Посмотреть</a>
            </div>
            <div class="card-footer text-muted" style="color:grey">
                ${account.getUser().getUsername()}

            </div>
        </div>
    </#list>
</div>
    <#else>
 <div class="alert alert-info col-sm-6" role="alert">
     У вас нет счетов!
 </div>
    </#if>

</@c.page>