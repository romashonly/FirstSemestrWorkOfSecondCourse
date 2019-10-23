package Models;

public class User {

    private int    id;
    private String login;
    private String password;
    private String phone_number;
    private String name;
    private String serName;
    private String date_birth;
    private String date_registration;
    private String avatar;
    private String city;

    public User() {
        this.id = 404;
        this.name = "404";
        this.login = "404";
        this.password = "404";
    }


    public User(
            int id,
            String login,
            String password,
            String phone_number,
            String name,
            String serName,
            String date_birth,
            String date_registration,
            String avatar,
            String city
    ) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.phone_number = phone_number;
        this.name = name;
        this.serName = serName;
        this.date_birth = date_birth;
        this.date_registration = date_registration;
        this.avatar = avatar;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSerName() {
        return serName;
    }

    public String getDate_birth() {
        return date_birth;
    }

    public String getDate_registration() {
        return date_registration;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getCity() {
        return city;
    }

    public String getPhone_number() {
        return phone_number;
    }
}
