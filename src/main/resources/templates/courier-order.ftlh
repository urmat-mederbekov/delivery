<#import "partials/main.ftlh" as main>
<@main.renderWith>
    <div class="row">
        <div class="col">Продукт</div>
        <div class="col">Адрес</div>
        <div class="col">Дата Заказа</div>
        <div class="col">Ко Скольки</div>
        <div class="col">Состояние</div>
        <div class="col">Действия</div>
    </div>
    <div class="row">
        <div class="col">${order.good}</div>
        <div class="col">${order.destination}</div>
        <div class="col">${order.orderedAt.toLocalDate()} ${order.orderedAt.toLocalTime()}</div>
        <div class="col">${order.orderedTo.toLocalDate()} ${order.orderedTo.toLocalTime()}</div>
        <#if order.state == "COMPLETED">
            <div class="col bg-success">Доставлен</div>
        <#elseif order.state == "NOT_COMPLETED">
            <div class="col bg-warning">Не доставлен</div>
        </#if>
        <div class="col">
            <#if order.state == "COMPLETED">
                <a href="/orders/${order.id?c}/state" class="btn btn-success">Отметить как не выполнено</a>
            <#elseif order.state == "NOT_COMPLETED">
                <a href="/orders/${order.id?c}/state" class="btn btn-warning">Отметить как выполнено</a>
            </#if>
        </div>
    </div>
</@main.renderWith>