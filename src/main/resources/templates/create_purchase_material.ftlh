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
    <label for="name">Сырье:</label>
    <input id="rawMaterial" name="rawMaterial" required>

    <label for="name">Количество:</label>
    <input type="number" id="quantity" name="quantity" required step="0.01" min="0">

    <label for="name">Дата:</label>
    <input type="date" id="date" name="date" required>

    <label for="name">Сумма:</label>
    <input type="number" id="totalCost" name="totalCost" required step="0.01" min="0">

    <label for="name">Сотрудник:</label>
    <input id="employee" name="employee" required>

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