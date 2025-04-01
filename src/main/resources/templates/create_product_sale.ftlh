<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Продажа продукта</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>

<#include "header.ftlh"> <!-- Подключаем header -->

<h1>Продажа продукта</h1>

<form id="purchaseForm" action="/productSale/create" method="post">
    <!-- Выбор продукта -->
    <label for="finishedProduct">Продукт:</label>
    <select id="finishedProduct" name="finishedProduct" required>
        <option value="">Выберите продукт</option>
        <#list finishedProducts as product>
            <option value="${product.id}" data-price="${product.totalCost / product.quantity}">
                ${product.name}
            </option>
        </#list>
    </select>

    <!-- Количество -->
    <label for="quantity">Количество:</label>
    <input type="number" id="quantity" name="quantity" required step="0.01" min="0">

    <!-- Сумма (автоматически вычисляется) -->
    <label for="totalCost">Цена:</label>
    <input type="number" id="totalCost" name="totalCost" required step="0.01" min="0" readonly>

    <!-- Дата -->
    <label for="date">Дата:</label>
    <input type="date" id="date" name="date" required>

    <!-- Выбор сотрудника -->
    <label for="employee">Сотрудник:</label>
    <select id="employee" name="employee" required>
        <option value="">Выберите сотрудника</option>
        <#list employees as employee>
            <option value="${employee.id}">${employee.fullName}</option>
        </#list>
    </select>

    <button type="submit">Добавить</button>
</form>

<a href="/productSales">Назад</a>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        let today = new Date().toISOString().split('T')[0]; // Получаем дату в формате YYYY-MM-DD
        document.getElementById("date").value = today;
        const productSelect = document.getElementById("finishedProduct");
        const quantityInput = document.getElementById("quantity");
        const totalCostInput = document.getElementById("totalCost");

        function updateTotalCost() {
            const selectedOption = productSelect.options[productSelect.selectedIndex];
            const pricePerUnit = parseFloat(selectedOption.getAttribute("data-price")) || 0;
            const quantity = parseFloat(quantityInput.value) || 0;
            totalCostInput.value = (pricePerUnit * quantity).toFixed(2);
        }

        productSelect.addEventListener("change", updateTotalCost);
        quantityInput.addEventListener("input", updateTotalCost);
    });
</script>

</body>
</html>