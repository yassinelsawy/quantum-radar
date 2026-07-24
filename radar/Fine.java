package radar;

import java.util.List;

public class Fine {
    private final String plateNumber;
    private final List<Violation> violations;

    public Fine(String plateNumber, List<Violation> violations) {
        this.plateNumber = plateNumber;
        this.violations = violations;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public List<Violation> getViolations() {
        return violations;
    }

    public int getTotalAmount() {
        int total = 0;
        for (Violation v : violations) {
            total += v.getFee();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Fine for ").append(plateNumber).append(":");
        for (Violation v : violations) {
            sb.append("\n  - ").append(v);
        }
        sb.append("\n  Total: $").append(getTotalAmount());
        return sb.toString();
    }
}
