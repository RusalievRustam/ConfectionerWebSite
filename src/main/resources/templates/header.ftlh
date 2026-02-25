<aside id="sidebar">
    <div id="totalBudget">
        <span>Бюджет: </span>
        <span id="budget">Загрузка...</span>
        <span> сом</span>
    </div>
    <hr class="divider">
    <nav id="menu">
        <ul>
            <li><a href="/products">Продукты</a></li>
            <li><a href="/rawMaterials">Сырье</a></li>
            <li><a href="/purchaseMaterials">Закупка сырья</a></li>
            <li><a href="/productSales">Продажа продукта</a></li>
            <li><a href="/productions">Производство продукта</a></li>

            <li><a href="/salaryPayments">Зарплата</a></li>
            <li><a href="/loans">Кредиты</a></li>
            <li><a href="/reports">Отчёты</a></li>
            <li><a href="/employees">Сотрудники</a></li>
        </ul>
    </nav>
    <div style="display:flex; flex-direction:column; align-items:flex-start; gap:6px;">
        <#-- если пользователь залогинен -->
        <#if currentUser??>
            <div>Пользователь: <b>${currentUser}</b></div>

            <#if currentRole??>
                <div>Роль: <b>${currentRole}</b></div>
            </#if>

            <form action="/logout" method="post">
                <#-- CSRF, если включен -->
                <#if _csrf??>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                </#if>
                <button type="submit">Выйти</button>
            </form>
        <#else>
            <a href="/login">Войти</a>
        </#if>
    </div>
</aside>

<script>
    document.addEventListener("DOMContentLoaded", () => {
        fetch('/budget')
            .then(r => {
                if (!r.ok) throw new Error("Ошибка при загрузке данных");
                return r.json();
            })
            .then(data => {
                document.getElementById("budget").innerText = data.budget.toLocaleString("ru-RU");
            })
            .catch(err => {
                console.error("Ошибка получения бюджета:", err);
                document.getElementById("budget").innerText = "Ошибка";
            });
    });
</script>