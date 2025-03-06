<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Список ингредиентов</title>
    <link rel="stylesheet" href="/styles.css">

</head>
<body>
<#include "header.ftlh"> <!-- Подключаем header -->

<h1>Ингредиенты</h1>
<#if ingredients?has_content>
    <ul>
        <#list ingredients as ingredients>
            <li>
                ID: ${ingredients.id}, Название: ${ingredients.finishedProduct.name},
                Сырье: ${ingredients.rawMaterial.name}, Количество: ${ingredients.quantity}
                <a href="ingredients/edit/${ingredients.id}">Редактировать</a>
                <form action="ingredients/delete/${ingredients.id}" method="/ingredients/post">
                    <button type="submit">Удалить</button>
                </form>
            </li>
        </#list>
    </ul>
<#else>
    <p>Нет ингредиентов.</p>
</#if>

<a href="/ingredients/create">Создать новый ингредиент</a>
</body>
</html>