package radar;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Radar {
    private final List<Rule> rules = new ArrayList<>();
    private final List<Fine> fines = new ArrayList<>();

    public void addRule(Rule rule) {
        rules.add(rule);
    }

    public Fine inspect(Observation obs) {
        List<Violation> violations = new ArrayList<>();
        for (Rule rule : rules) {
            Violation v = rule.check(obs);
            if (v != null) {
                violations.add(v);
            }
        }
        if (violations.isEmpty()) {
            return null;
        }
        Fine fine = new Fine(obs.getPlateNumber(), violations);
        fines.add(fine);
        return fine;
    }

    public Map<String, Integer> getFinesByPlate() {
        Map<String, Integer> byPlate = new LinkedHashMap<>();
        for (Fine fine : fines) {
            byPlate.merge(fine.getPlateNumber(), fine.getTotalAmount(), Integer::sum);
        }
        return byPlate;
    }

    public Map<String, Integer> getViolationCounts() {
        Map<String, Integer> counts = new LinkedHashMap<>();
        for (Fine fine : fines) {
            for (Violation v : fine.getViolations()) {
                counts.merge(v.getRuleName(), 1, Integer::sum);
            }
        }
        return counts;
    }

    public List<Fine> getFines() {
        return fines;
    }
}
