package Models;

public class Message {

    private int id;
    private int id_sender;
    private int id_destUser;
    private String text;
    private String date_sending;

    public Message(int id, int id_sender, int id_destUser, String text, String date_sending) {
        this.id = id;
        this.id_sender = id_sender;
        this.id_destUser = id_destUser;
        this.text = text;
        this.date_sending = date_sending;
    }

    public int getId() {
        return id;
    }

    public int getId_sender() {
        return id_sender;
    }

    public int getId_destUser() {
        return id_destUser;
    }

    public String getText() {
        return text;
    }

    public String getDate_sending() {
        return date_sending;
    }
}
