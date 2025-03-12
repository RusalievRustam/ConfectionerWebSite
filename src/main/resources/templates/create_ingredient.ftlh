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

<form action="/ingredients/create" method="post">
    <!-- Выбор сырья -->
    <label for="rawMaterial">Сырье:</label>
    <select id="rawMaterial" name="rawMaterial" required>
        <option value="">Выберите сырье</option>
        <#list rawMaterials as material>
            <option value="${material.id}">${material.name}</option>
        </#list>
    </select>

    <label for="finishedProduct">Продукт:</label>
    <select id="finishedProduct" name="finishedProduct" required>
        <option value="">Выберите продукт</option>
        <#list finishedProducts as finishedProduct>
            <option value="${finishedProduct.id}">${finishedProduct.name}</option>
        </#list>
    </select>

    <!-- Количество -->
    <label for="quantity">Количество:</label>
    <input type="number" id="quantity" name="quantity" required step="0.01" min="0">



    <button type="submit">Добавить</button>
</form>

<a href="/ingredients">Назад</a>

</body>
</html>