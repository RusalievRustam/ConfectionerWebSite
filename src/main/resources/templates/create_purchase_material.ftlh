<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Покупка сырья</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>

<#include "header.ftlh"> <!-- Подключаем header -->

<h1>Закупка нового сырья</h1>

<form id="purchaseForm">
    <!-- Выбор сырья -->
    <label for="rawMaterial">Сырье:</label>
    <select id="rawMaterial" name="rawMaterial" required>
        <option value="">Выберите сырье</option>
        <#list rawMaterials as material>
        <option value="${material.id}">${material.name}</option>
        </#list>
    </select>

    <!-- Количество -->
    <label for="quantity">Количество:</label>
    <input type="number" id="quantity" name="quantity" required step="0.01" min="0">

    <!-- Дата -->
    <label for="date">Дата:</label>
    <input type="date" id="date" name="date" required>

    <!-- Сумма -->
    <label for="totalCost">Сумма:</label>
    <input type="number" id="totalCost" name="totalCost" required step="0.01" min="0">

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

<a href="/purchaseMaterials">Назад</a>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        let today = new Date().toISOString().split('T')[0]; // Получаем дату в формате YYYY-MM-DD
        document.getElementById("date").value = today;
    });

    document.getElementById("purchaseForm").addEventListener("submit", function(event) {
        event.preventDefault(); // Prevent normal form submission

        const rawMaterialPurchase = {
            rawMaterial: document.getElementById("rawMaterial").value,
            quantity: document.getElementById("quantity").value,
            date: document.getElementById("date").value,
            totalCost: document.getElementById("totalCost").value,
            employee: document.getElementById("employee").value
        };

        fetch("/purchaseMaterial/create", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(rawMaterialPurchase)
        })
        .then(response => {
            if (!response?.ok) {
                return response.json();
            } else {
                window.open.href ="/purchaseMaterials";
            }
        }).then(json => {
            alert(json.message);
        });
    });
</script>

</body>
</html>