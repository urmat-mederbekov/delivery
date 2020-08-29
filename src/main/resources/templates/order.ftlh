<#import "partials/main.ftlh" as main>
<@main.renderWith>
    <div class="row">
        <div class="col">Продукт</div>
        <div class="col">Адрес</div>
        <div class="col">Дата Заказа</div>
        <div class="col">Ко Скольки</div>
        <div class="col">Состояние</div>
        <div class="col">Курьер</div>
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
            <div class="col bg-warning">Не Доставлен</div>
        </#if>
        <#if order.user??>
            <div class="col">${order.user.name}</div>
        <#else >
            <div class="col">Не выбран</div>
        </#if>
        <div class="col">
            <a href="/orders/${order.id?c}/delete" class="btn btn-danger">Удалить</a>
            <a href="/orders/${order.id?c}/edit" class="btn btn-secondary">Редактировать</a>
            <a href="/orders/${order.id?c}/assign-courier" class="btn btn-primary">Передать заказ курьеру</a>
        </div>
    </div>
</@main.renderWith>