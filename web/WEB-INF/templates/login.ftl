<form method="Post" action="/login">
    <table>
        <tr>
            <td><label for="loginField">Login</label></td>
            <td><input id="loginField" type="text" name="login"></td>
        </tr>
        <tr>
            <td><label for="passwordField">Password</label></td>
            <td><input id="passwordField" type="password" name="password"></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center"><input type="submit" value="Go"></td>
        </tr>
        <tr>
            <td colspan="2" class="text_center" >
                <label>
                    <input type="checkbox" name="remember_me" checked="checked" /> Remember me
                </label>
            </td>
        </tr>
    </table>
</form>
<form method="get" action="/registration">
    <td colspan="2" style="text-align: center"><input type="submit" value="Registration"></td>
</form>