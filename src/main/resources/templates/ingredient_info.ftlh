<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Детали ингредиента</title>
    <link rel="stylesheet" href="/static/styles.css">
</head>
<body>
<h1>Информация об ингредиенте</h1>

<p><strong>ID:</strong> ${ingredient.id}</p>
<p><strong>Название продукта:</strong> ${ingredient.finishedProduct.name}</p>
<p><strong>Название сырья:</strong> ${ingredient.rawMaterial.name}</p>
<p><strong>Количество:</strong> ${ingredient.quantity}</p>

<a href="/ingredients/{id}">Назад к списку</a>
</body>
</html>