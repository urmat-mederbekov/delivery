<#import "partials/main.ftlh" as main>
<@main.renderWith>
    <h3>Список курьеров</h3>

    <div class="row">
        <div class="col">#</div>
        <div class="col">Имя</div>
        <div class="col">Email</div>
    </div>
    <#list items as user>
        <a href="/users/${user.id?c}">
            <div class="row">
                <div class="col">${user?counter}</div>
                <div class="col">${user.name}</div>
                <div class="col">${user.email}</div>
            </div>
        </a>
    </#list>
    <#include "partials/pagination.ftlh">
</@main.renderWith>