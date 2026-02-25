<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Вход</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>

<h2>Вход в систему</h2>

<#if (param.error)??>
    <div style="color:red;">Неверный логин или пароль</div>
</#if>

<form method="post" action="/login" style="display:flex; flex-direction:column; gap:10px; max-width:300px;">
    <input type="text" name="username" placeholder="Логин" required>
    <input type="password" name="password" placeholder="Пароль" required>
    <button type="submit">Войти</button>
</form>

<hr style="margin:20px 0;">

<!-- Новая кнопка -->
<div>
    <a href="/users/create">
        <button type="button">Создать нового пользователя</button>
    </a>
</div>

</body>
</html>