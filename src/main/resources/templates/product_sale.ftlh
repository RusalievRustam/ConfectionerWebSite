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

<form id="saleForm">
    <!-- Выбор продукта -->
    <h3>${finishedProduct.name} (Осталось: ${finishedProduct.quantity})</h3>

    <input type="hidden" id="productPrice" value="${cost?c}">

    <input type="hidden" id="finishedProduct" name="finishedProduct" value="${finishedProduct.id}">

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

<a href="/products">Назад</a>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        let today = new Date().toISOString().split('T')[0]; // Получаем дату в формате YYYY-MM-DD
        document.getElementById("date").value = today;
        const quantityInput = document.getElementById("quantity");
        const totalCostInput = document.getElementById("totalCost");

        function updateTotalCost() {
            const pricePerUnit = parseFloat(document.getElementById("productPrice").value.replace(/,/g, '')) || 0;
            const quantity = parseFloat(quantityInput.value) || 0;
            totalCostInput.value = (pricePerUnit * quantity).toFixed(2);

        }

        quantityInput.addEventListener("input", updateTotalCost);
    });

    document.getElementById("saleForm").addEventListener("submit", function (event) {
        event.preventDefault(); // Prevent normal form submission

        const productSale = {
            finishedProduct: {id: document.getElementById("finishedProduct").value},
            quantity: parseFloat(document.getElementById("quantity").value),
            date: document.getElementById("date").value,
            totalCost: parseFloat(document.getElementById("totalCost").value),
            employee: {id: document.getElementById("employee").value}
        };

        fetch("/product/sale", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(productSale)
        })
            .then(response => {
                if (!response?.ok) {
                    return response.json();
                } else {
                    window.location.href = "/products";
                }
            }).then(json => {
            alert(json.message);
        });
    });

</script>

</body>
</html>