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
    <input type="text" id="rawMaterial" name="rawMaterial" value="${rawMaterial.name}" required>
    <label for="name">Количество:</label>
    <input type="number" step=0.01 id="quantity" name="quantity" value="${rawMaterial.quantity}">
    <label for="name">Сумма:</label>
    <input type="number" step=0.01 id="totalCost" name="totalCost" value="${rawMaterial.totalCost}">
    <input type="text" id="measurementUnit" name="measurementUnit" value="${rawMaterial.measurementUnit.name}">

    <button type="submit">Сохранить</button>
</form>

<a href="/rawMaterials">Назад</a>
</body>
</html>
