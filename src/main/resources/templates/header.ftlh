<header>
<div class="logo">
 <img src="/images/icon8-cake.png" alt="Sweetsland">
</div>
    <nav>
        <ul>
            <li><a href="/products"> Продукты</a></li>
            <li><a href="/ingredients"> Ингредиенты</a></li>
            <li><a href="/rawMaterials"> Сырье</a></li>
            <li><a href="/purchaseMaterials"> Закупка сырья</a></li>
        </ul>
        <div><span id="budget">Loading...</span></div>
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