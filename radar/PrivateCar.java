package radar;

public class PrivateCar extends CarType {
    public PrivateCar() {
        super("PrivateCar");
    }

    @Override
    public int getMaxSpeed() {
        return 80;
    }
}
