<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Список производства</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<#include "header.ftlh"> <!-- Подключаем header -->

<h1>Производство продукта</h1>

<#if productions?has_content>
    <table>
        <tr>
            <th>Продукт</th>
            <th>Количество</th>
            <th>Дата</th>
            <th>Сотрудник</th>
        </tr>
        <#list productions as production>
            <tr>
                <td>${production.finishedProduct.name}</td>
                <td>${production.quantity}</td>
                <td>${production.date}</td>
                <td>${production.employee.fullName}</td>
            </tr>
        </#list>
    </table>
<#else>
    <p>Нет производства.</p>
</#if>

<form action="/production/create" method="get">
    <button type="submit">Создать</button>
</form>
</body>
</html>