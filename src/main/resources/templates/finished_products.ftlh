<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>Список готовых продуктов</title>
</head>
<body>
<h1>Готовые продукты</h1>

<#if products?has_content>
  <ul>
    <#list products as finishedProduct>
      <li>
        ID: ${finishedProduct.id}, Название: ${finishedProduct.name}
        <a href="/edit/${finishedProduct.id}">Редактировать</a>
        <a href="/get/${finishedProduct.id}">Инфо</a>
        <form action="/delete/${finishedProduct.id}" method="post">
          <button type="submit">Удалить</button>
        </form>
      </li>
    </#list>
  </ul>
<#else>
  <p>Нет готовых продуктов.</p>
</#if>

<a href="/create">Создать новый продукт</a>
</body>
</html>