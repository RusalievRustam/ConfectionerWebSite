<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Редактирование продукта</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<#include "header.ftlh"> <!-- Подключаем header -->

<h1>Редактирование продукта</h1>

<form action="/edit/${finishedProduct.id}" method="post">
    <label for="name">Название продукта:</label>
    <input type="text" id="name" name="name" value="${finishedProduct.name}" required>

    <label for="name">Количество:</label>
    <input type="number" step=0.01 id="quantity" name="quantity" value="${finishedProduct.quantity}">

    <label for="name">Сумма:</label>
    <input type="number" step=0.01 id="totalCost" name="totalCost" value="${finishedProduct.totalCost}">

    <label for="name">Еденица измерения:</label>
    <select id="measurementUnit" name="measurementUnit" required>
        <option value="">Выберите единицу измерения</option>
        <#list measurementUnits as unit>
            <option value="${unit.id}">${unit.name}</option>
        </#list>
    </select>

    <button type="submit">Сохранить</button>
</form>

<a href="/products">Назад</a>
</body>
</html>
