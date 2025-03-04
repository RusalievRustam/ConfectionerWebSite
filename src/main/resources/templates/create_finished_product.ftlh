<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <title>Создание продукта</title>
    <link rel="stylesheet" href="/resources/static/styles.css">

</head>
<body>
<h1>Создание нового продукта</h1>

<form action="#" th:action="@{/create}" method="post">
    <label for="name">Название:</label>
    <input type="text" id="name" name="name" required>
    <label for="name">Количество:</label>
    <input type="text" id="quantity" name="quantity" required>
    <label for="name">Стоимость:</label>
    <input type="text" id="totalCost" name="totalCost" required>
    <label for="name">Еденица измерения:</label>
    <input type="text" id="measurementUnit" name="measurementUnit" required>

    <button type="submit">Добавить</button>
</form>

<a href="/products">Назад</a>
</body>
</html>
