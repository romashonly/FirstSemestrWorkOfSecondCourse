package Models;

public class Comment {

    private int id;
    private User sender;
    private Car car;
    private String text;
    private String date_sending;

    public Comment(int id, User sender, Car car, String text, String date_sending) {
        this.id = id;
        this.sender = sender;
        this.car = car;
        this.text = text;
        this.date_sending = date_sending;
    }

    public int getId() {
        return id;
    }

    public User getSender() {
        return sender;
    }

    public Car getCar() {
        return car;
    }

    public String getText() {
        return text;
    }

    public String getDate_sending() {
        return date_sending;
    }
}
