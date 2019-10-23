package Models;

public class Comment {

    private int id;
    private int id_sender;
    private int id_car;
    private String text;
    private String date_sending;

    public Comment(int id, int id_sender, int id_car, String text, String date_sending) {
        this.id = id;
        this.id_sender = id_sender;
        this.id_car = id_car;
        this.text = text;
        this.date_sending = date_sending;
    }

    public int getId() {
        return id;
    }

    public int getId_sender() {
        return id_sender;
    }

    public int getId_car() {
        return id_car;
    }

    public String getText() {
        return text;
    }

    public String getDate_sending() {
        return date_sending;
    }
}
