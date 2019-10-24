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
        <form method="get" action="/registration">
            <td colspan="2" style="text-align: center"><input type="submit" value="Sell"></td>
        </form>
        <form method="get" action="/login">
            <td colspan="2" style="text-align: center"><input type="submit" value="Login"></td>
        </form>
        <form method="get" action="/info">
            <td colspan="2" style="text-align: center"><input type="submit" value="Info"></td>
        </form>
    </div>
    <div>
        <ul>
            <#list cars as car>
                <li>${car}</li>
            </#list>
        </ul>
    </div>
</body>
</html>