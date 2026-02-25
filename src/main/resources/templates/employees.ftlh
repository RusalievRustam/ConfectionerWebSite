<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Сотрудники</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<#include "header.ftlh">

<h1>Сотрудники</h1>

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

<table>
    <tr>
        <th>ФИО</th>
        <th>Роль</th>
        <th>Зарплата</th>
        <th></th>
    </tr>

    <#list employees as e>
        <tr>
            <form action="/employees/update" method="post">
                <td>
                    ${e.fullName}
                    <input type="hidden" name="id" value="${e.id}">
                </td>

                <td>
                    <select name="positionId" required>
                        <#list positions as p>
                            <option value="${p.id}"
                                    <#if e.position?? && e.position.id == p.id>selected</#if>>
                                ${p.name}
                            </option>
                        </#list>
                    </select>
                </td>

                <td>
                    <input type="text" name="salary"
                           value="${(e.salary!0)?c}">
                </td>

                <td>
                    <button type="submit">Сохранить</button>
                </td>
            </form>
        </tr>
    </#list>
</table>

<form action="/salary/pay-all" method="post">
    <button type="submit">Выдать зарплату всем</button>
</form>


<h2>Добавить сотрудника</h2>

<form action="/employees/create" method="post" style="display:flex; flex-direction:column; gap:10px; max-width:400px;">
    <label>
        ФИО:
        <input type="text" name="fullName" required>
    </label>

    <select name="positionId" required>
        <option value="">Выберите роль</option>
        <#list positions as p>
            <option value="${p.id}">${p.name}</option>
        </#list>
    </select>

    <label>
        Зарплата:
        <input type="text" name="salary" value="0" required>
    </label>

    <label>
        Адрес:
        <input type="text" name="address">
    </label>

    <label>
        Телефон:
        <input type="text" name="phone">
    </label>

    <label>
        Логин:
        <input type="text" name="username">
    </label>

    <label>
        Пароль:
        <input type="text" name="password">
    </label>

    <button type="submit">Добавить</button>
</form>

<hr>

<br>

</body>
</html>