<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Список закупки сырья</title>
    <link rel="stylesheet" href="/styles.css">

</head>
<body>
<#include "header.ftlh"> <!-- Подключаем header -->
<h1>Закупка сырья</h1>
<#if purchaseMaterials?has_content>
    <table>
        <tr>
            <th>Сырье</th>
            <th>Количество</th>
            <th>Сумма</th>
            <th>Дата</th>
            <th>Сотрудник</th>
        </tr>
        <#list purchaseMaterials as purchaseMaterial>
            <tr>
                <td>${purchaseMaterial.rawMaterial.name}</td>
                <td>${purchaseMaterial.quantity}</td>
                <td>${purchaseMaterial.totalCost}</td>
                <td>${purchaseMaterial.date}</td>
                <td>${purchaseMaterial.employee.fullName}</td>
            </tr>
        </#list>
    </table>
<#else>
    <p>Нет закупок сырья.</p>
</#if>

<form action="/purchaseMaterial/create" method="get">
    <button type="submit">Создать</button>
</form>
</body>
</html>