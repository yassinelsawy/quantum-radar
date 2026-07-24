package radar;

public abstract class CarType {
    private final String name;

    protected CarType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract int getMaxSpeed();

    public static CarType fromName(String name) {
        switch (name.trim().toLowerCase()) {
            case "privatecar":
            case "private":
            case "car":
                return new PrivateCar();
            case "truck":
                return new Truck();
            case "bus":
                return new Bus();
            default:
                throw new IllegalArgumentException("Unknown car type: " + name);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
