package Models;

public class Favorite {

    private int id;
    private int id_car;
    private int id_user;
    private String date_adding;

    public Favorite(int id, int id_car, int id_user, String date_adding) {
        this.id = id;
        this.id_car = id_car;
        this.id_user = id_user;
        this.date_adding = date_adding;
    }

    public int getId() {
        return id;
    }

    public int getId_car() {
        return id_car;
    }

    public int getId_user() {
        return id_user;
    }

    public String getDate_adding() {
        return date_adding;
    }
}
