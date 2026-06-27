package hexlet.code.schemas;

import java.util.Objects;

public class StringSchema {
    private boolean isRequired = false;
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

    public boolean isValid(String value) {
        if (!isRequired && (value == null || value.isEmpty())) {
            return true;
        }
        if (isRequired && (value == null || value.isEmpty())) {
            return false;
        }
        if (minLength != null && (value == null || value.length() < minLength)) {
            return false;
        }
        if (containsSubstring != null && (value == null || !value.contains(containsSubstring))) {
            return false;
        }

        return true;
    }
}