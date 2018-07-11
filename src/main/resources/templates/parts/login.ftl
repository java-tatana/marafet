<#macro login path>

<form action="/login" method="post">

     <#--<#if message??>-->
    <#--<div class="alert alert-success" role="alert">-->
        <#--${message}-->
    <#--</div>-->
     <#--</#if>-->

    <div class="form-group row">
        <label class="col-sm-2 col-form-label">User Name:</label>
        <div class="col-sm-5">
            <input type="text" name="username" class="form-control" placeholder="User name"/>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Password:</label>
        <div class="col-sm-5">
            <input type="password" name="password" class="form-control" placeholder="Password"/>
        </div>
    </div>

    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <div class="mb-2"><button type="submit" class="btn btn-primary">Sign in</button></div>
    <div class="mb-2"><a href="/registration">Зарегистрироваться</a></div>

</form>
</#macro>

<#macro logout>
     <form action="/logout" method="post">
         <input type="hidden" name="_csrf" value="${_csrf.token}">
         <button type="submit" class="btn btn-primary">Выйти</button>
     </form>
</#macro>


<#macro registration>

<form method="post" action="/registration">
    <#--<#if message??>-->
    <#--<div class="alert alert-danger" role="alert">-->
        <#--${message}-->
    <#--</div>-->
    <#--</#if>-->

    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Имя пользователя:</label>
        <div class="col-sm-5">
            <input type="text ${(usernameError??)?string('is-invalid', '')}"
                   name="username" class="form-control" placeholder="Введите имя пользователя"
                    value<#if user??>${user.username}</#if>/>
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
        </div>
    </div>

    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Пароль:</label>
        <div class="col-sm-5">
            <input type="password ${(passwordError??)?string('is-invalid', '')}"
                   name="password" class="form-control" placeholder="Введите пароль"/>
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
        </div>
    </div>

    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Пароль (повторно):</label>
        <div class="col-sm-5">
            <input type="password ${(password2Error??)?string('is-invalid', '')}"
                   name="password2" class="form-control" placeholder="Введите пароль еще раз"/>
                <#if password2Error??>
                    <div class="invalid-feedback">
                        ${password2Error}
                    </div>
                </#if>
        </div>
    </div>

    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Email:</label>
        <div class="col-sm-5">
            <input type="email ${(emailError??)?string('is-invalid', '')}"
                   name="email" class="form-control" placeholder="Введите email"
                    value<#if user??>${user.email}</#if>/>
                <#if emailError??>
                    <div class="invalid-feedback">
                        ${emailError}
                    </div>
                </#if>
        </div>
    </div>

    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <div class="mb-2"><button type="submit" class="btn btn-primary">Зарегистрироваться</button></div>
</form>
</#macro>
