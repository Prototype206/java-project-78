package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected Map<String, Predicate<T>> checks = new LinkedHashMap<>();

    protected void addCheck(String name, Predicate<T> validate) {
        checks.put(name, validate);
    }
    public boolean isValid(T value) {
        for (Predicate<T> check : checks.values()) {
            if (!check.test(value)) {
                return false;
            }
        }
        return true;
    }
    @SuppressWarnings("unchecked")
    public boolean isValidValue(Object value) {
        try {
            return isValid((T) value);
        } catch (ClassCastException e) {
            return false;
        }
    }
}