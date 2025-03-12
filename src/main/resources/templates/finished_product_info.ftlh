<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Детали продукта</title>
    <link rel="stylesheet" href="/styles.css">
    <script>
        function removeIngredientField(button, id) {
            button.parentElement.remove();
            fetch('/ingredients/delete/id')
        }
    </script>
</head>
<body>
<#include "header.ftlh"> <!-- Подключаем header -->

<h1>Информация о продукте</h1>

<#if ingredients?has_content>
    <h2>${ingredients[0].finishedProduct.name}</h2>
    <#list ingredients as ingredient>
        <div>
            ${ingredient.rawMaterial.name} ${ingredient.quantity} ${ingredient.rawMaterial.measurementUnit.name}
            <a href="/ingredients/edit/${ingredient.id}">
                Update ingredient
            </a>
            <a href="/ingredients/delete/${ingredient.id}">
                Delete
            </a>
        </div>
    </#list>
<#else>
    <p>Нет ингредиентов.</p>
</#if>
<form action="/ingredients/save/productId=${productId}" method="get">
    <button type="submit">Добавить ингредиент</button>
</form>

<a href="/products">Назад к списку</a>
</body>
</html>