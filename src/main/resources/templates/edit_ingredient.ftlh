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
    <label for="finishedProduct">Продукция:</label>
    <select id="finishedProduct" name="finishedProduct" required>
        <#list finishedProducts as product>
            <option value="${product.id}" <#if product.id == ingredient.finishedProduct.id>selected</#if>>${product.name}</option>
        </#list>
    </select>

    <label for="rawMaterial">Сырье:</label>
    <select id="rawMaterial" name="rawMaterial">
        <#list rawMaterials as raw>
            <option value="${raw.id}" <#if raw.id == ingredient.rawMaterial.id>selected</#if>>${raw.name}</option>
        </#list>
    </select>

    <label for="quantity">Количество:</label>
    <input type="number" step="0.01" id="quantity" name="quantity" value="${ingredient.quantity}">

    <button type="submit">Сохранить</button>
</form>

<a href="/ingredients/${ingredient.finishedProduct.id}">Назад</a>
</body>
</html>