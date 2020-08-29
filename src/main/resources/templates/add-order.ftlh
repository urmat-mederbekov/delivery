<#import "partials/main.ftlh" as main>
<@main.renderWith>
    <div class="mx-auto mt-3 w-50">
        <form class="box-1" action="/orders" method="post">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>
            <#if errors??>
                <#assign fieldNames = errors?map(fieldError -> fieldError.field)>
            </#if>
            <h3 class="text-center">Добавить заказ</h3>
            <div class="form-group">
                <#assign fieldName = "good">
                <#assign input ='<input class="form-control" type="text" name="good" placeholder="Товар" autofocus>'>
                <#if errors?? && !fieldNames?seq_contains(fieldName)>
                    <#assign inputWithValue = '<input class="form-control" type="text" name="good" value="${orderForm.good}" placeholder="Товар" autofocus>'>
                </#if>
                <#include "partials/inputErrors.ftlh">
            </div>
            <div class="form-group">
                <#assign fieldName = "destination">
                <#assign input ='<input class="form-control" type="text" name="destination" placeholder="Адрес">'>
                <#if errors?? && !fieldNames?seq_contains(fieldName)>
                    <#assign inputWithValue = '<input class="form-control" type="text" name="destination" value="${orderForm.destination}" placeholder="Адрес">'>
                </#if>
                <#include "partials/inputErrors.ftlh">
            </div>
            <div class="form-group">
                <#assign fieldName = "orderedTo">
                <#assign input ='<input class="form-control" type="datetime-local" name="orderedTo" placeholder="Ко скольки">'>
                <#if errors?? && !fieldNames?seq_contains(fieldName)>
                    <#assign inputWithValue = '<input class="form-control" type="datetime-local" name="orderedTo" value="${orderForm.orderedTo}" placeholder="Ко скольки">'>
                </#if>
                <#include "partials/inputErrors.ftlh">
            </div>
            <p><button type="submit" class="btn btn-primary w-100">Добавить</button></p>
        </form>
    </div>
</@main.renderWith>