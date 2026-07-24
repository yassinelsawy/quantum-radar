package radar;

public class Truck extends CarType {
    public Truck() {
        super("Truck");
    }

    @Override
    public int getMaxSpeed() {
        return 60;
    }
}
