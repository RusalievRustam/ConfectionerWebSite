<header>
    <nav id="menu">
        <ul>
            <li><a href="/products">Продукты</a></li>
            <li><a href="/rawMaterials">Сырье</a></li>
            <li><a href="/purchaseMaterials">Закупка сырья</a></li>
            <li><a href="/productSales">Продажа продукта</a></li>
            <li><a href="/productions">Производство продукта</a></li>
        </ul>
        <div id="totalBudget">
            <strong>Бюджет: </strong>
            <span id="budget">Загрузка...</span>
            <strong> сом</strong>
        </div>
    </nav>
</header>

<script>
    document.addEventListener("DOMContentLoaded", () => {
        fetch('/budget') // API для получения бюджета
            .then(response => {
                if (!response.ok) throw new Error("Ошибка при загрузке данных");
                return response.json();
            })
            .then(data => {
                document.getElementById("budget").innerText = data.budget.toLocaleString("ru-RU");
            })
            .catch(error => {
                console.error("Ошибка получения бюджета:", error);
                document.getElementById("budget").innerText = "Ошибка";
            });
    });
</script>