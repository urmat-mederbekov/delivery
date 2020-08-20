<#import "partials/main.ftlh" as main>
<@main.renderWith>
    <div class="mx-auto mt-3 w-50">
        <form class="box-1" action="/orders/${order.id?c}/assign-courier" method="post">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>
            <h3 class="text-center">Передать заказ курьеру</h3>
            <div class="form-group">
                <select class="form-control" name="userId">
                    <#list users as user>
                        <option value="${user.id?c}">${user.name}</option>
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
            <p><button type="submit" class="btn btn-primary w-100">Добавить</button></p>
        </form>
    </div>
</@main.renderWith>