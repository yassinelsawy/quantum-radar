package radar;

public class SpeedRule implements Rule {
    private final int fee;

    public SpeedRule(int fee) {
        this.fee = fee;
    }

    @Override
    public String getName() {
        return "Speed";
    }

    @Override
    public Violation check(Observation obs) {
        int limit = obs.getCarType().getMaxSpeed();
        if (obs.getSpeed() > limit) {
            int over = obs.getSpeed() - limit;
            String description = String.format(
                    "%s did %d in a %d zone (%d over)",
                    obs.getCarType().getName(), obs.getSpeed(), limit, over);
            return new Violation(getName(), description, fee);
        }
        return null;
    }
}
