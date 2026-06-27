package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public class MapSchema extends BaseSchema<Map<String, Object>> {
    private Integer size = null;

    public MapSchema required() {
        isRequired = true;
        return this;
    }

    public MapSchema sizeof(int size) {
        this.size = size;
        return this;
    }

    @Override
    public boolean isValid(Map<String, Object> value) {
        if (!isRequired && value == null) {
            return true;
        }
        if (isRequired && value == null) {
            return false;
        }
        if (size != null && value.size() != size) {
            return false;
        }

        return true;
    }
}