<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <title>Создание ингредиента</title>
    <link rel="stylesheet" href="/styles.css">

</head>
<body>

<#include "header.ftlh"> <!-- Подключаем header -->
<h1>Создание нового ингредиента</h1>

<form action="#" th:action="@{/create}" method="post">
    <label for="name">Название готового продукта:</label>
    <input type="text" id="finishedProduct" name="finishedProduct" required>
    <label for="name">Используемое сырье:</label>
    <input type="text" id="rawMaterial" name="rawMaterial" required>
    <label for="name">Количество:</label>
    <input type="text" id="quantity" name="quantity" required>

    <button type="submit">Добавить</button>
</form>

<a href="/ingredients">Назад</a>
</body>
</html>
