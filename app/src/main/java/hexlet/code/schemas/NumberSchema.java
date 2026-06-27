package hexlet.code.schemas;

import java.util.Objects;

public class NumberSchema extends BaseSchema<Integer> {
    private Integer minRange = null;
    private Integer maxRange = null;
    private Boolean isPositive = null;

    public NumberSchema required() {
        isRequired = true;
        return this;
    }

    public NumberSchema positive() {
        this.isPositive = true;
        return this;
    }

    public NumberSchema range(int min, int max) {
        this.minRange = min;
        this.maxRange = max;
        return this;
    }

    @Override
    public boolean isValid(Integer value) {
        if (!isRequired && value == null) {
            return true;
        }
        if (isRequired && value == null) {
            return false;
        }
        if (isPositive != null && value <= 0) {
            return false;
        }
        if (minRange != null && maxRange != null) {
            if (value < minRange || value > maxRange) {
                return false;
            }
        }

        return true;
    }
}