<#import "partials/main.ftlh" as main>
<@main.renderWith>
    <form action="/users/${courier.id?c}/orders/search">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <div class="form-group">
            <input type="text" name="query" class="form-control" placeholder="Искать" required>
        </div>
        <button name="login" class="btn btn-primary btn-block">Искать</button>
    </form>

    <h3>Список моих заказов</h3>
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
                <div class="col">${order.orderedTo.toLocalDate()} ${order.orderedTo.toLocalTime()}</div>
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