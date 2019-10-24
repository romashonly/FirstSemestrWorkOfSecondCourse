package Models;

public class Car {
    private int    id;
    private User   owner;
    private String brand_car;
    private String model_car;
    private String year_issue;
    private String date_posting;
    private String color;
    private String mileage;
    private String engine;
    private String body_type;
    private String gearBox_type;
    private String driveUnit_type;
    private String rudder_type;
    private String condition_type;
    private String image;
    private int    cost;

    public Car() {
        this.id = 404;
        this.owner = new User();
        this.brand_car = "404";
        this.model_car = "404";
        this.year_issue = "404";
        this.date_posting = "404";
        this.color = "404";
        this.mileage = "404";
        this.engine = "404";
        this.body_type = "404";
        this.gearBox_type = "404";
        this.driveUnit_type = "404";
        this.rudder_type = "404";
        this.condition_type = "404";
        this.image = "404";
        this.cost = 404;
    }

    public Car(
            int id,
            User owner,
            String brand_car,
            String model_car,
            String year_issue,
            String date_posting,
            String color,
            String mileage,
            String engine,
            String body_type,
            String gearBox_type,
            String driveUnit_type,
            String rudder_type,
            String condition_type,
            String image,
            int cost
    ) {
        this.id = id;
        this.owner = owner;
        this.brand_car = brand_car;
        this.model_car = model_car;
        this.year_issue = year_issue;
        this.date_posting = date_posting;
        this.color = color;
        this.mileage = mileage;
        this.engine = engine;
        this.body_type = body_type;
        this.gearBox_type = gearBox_type;
        this.driveUnit_type = driveUnit_type;
        this.rudder_type = rudder_type;
        this.condition_type = condition_type;
        this.image = image;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public User getOwner() {
        return owner;
    }

    public String getBrand_car() {
        return brand_car;
    }

    public String getModel_car() {
        return model_car;
    }

    public String getYear_issue() {
        return year_issue;
    }

    public String getDate_posting() {
        return date_posting;
    }

    public String getColor() {
        return color;
    }

    public String getMileage() {
        return mileage;
    }

    public String getEngine() {
        return engine;
    }

    public String getBody_type() {
        return body_type;
    }

    public String getGearBox_type() {
        return gearBox_type;
    }

    public String getDriveUnit_type() {
        return driveUnit_type;
    }

    public String getCondition_type() {
        return condition_type;
    }

    public String getImage() {
        return image;
    }

    public int getCost() {
        return cost;
    }

    public String getRudder_type() {
        return rudder_type;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", id_owner=" + owner.getName() +
                ", brand_car='" + brand_car + '\'' +
                ", model_car='" + model_car + '\'' +
                ", year_issue='" + year_issue + '\'' +
                ", date_posting='" + date_posting + '\'' +
                ", color='" + color + '\'' +
                ", mileage='" + mileage + '\'' +
                ", engine='" + engine + '\'' +
                ", body_type='" + body_type + '\'' +
                ", gearBox_type='" + gearBox_type + '\'' +
                ", driveUnit_type='" + driveUnit_type + '\'' +
                ", rudder_type='" + rudder_type + '\'' +
                ", condition_type='" + condition_type + '\'' +
                ", image='" + image + '\'' +
                ", cost=" + cost +
                '}';
    }
}
