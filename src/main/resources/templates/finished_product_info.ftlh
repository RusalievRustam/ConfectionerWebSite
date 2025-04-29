<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Детали продукта</title>
    <link rel="stylesheet" href="/styles.css">
    <script>
        function deleteIngredient(id) {
            if (window.confirm("Do you want to delete the ingredient?")) {
                window.location.href = "/ingredients/delete/" + id;
            } else {
            }
        }
    </script>
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
                <a href="/ingredients/edit/${ingredient.id}">
                    Update ingredient
                </a>
                <button id="deleteButton" onclick="deleteIngredient(${ingredient.id})">Удалить</button>
                </a>
            </li>
        </#list>
    </ul>
<#else>
    <p>Нет ингредиентов.</p>
</#if>
<form action="/ingredients/save/${productId}" method="get">
    <button type="submit">Добавить ингредиент</button>
</form>

<a href="/products">Назад к списку</a>
</body>
</html>