<!DOCTYPE html>
<html lang="ru">
<link rel="stylesheet" href="/styles.css">

<body>
<header>
    <nav id="menu">
        <ul>
            <li><a href="/products">Продукты</a></li>
            <li><a href="/rawMaterials">Сырье</a></li>
            <li><a href="/purchaseMaterials">Закупка сырья</a></li>
            <li><a href="/productSales">Продажа продукта</a></li>
        </ul>
        <div id="totalBudget"><strong>Бюджет: </strong><span id="budget">Loading...</span><strong> Сом</strong></div>
    </nav>
</header>
<script>
    function fetchTotalBudget() {
        fetch('/budget') // Replace with your actual API endpoint
            .then(response => response.json())
            .then(data => {
                document.getElementById("budget").innerText = data.budget;
            })
            .catch(error => {
                console.error("Error fetching budget:", error);
                document.getElementById("budget").innerText = "Error";
            });
    }

    // Fetch budget data when the page loads
    window.onload = fetchTotalBudget;
</script>
</body>
</html>
