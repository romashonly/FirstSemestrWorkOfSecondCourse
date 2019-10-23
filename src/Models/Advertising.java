package Models;

public class Advertising {

    private int id;
    private String image_advertising;
    private String date_start;
    private String date_end;
    private String comment_for_image;

    public Advertising(int id, String image_advertising, String date_start, String date_end, String comment_for_image) {
        this.id = id;
        this.image_advertising = image_advertising;
        this.date_start = date_start;
        this.date_end = date_end;
        this.comment_for_image = comment_for_image;
    }

    public int getId() {
        return id;
    }

    public String getImage_advertising() {
        return image_advertising;
    }

    public String getDate_start() {
        return date_start;
    }

    public String getDate_end() {
        return date_end;
    }

    public String getComment_for_image() {
        return comment_for_image;
    }
}
