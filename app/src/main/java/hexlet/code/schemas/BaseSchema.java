package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected Map<String, Predicate<T>> checks = new LinkedHashMap<>();

    protected void addCheck(String name, Predicate<T> validate) {
        checks.put(name, validate);
    }

    @SuppressWarnings("unchecked")
    public boolean isValid(Object value) {
        for (Predicate<T> check : checks.values()) {
            try {
                if (!check.test((T) value)) {
                    return false;
                }
            } catch (ClassCastException e) {
                return false;
            }
        }
        return true;
    }
}