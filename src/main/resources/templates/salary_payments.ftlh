<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Выплаты зарплат</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<#include "header.ftlh">

<h1>Выплаты зарплат</h1>

<#if payments?has_content>
    <table>
        <tr>
            <th>Дата</th>
            <th>Сотрудник</th>
            <th>Сумма</th>
            <th>Комментарий</th>
        </tr>
        <#list payments as p>
            <tr>
                <td>${p.payDate}</td>
                <td>${p.employee.fullName}</td>
                <td>${p.amount}</td>
                <td>${p.note!""}</td>
            </tr>
        </#list>
    </table>
<#else>
    <p>Выплат пока нет.</p>
</#if>

<a href="/employees">Назад</a>

</body>
</html>