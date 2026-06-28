package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {
    private Integer minLength = null;
    private String containsSubstring = null;

    public StringSchema required() {
        isRequired = true;
        return this;
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        return this;
    }

    public StringSchema contains(String substring) {
        this.containsSubstring = substring;
        return this;
    }

    @Override
    public boolean isValid(String value) {
        if (isRequired) {
            if (value == null || value.isEmpty()) {
                return false;
            }
        } else {
            if (value == null) {
                return true;
            }
            if (value.isEmpty()) {
                if (minLength == null && containsSubstring == null) {
                    return true;
                }
                return false;
            }
        }
        if (minLength != null && value.length() < minLength) {
            return false;
        }
        if (containsSubstring != null && !value.contains(containsSubstring)) {
            return false;
        }

        return true;
    }
}