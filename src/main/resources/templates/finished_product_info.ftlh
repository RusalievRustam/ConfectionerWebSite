<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Детали продукта</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<#include "header.ftlh"> <!-- Подключаем header -->

<h1>Информация о продукте</h1>

<#if ingredients?has_content>
<h2>${ingredients[0].finishedProduct.name}</h2>
<ul>
    <#list ingredients as ingredient>
    <li>
        ${ingredient.rawMaterial.name} ${ingredient.quantity} ${ingredient.rawMaterial.measurementUnit.name}
    </li>
    </#list>
</ul>
<#else>
<p>Нет ингредиентов.</p>
</#if>

<a href="/products">Назад к списку</a>
</body>
</html>