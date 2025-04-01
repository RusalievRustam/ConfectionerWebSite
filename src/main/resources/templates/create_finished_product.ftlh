<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <title>Создание продукта</title>
    <link rel="stylesheet" href="/styles.css">

</head>
<body>

<#include "header.ftlh"> <!-- Подключаем header -->
<h1>Создание нового продукта</h1>

<form action="#" th:action="@{/create}" method="post">
    <label for="name">Название:</label>
    <input type="text" id="name" name="name" required>

    <label for="name">Количество:</label>
    <input type="text" id="quantity" name="quantity" required>

    <label for="name">Сумма:</label>
    <input type="text" id="totalCost" name="totalCost" required>

    <label for="name">Единица измерения:</label>
    <select id="measurementUnit" name="measurementUnit" required>
        <option value="">Выберите единицу измерения</option>
        <#list measurementUnits as unit>
            <option value="${unit.id}">${unit.name}</option>
        </#list>
    </select>
    <button type="submit">Добавить</button>
</form>

<a href="/products">Назад</a>
</body>
</html>
