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

<form action="/purchaseMaterial/create" method="post">
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

    <!-- Дата (автоматически заполняется сегодняшней датой) -->
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
</script>

</body>
</html>