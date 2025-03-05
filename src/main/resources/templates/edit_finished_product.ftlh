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
    <label for="name">Название:</label>
    <input type="text" id="name" name="name" value="${finishedProduct.name}" required>
    <input type="text" id="quantity" name="quantity" value="${finishedProduct.quantity}">
    <input type="number" step=0.01 id="totalCost" name="totalCost" value="${finishedProduct.totalCost}">
    <input type="text" id="measurementUnit" name="measurementUnit" value="${finishedProduct.measurementUnit.id}">

    <button type="submit">Сохранить</button>
</form>

<a href="/products">Назад</a>
</body>
</html>
