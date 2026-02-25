<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Создать пользователя</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<#include "header.ftlh">

<h1>Создать пользователя (сотрудника)</h1>

<#if errorMessage?? && errorMessage?has_content>
    <div style="color:red;font-weight:bold;margin:10px 0;">
        ${errorMessage}
    </div>
</#if>

<#if successMessage?? && successMessage?has_content>
    <div style="color:green;font-weight:bold;margin:10px 0;">
        ${successMessage}
    </div>
</#if>

<form action="/users/create" method="post" style="display:grid; gap:10px; max-width:420px;">
    <label>
        Логин (username):
        <input type="text" name="username" required value="${username!""}">
    </label>

    <label>
        Пароль:
        <input type="password" name="password" required>
    </label>

    <label>
        ФИО:
        <input type="text" name="fullName" required value="${fullName!""}">
    </label>

    <label>
        Телефон:
        <input type="text" name="phone" value="${phone!""}">
    </label>

    <label>
        Адрес:
        <input type="text" name="address" value="${address!""}">
    </label>

    <label>
        Роль (должность):
        <select name="positionId" required>
            <option value="">-- Выберите роль --</option>
            <#list positions as p>
                <option value="${p.id}"
                        <#if (positionId??) && (positionId?number == p.id)>selected</#if>>
                    ${p.name}
                </option>
            </#list>
        </select>
    </label>

    <label>
        Зарплата:
        <input type="number" name="salary" step="0.01" min="0" required value="${salary!"0"}">
    </label>

    <button type="submit">Создать</button>
</form>

<br>
<a href="/employees">← Назад к сотрудникам</a>

</body>
</html>