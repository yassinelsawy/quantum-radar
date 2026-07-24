package radar;

public interface Rule {
    String getName();

    Violation check(Observation obs);
}
