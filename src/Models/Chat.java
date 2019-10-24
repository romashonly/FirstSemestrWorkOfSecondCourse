package Models;

public class Chat {
    private int id;
    private User user_first;
    private User user_second;
    private String date_creating;

    public Chat() {
        this.id = -1;
        this.user_first = new User();
        this.user_second = new User();
        this.date_creating = "not created";
    }

    public Chat(int id, User user_first, User user_second, String date_creating) {
        this.id = id;
        this.user_first = user_first;
        this.user_second = user_second;
        this.date_creating = date_creating;
    }

    public int getId() {
        return id;
    }

    public User getUser_first() {
        return user_first;
    }

    public User getUser_second() {
        return user_second;
    }

    public String getDate_creating() {
        return date_creating;
    }
}
