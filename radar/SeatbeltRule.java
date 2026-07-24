package radar;

public class SeatbeltRule implements Rule {
    private final int fee;

    public SeatbeltRule(int fee) {
        this.fee = fee;
    }

    @Override
    public String getName() {
        return "Seatbelt";
    }

    @Override
    public Violation check(Observation obs) {
        if (!obs.isSeatbeltFastened()) {
            return new Violation(getName(), "Seatbelt not fastened", fee);
        }
        return null;
    }
}
