<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
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
        <form method="get" action="/wants_logout">
            <td colspan="2" style="text-align: center"><input type="submit" value="Logout"></td>
        </form>
        <form method="get" action="/info">
            <td colspan="2" style="text-align: center"><input type="submit" value="Info"></td>
        </form>
    </div>
    <h1>Detail Car</h1>
    <div>
        <ul>
            <li>${id}</li>
            <li>${owner.getName()}</li>
            <li>${brand_car}</li>
            <li>${model_car}</li>
            <li>${year_issue}</li>
            <li>${date_posting}</li>
            <li>${color}</li>
            <li>${mileage}</li>
            <li>${engine}</li>
            <li>${body_type}</li>
            <li>${gearBox_type}</li>
            <li>${driveUnit_type}</li>
            <li>${rudder_type}</li>
            <li>${condition_type}</li>
            <li>${image}</li>
            <li>${cost}</li>
        </ul>
    </div>

    <form method="post" action="/">
        <td colspan="2" style="text-align: center"><input type="submit" value="Info"></td>
    </form>

    <div>
        <ul>
            <#list comments as comment>
                <li>${comment}</li>
            </#list>
        </ul>
    </div>

</body>
</html>