package Models;

public class Message {

    private int id;
    private User sender;
    private User destUser;
    private Chat chat;
    private String text;
    private String date_sending;

    public Message(int id, User sender, User destUser, Chat chat, String text, String date_sending) {
        this.id = id;
        this.sender = sender;
        this.destUser = destUser;
        this.chat = chat;
        this.text = text;
        this.date_sending = date_sending;
    }

    public int getId() {
        return id;
    }

    public User getSender() {
        return sender;
    }

    public User getDestUser() {
        return destUser;
    }

    public String getText() {
        return text;
    }

    public String getDate_sending() {
        return date_sending;
    }

    public Chat getChat() {
        return chat;
    }
}
