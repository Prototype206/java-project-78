package hexlet.code.schemas;

import java.util.Objects;

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
        if (isRequired && (value == null || value.isEmpty())) {
            return false;
        }
        if (!isRequired && value == null) {
            return true;
        }
        if (!isRequired && value.isEmpty()) {
            if (minLength != null) {
                return false;
            }
            if (containsSubstring != null) {
                return false;
            }
            return true;
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