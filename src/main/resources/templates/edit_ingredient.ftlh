<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Редактирование ингредиента</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<#include "header.ftlh"> <!-- Подключаем header -->

<h1>Редактирование ингредиента</h1>

<form action="/ingredients/edit/${ingredient.id}" method="post">
    <label for="name">Продукция:</label>
    <input type="text" id="finishedProduct" name="finishedProduct" value="${ingredient.finishedProduct.id}" required>
    <label for="name">Сырье:</label>
    <input type="text" id="rawMaterial" name="rawMaterial" value="${ingredient.rawMaterial.id}">
    <label for="name">Количество:</label>
    <input type="number" step=0.01 id="quantity" name="quantity" value="${ingredient.quantity}">

    <button type="submit">Сохранить</button>
</form>

<a href="/ingredients">Назад</a>
</body>
</html>
