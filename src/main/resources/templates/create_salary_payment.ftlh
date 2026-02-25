<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Выплата зарплаты</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<#include "header.ftlh">

<h1>Выплата зарплаты</h1>

<#if errorMessage??>
    <div style="color:red;font-weight:bold;margin:10px 0;">${errorMessage}</div>
</#if>

<form action="/salaryPayment/create" method="post">
    <label>Сотрудник:</label>
    <select name="employeeId" required>
        <option value="">Выберите сотрудника</option>
        <#list employees as e>
            <option value="${e.id}">${e.fullName}</option>
        </#list>
    </select>

    <label>Сумма:</label>
    <input type="number" name="amount" step="0.01" min="0.01" required>

    <label>Комментарий:</label>
    <input type="text" name="note">

    <button type="submit">Выплатить</button>
</form>

<a href="/salaryPayments">Назад</a>
</body>
</html>