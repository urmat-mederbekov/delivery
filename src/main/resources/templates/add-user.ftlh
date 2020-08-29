<#import "partials/main.ftlh" as main>
<@main.renderWith>
    <div class="mx-auto mt-3 w-50">
        <form class="box-1" action="/users" method="post">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>
            <#if errors??>
                <#assign fieldNames = errors?map(fieldError -> fieldError.field)>
            </#if>
            <h3 class="text-center">Добавить курьера</h3>
            <div class="form-group">
                <#assign fieldName = "name">
                <#assign input ='<input class="form-control" type="text" name="name" placeholder="Имя" autofocus>'>
                <#if errors?? && !fieldNames?seq_contains(fieldName)>
                    <#assign inputWithValue = '<input class="form-control" type="text" name="name" value="${userForm.name}" placeholder="Имя" autofocus>'>
                </#if>
                <#include "partials/inputErrors.ftlh">
            </div>
            <div class="form-group">
                <#assign fieldName = "password">
                <#assign input ='<input class="form-control" type="password" name="password" placeholder="Пароль">'>
                <#if errors?? && !fieldNames?seq_contains(fieldName)>
                    <#assign inputWithValue = '<input class="form-control" type="password" name="password" value="${userForm.password}" placeholder="Пароль">'>
                </#if>
                <#include "partials/inputErrors.ftlh">
            </div>
            <div class="form-group">
                <#assign fieldName = "email">
                <#assign input ='<input class="form-control" type="email" name="email" placeholder="Email">'>
                <#if errors?? && !fieldNames?seq_contains(fieldName)>
                    <#assign inputWithValue = '<input class="form-control" type="email" name="email" value="${userForm.email}" placeholder="Email">'>
                </#if>
                <#include "partials/inputErrors.ftlh">
            </div>
            <p><button type="submit" class="btn btn-primary w-100">Добавить</button></p>
        </form>
    </div>
</@main.renderWith>