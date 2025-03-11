<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Список сырья</title>
    <link rel="stylesheet" href="/styles.css">

</head>
<body>
<#include "header.ftlh"> <!-- Подключаем header -->
<h1>Сырье</h1>
<#if rawMaterials?has_content>
    <table>
        <tr>
            <th>Название</th>
            <th>Количество</th>
            <th>Сумма</th>
            <th>Еденица измерения</th>
            <th></th>
            <th></th>
        </tr>

        <#list rawMaterials as rawMaterial>
            <tr>
                <td>${rawMaterial.name}</td>
                <td>${rawMaterial.quantity}</td>
                <td>${rawMaterial.totalCost}</td>
                <td>${rawMaterial.measurementUnit.name}</td>
                <td>
                    <form action="/rawMaterial/edit/${rawMaterial.id}" method="get">
                        <button type="submit">Редактировать</button>
                    </form>
                </td>
                <td>
                    <form action="/rawMaterial/delete/${rawMaterial.id}" method="delete">
                        <button type="submit">Удалить</button>
                    </form>
                </td>
            </tr>
        </#list>
    </table>
<#else>
    <p>Нет сырья.</p>
</#if>

<form action="/rawMaterial/create" method="get">
    <button type="submit">Создать</button>
</form>
</body>
</html>