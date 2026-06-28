package hexlet.code.schemas;

public abstract class BaseSchema<T> {
    protected boolean isRequired = false;

    public abstract boolean isValid(T value);

    @SuppressWarnings("unchecked")
    public boolean isValidValue(Object value) {
        if (value == null) {
            return isValid(null);
        }

        try {
            return isValid((T) value);
        } catch (ClassCastException e) {
            return false;
        }
    }
}