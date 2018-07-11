<#import "parts/common.ftl" as c>

<@c.page>
<h5>${username}</h5>
<div class="mb-4">
    Введите новые денные
</div>
<form method="post">

    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Password:</label>
        <div class="col-sm-5">
            <input type="password" name="password" class="form-control" placeholder="Password"/>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Email:</label>
        <div class="col-sm-5">
            <input type="email" name="email" value="${email!''}" class="form-control" placeholder="Email"/>
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <div class="mb-2">
        <button type="submit" class="btn btn-primary">Сохранить</button>
    </div>
</form>
</@c.page>