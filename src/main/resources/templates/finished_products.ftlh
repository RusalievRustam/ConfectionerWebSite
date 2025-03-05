<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Список готовых продуктов</title>
    <link rel="stylesheet" href="/styles.css">

</head>
<body>
<#include "header.ftlh"> <!-- Подключаем header -->

<h1>Готовые продукты</h1>

<#if products?has_content>
    <table>
        <tr>
            <th>Продукт</th>
            <th>Цена</th>
            <th></th>
            <th></th>
            <th></th>
        </tr>
        <#list products as finishedProduct>
            <tr>
                <td>${finishedProduct.name}</td>
                <td>${finishedProduct.totalCost}</td>
                <td>
                    <form action="/ingredients/${finishedProduct.id}" method="get">
                        <button type="submit">Ингредиенты</button>
                    </form>
                </td>
                <td>
                    <form action="/edit/${finishedProduct.id}" method="get">
                        <button type="submit">Редактировать</button>
                    </form>
                </td>
                <td>
                    <form action="/delete/${finishedProduct.id}" method="/delete">
                        <button type="submit">Удалить</button>
                    </form>
                </td>
            </tr>
        </#list>
    </table>
<#else>
    <p>Нет готовых продуктов.</p>
</#if>

<form action="/create" method="create">
    <button type="submit">Создать</button>
</form></body>
</html>