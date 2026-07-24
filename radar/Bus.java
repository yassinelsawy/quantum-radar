package radar;

public class Bus extends CarType {
    public Bus() {
        super("Bus");
    }

    @Override
    public int getMaxSpeed() {
        return 70;
    }
}
