<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Продажа продукта</title>
    <link rel="stylesheet" href="/styles.css">

</head>
<body>
<#include "header.ftlh"> <!-- Подключаем header -->
<h1>Продажа продукта</h1>
<#if productSales?has_content>
    <table>
        <tr>
            <th>Продукт</th>
            <th>Количество</th>
            <th>Сумма</th>
            <th>Дата</th>
            <th>Сотрудник</th>
        </tr>
        <#list productSales as productSales>
            <tr>
                <td>${productSales.finishedProduct.name}</td>
                <td>${productSales.quantity}</td>
                <td>${productSales.totalCost}</td>
                <td>${productSales.date}</td>
                <td>${productSales.employee.fullName}</td>
            </tr>
        </#list>
    </table>
<#else>
    <p>Нет проданных продуктов.</p>
</#if>

<form action="/productSale/create" method="get">
    <button type="submit">Создать</button>
</form>
</body>
</html>