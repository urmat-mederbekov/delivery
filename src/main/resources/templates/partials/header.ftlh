<header>
    <nav class="site-header sticky-top py-1">
        <div class="container d-flex flex-column flex-md-row justify-content-between">
            <#if courier??>
                <a class="py-2 ml-auto mr-5 d-none d-md-inline-block" href="/users/${courier.id?c}/orders">Мои заказы</a>
                <form class="flex box-128" action="/logout" method="post">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <button class="btn btn-dark py-2 d-none d-md-inline-block logout-button">Выйти</button>
                </form>
            <#elseif admin??>
                <a class="py-2 ml-auto mr-5 d-none d-md-inline-block" href="/orders">Все заказы</a>
                <a class="py-2 ml-auto mr-5 d-none d-md-inline-block" href="/users">Все курьеры</a>
                <a class="py-2 ml-auto mr-5 d-none d-md-inline-block" href="/orders/add-order">Добавить заказ</a>
                <a class="py-2 ml-auto mr-5 d-none d-md-inline-block" href="/users/add-user">Добавить курьера</a>
                <form class="flex box-128" action="/logout" method="post">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <button class="btn btn-dark py-2 d-none d-md-inline-block logout-button">Выйти</button>
                </form>
            <#else>
                <a class="py-2 ml-auto mr-5 d-none d-md-inline-block" href="/login">Войти</a>
            </#if>
        </div>
    </nav>
</header>
