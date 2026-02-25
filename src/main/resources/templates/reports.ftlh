<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Отчёты</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<#include "header.ftlh">

<h1>Отчёт предприятия</h1>

<form method="get" action="/reports">
    <label>С:</label>
    <input type="date" name="from" value="${from}">
    <label>По:</label>
    <input type="date" name="to" value="${to}">
    <button type="submit">Показать</button>
</form>

<br>

<table>
    <tr>
        <th>Показатель</th>
        <th>Сумма</th>
    </tr>
    <tr>
        <td>Продажи</td>
        <td>${report.sales}</td>
    </tr>
    <tr>
        <td>Закупка сырья</td>
        <td>${report.purchases}</td>
    </tr>
    <tr>
        <td>Зарплаты</td>
        <td>${report.salaries}</td>
    </tr>
    <tr>
        <td>Кредиты получено</td>
        <td>${report.loansTaken}</td>
    </tr>
    <tr>
        <td>Кредиты погашено</td>
        <td>${report.loanPayments}</td>
    </tr>
    <tr>
        <td><b>Прибыль</b></td>
        <td><b>${report.profit}</b></td>
    </tr>
</table>

<br>

<a href="/reports/export?from=${from}&to=${to}&format=xlsx">
    <button type="button">Скачать XLSX</button>
</a>

<a href="/reports/export?from=${from}&to=${to}&format=pdf">
    <button type="button">Скачать PDF</button>
</a>

<a href="/reports/export?from=${from}&to=${to}&format=docx">
    <button type="button">Скачать Word</button>
</a>

</body>
</html>