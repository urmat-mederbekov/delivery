<#import "partials/main.ftlh" as main>
<@main.renderWith>
    <div class="mx-auto mt-3 w-50">
        <form class="box-1" action="/users" method="post">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>
            <h3 class="text-center">Зарегистрироваться</h3>
            <div class="form-group">
                <input class="form-control" type="text" name="name" placeholder="Имя" autofocus>
                <#if errors??>
                    <#list errors as fieldError>
                        <#if fieldError.field == 'name'>
                            <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                        </#if>
                    </#list>
                </#if>
            </div>
            <div class="form-group">
                <input class="form-control" type="text" name="password" placeholder="Пароль">
                <#if errors??>
                    <#list errors as fieldError>
                        <#if fieldError.field == 'password'>
                            <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                        </#if>
                    </#list>
                </#if>
            </div>
            <div class="form-group">
                <input class="form-control" type="email" name="email" placeholder="Email">
                <#if errors??>
                    <#list errors as fieldError>
                        <#if fieldError.field == 'email'>
                            <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
                        </#if>
                    </#list>
                </#if>
            </div>
            <p><button type="submit" class="btn btn-primary w-100">Добавить</button></p>
        </form>
    </div>
</@main.renderWith>