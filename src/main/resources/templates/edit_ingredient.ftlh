<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Редактирование ингредиента</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<#include "header.ftlh"> <!-- Подключаем header -->

<h1>Редактирование ингредиента</h1>

<form action="/ingredients/edit/${ingredient.id}" method="post">
    <label for="finishedProduct">Продукция:</label>
    <select id="finishedProduct" name="finishedProduct" required>
        <#list finishedProducts as product>
            <option value="${product.id}"
                    <#if product.id == ingredient.finishedProduct.id>selected</#if>>${product.name}</option>
        </#list>
    </select>

    <label for="rawMaterial">Сырье:</label>
    <select id="rawMaterial" name="rawMaterial">
        <#list rawMaterials as raw>
            <option value="${raw.id}" <#if raw.id == ingredient.rawMaterial.id>selected</#if>>${raw.name}</option>
        </#list>
    </select>

    <label for="quantity">Количество:</label>
    <input type="number" step="0.01" id="quantity" name="quantity" value="${ingredient.quantity}" required
           placeholder="Введите количество">

    <button type="submit">Сохранить</button>
</form>

<a href="/ingredients/${ingredient.finishedProduct.id}">Назад</a>

<script>
    document.querySelector("form").addEventListener("submit", function (event) {
        event.preventDefault();

        const formData = new FormData(this);

        fetch(this.action, { // this.action = "/ingredients/edit/${ingredient.id}"
            method: "POST",
            body: new URLSearchParams(formData) // Преобразуем в application/x-www-form-urlencoded
        })
            .then(response => {
                if (!response?.ok) {
                    return response.json();
                } else {
                    window.location.href = "/ingredients/${ingredient.finishedProduct.id}";
                }
            }).then(json => {
            alert(json.message);
        });
    });
</script>
</body>
</html>