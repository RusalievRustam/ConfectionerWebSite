<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Редактирование сырья</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<#include "header.ftlh"> <!-- Подключаем header -->

<h1>Редактирование сырья</h1>

<form action="/rawMaterial/edit/${rawMaterial.id}" method="post">
    <label for="name">Название:</label>
    <input type="text" id="name" name="name" value="${rawMaterial.name}" required placeholder="Введите название">

    <label for="quantity">Количество:</label>
    <input type="number" step="0.01" id="quantity" name="quantity" value="${rawMaterial.quantity}" required
           placeholder="Введите количество">

    <label for="totalCost">Сумма:</label>
    <input type="number" step="0.01" id="totalCost" name="totalCost" value="${rawMaterial.totalCost}" required
           placeholder="Введите сумму">

    <label for="name">Единица измерения:</label>
    <select id="measurementUnit" name="measurementUnit" required>
        <option value="">Выберите единицу измерения</option>
        <#list measurementUnits as unit>
            <option value="${unit.id}">${unit.name}</option>
        </#list>
    </select>
    <button type="submit">Сохранить</button>
</form>

<a href="/rawMaterials">Назад</a>
</body>
</html>