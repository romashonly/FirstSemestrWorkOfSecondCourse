<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Catalog</title>
</head>
<body>
<div>
    <form method="get" action="/catalog">
        <td colspan="2" style="text-align: center"><input type="submit" value="Catalog"></td>
    </form>
    <form method="get" action="/my_posts">
        <td colspan="2" style="text-align: center"><input type="submit" value="myPosts"></td>
    </form>
    <form method="get" action="/favorites">
        <td colspan="2" style="text-align: center"><input type="submit" value="Favorites"></td>
    </form>
    <form method="get" action="/chats">
        <td colspan="2" style="text-align: center"><input type="submit" value="Chats"></td>
    </form>
    <form method="get" action="/post_car">
        <td colspan="2" style="text-align: center"><input type="submit" value="Sell"></td>
    </form>
    <form method="get" action="/profile">
        <td colspan="2" style="text-align: center"><input type="submit" value="Profile"></td>
    </form>
    <form method="get" action="/logout">
        <td colspan="2" style="text-align: center"><input type="submit" value="Logout"></td>
    </form>
    <form method="get" action="/info">
        <td colspan="2" style="text-align: center"><input type="submit" value="Info"></td>
    </form>
</div>
<div>
    <ul>
        <li>${id}</li>
        <li>${login}</li>
        <li>${phone_number}</li>
        <li>${name}</li>
        <li>${serName}</li>
        <li>${date_birth}</li>
        <li>${date_registration}</li>
        <li>${avatar}</li>
        <li>${city}</li>
    </ul>
</div>
</body>
</html>