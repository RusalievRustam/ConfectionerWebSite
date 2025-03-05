<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Создание сырья</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>

<#include "header.ftlh"> <!-- Подключаем header -->

<h1>Создание нового сырья</h1>

<form action="rawMaterial/create" method="post">
    <label for="name">Название сырья:</label>
    <input type="text" id="name" name="name" required>

    <label for="quantity">Количество:</label>
    <input type="text" id="quantity" name="quantity" required>

    <label for="totalCost">Стоимость:</label>
    <input type="text" id="totalCost" name="totalCost" required>

    <label for="measurementUnit">Единица измерения:</label>
    <select id="measurementUnit" name="measurementUnit" required>
        <option value="">Выберите единицу измерения</option>
        <#list measurementUnits as unit>
            <option value="${unit.id}">${unit.name}</option>
        </#list>
    </select>

    <button type="submit">Добавить</button>
</form>

<a href="/rawMaterial/create">Назад</a>

</body>
</html>