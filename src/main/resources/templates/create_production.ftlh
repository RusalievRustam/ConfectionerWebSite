<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Создание производства</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<#include "header.ftlh">

<h1>Создание производства продукта</h1>

<form action="/production/create" method="post">
    <#if errorMessage??>
        <div style="color: #4f4f4f; font-weight: bold; margin-bottom: 10px;">
            ${errorMessage}
        </div>
    </#if>

    <label for="finishedProduct">Готовый продукт:</label>
    <select id="finishedProduct" name="finishedProduct.id" required>
        <option value="">-- Выберите продукт --</option>
        <#list finishedProducts as product>
            <option value="${product.id}"
                    <#if production.finishedProduct?? && production.finishedProduct.id == product.id>selected</#if>>
                ${product.name}
            </option>
        </#list>
    </select>
    <br><br>

    <label for="quantity">Количество:</label>
    <input type="number" id="quantity" name="quantity" required min="1" step="0.01"
           value="${production.quantity!}">
    <br><br>

    <label for="employee">Сотрудник:</label>
    <select id="employee" name="employee.id" required>
        <option value="">-- Выберите сотрудника --</option>
        <#list employees as emp>
            <option value="${emp.id}"
                    <#if production.employee?? && production.employee.id == emp.id>selected</#if>>
                ${emp.fullName}
            </option>
        </#list>
    </select>
    <br><br>

    <button type="submit">Сохранить</button>
</form>
<br>
<a href="/productions">← Назад к списку производств</a>

</body>
</html>