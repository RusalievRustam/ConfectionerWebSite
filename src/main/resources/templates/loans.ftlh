<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Кредиты</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<#include "header.ftlh">

<h1>Кредиты</h1>

<#if errorMessage??>
    <div style="color:red;font-weight:bold;margin:10px 0;">${errorMessage}</div>
</#if>

<#if loans?has_content>
    <table>
        <tr>
            <th>Банк</th>
            <th>Сумма</th>
            <th>%</th>
            <th>Остаток</th>
            <th>Статус</th>
            <th>Погасить</th>
        </tr>
        <#list loans as l>
            <tr>
                <td>${l.bankName!""}</td>
                <td>${l.principal}</td>
                <td>${l.interestRate}</td>
                <td>${l.outstanding}</td>
                <td>${l.status}</td>
                <td>
                    <#if l.status == "ACTIVE">
                        <form action="/loan/pay" method="post">
                            <input type="hidden" name="loanId" value="${l.id}">
                            <input type="number" name="amount" step="0.01" min="0.01" required>
                            <button type="submit">Платёж</button>
                        </form>
                    </#if>
                </td>
            </tr>
        </#list>
    </table>
<#else>
    <p>Кредитов нет.</p>
</#if>

<form action="/loan/create" method="get">
    <button type="submit">Взять кредит</button>
</form>

</body>
</html>