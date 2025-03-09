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
            <th>Количество</th>
            <th>Дата</th>
            <th>Сумма</th>
            <th>Сотрудник</th>
            <th>id сырья</th>
            <th></th>
            <th></th>
        </tr>
        <#list purchaseMaterials as purchaseMaterial>
            <tr>
                <td>${purchaseMaterial.quantity}</td>
                <td>${purchaseMaterial.date}</td>
                <td>${purchaseMaterial.totalCost}</td>
                <td>${purchaseMaterial.employee}</td>
                <td>${purchaseMaterial.rawMaterial}</td>
<#--                <td>-->
<#--                    <form action="/rawMaterial/edit/${purchaseMaterial.id}" method="get">-->
<#--                        <button type="submit">Редактировать</button>-->
<#--                    </form>-->
<#--                </td>-->
<#--                <td>-->
<#--                    <form action="/rawMaterial/delete/${rawMaterial.id}" method="delete">-->
<#--                        <button type="submit">Удалить</button>-->
<#--                    </form>-->
<#--                </td>-->
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