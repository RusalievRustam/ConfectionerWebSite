<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Взять кредит</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<#include "header.ftlh">

<h1>Взять кредит</h1>

<#if errorMessage??>
    <div style="color:red;font-weight:bold;margin:10px 0;">${errorMessage}</div>
</#if>

<form action="/loan/create" method="post">
    <label>Банк:</label>
    <input type="text" name="bankName">

    <label>Сумма:</label>
    <input type="number" name="principal" step="0.01" min="0.01" required>

    <label>Процент годовой:</label>
    <input type="number" name="interestRate" step="0.01" min="0" required>

    <label>Срок (месяцев):</label>
    <input type="number" name="termMonths" min="1" required>

    <button type="submit">Оформить</button>
</form>

<a href="/loans">Назад</a>
</body>
</html>