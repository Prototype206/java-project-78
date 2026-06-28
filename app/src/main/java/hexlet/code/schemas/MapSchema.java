package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<String, Object>> {
    private Integer size = null;
    private Map<String, BaseSchema<?>> schemas = null;

    public MapSchema required() {
        isRequired = true;
        return this;
    }

    public MapSchema sizeof(int size) {
        this.size = size;
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<?>> schemas) {
        this.schemas = schemas;
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

        if (schemas != null) {
            for (Map.Entry<String, BaseSchema<?>> entry : schemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema<?> schema = entry.getValue();
                Object fieldValue = value.get(key);

                if (!schema.isValidValue(fieldValue)) {
                    return false;
                }
            }
        }

        return true;
    }
}