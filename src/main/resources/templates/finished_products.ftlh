<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Список готовых продуктов</title>
    <link rel="stylesheet" href="/styles.css">
    <script>
        function deleteProduct(id) {
            if (window.confirm("Do you want to delete the product?")) {
                window.location.href = "/delete/" + id;
            } else {

            }
        }
    </script>
</head>
<body>
<#include "header.ftlh"> <!-- Подключаем header -->

<h1>Готовые продукты</h1>

<#if products?has_content>
    <table>
        <tr>
            <th>Продукт</th>
            <th>Сумма</th>
            <th>Количество</th>
            <th>Единица изм.</th>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
        </tr>
        <#list products as finishedProduct>
            <tr>
                <td>${finishedProduct.name}</td>
                <td>${finishedProduct.totalCost}</td>
                <td>${finishedProduct.quantity}</td>
                <td>${finishedProduct.measurementUnit.name}</td>
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
                    <form action="/product/sale/${finishedProduct.id}" method="get">
                        <button type="submit">Продать</button>
                    </form>
                </td>
                <td>
                    <button id="deleteButton" onclick="deleteProduct(${finishedProduct.id})">Удалить</button>
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