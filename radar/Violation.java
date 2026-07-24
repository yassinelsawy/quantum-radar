package radar;

public class Violation {
    private final String ruleName;
    private final String description;
    private final int fee;

    public Violation(String ruleName, String description, int fee) {
        this.ruleName = ruleName;
        this.description = description;
        this.fee = fee;
    }

    public String getRuleName() {
        return ruleName;
    }

    public int getFee() {
        return fee;
    }

    @Override
    public String toString() {
        return ruleName + ": " + description + " ($" + fee + ")";
    }
}
