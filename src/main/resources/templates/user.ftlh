<#import "partials/main.ftlh" as main>
<@main.renderWith>
    <h3>Список курьеров</h3>

    <div class="row">
        <div class="col">Имя</div>
        <div class="col">Email</div>
    </div>
    <a href="/users/${user.id?c}/edit">
        <div class="row">
            <div class="col">${user.name}</div>
            <div class="col">${user.email}</div>
        </div>
    </a>
    <h3>Список заказов курьера</h3>
    <div class="row">
        <div class="col">#</div>
        <div class="col">Адрес</div>
        <div class="col">Ко Скольки</div>
        <div class="col">Состояние</div>
    </div>
    <#list items as order>
        <a href="/orders/${order.id?c}">
            <div class="row">
                <div class="col">${order?counter}</div>
                <div class="col">${order.destination}</div>
                <div class="col">${order.orderedTo}</div>
                <#if order.state == "COMPLETED">
                    <div class="col bg-success">Доставлен</div>
                <#elseif order.state == "NOT_COMPLETED">
                    <div class="col bg-warning">Не Доставлен</div>
                </#if>
            </div>
        </a>
    </#list>
    <#include "partials/pagination.ftlh">
</@main.renderWith>