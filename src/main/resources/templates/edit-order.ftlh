<#import "partials/main.ftlh" as main>
<@main.renderWith>
    <form class="box-1" action="/orders/${order.id}/edit" method="post">
    <#if _csrf??>
        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
    </#if>
    <#if errors??>
        <#assign fieldNames = errors?map(fieldError -> fieldError.field)>
    </#if>
    <h3 class="text-center">Редактировать заказ</h3>
    <div class="form-group">
        <label class="col-form-label">Товар</label>
        <#assign fieldName = "good">
        <#assign input ='<input class="form-control" type="text" name="good" value="${order.good}" placeholder="Товар" autofocus>'>
        <#if errors?? && !fieldNames?seq_contains(fieldName)>
            <#assign inputWithValue = '<input class="form-control" type="text" name="good" value="${orderForm.good}" placeholder="Товар" autofocus>'>
        </#if>
        <#include "partials/inputErrors.ftlh">
    </div>
    <div class="form-group">
        <label class="col-form-label">Адрес</label>
        <#assign fieldName = "destination">
        <#assign input ='<input class="form-control" type="text" name="destination" value="${order.destination}" placeholder="Адрес">'>
        <#if errors?? && !fieldNames?seq_contains(fieldName)>
            <#assign inputWithValue = '<input class="form-control" type="text" name="destination" value="${orderForm.destination}" placeholder="Адрес">'>
        </#if>
        <#include "partials/inputErrors.ftlh">
    </div>
    <div class="form-group">
        <label class="col-form-label">Ко скольки</label>
        <#assign fieldName = "orderedTo">
        <#assign input ='<input class="form-control" type="datetime-local" name="orderedTo"  value="${order.orderedTo}" placeholder="Ко скольки">'>
        <#if errors?? && !fieldNames?seq_contains(fieldName)>
            <#assign inputWithValue = '<input class="form-control" type="datetime-local" name="orderedTo" value="${orderForm.orderedTo}" placeholder="Ко скольки">'>
        </#if>
        <#include "partials/inputErrors.ftlh">
    </div>
    <div class="form-group">
        <label class="col-form-label">Курьер</label>
        <select class="form-control" name="userId">
            <#if order.user??>
                <option selected="selected" value="${order.user.id?c}">${order.user.name}</option>
            </#if>
            <#list users as user>
                <#if order.user??>
                    <#if user.id?c != order.user.id?c>
                        <option value="${user.id?c}">${user.name}</option>
                    </#if>
                <#else >
                    <option value="${user.id?c}">${user.name}</option>
                </#if>
            </#list>
        </select>
        <#if errors??>
            <#list errors as fieldError>
                <#if fieldError.field == 'userId'>
                    <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                </#if>
            </#list>
        </#if>
    </div>
    <p><button type="submit" class="btn btn-primary w-100">Редактировать</button></p>
</@main.renderWith>