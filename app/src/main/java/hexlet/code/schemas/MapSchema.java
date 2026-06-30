package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema required() {
        addCheck("required", value -> value != null);
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck("sizeof", value -> {
            if (value == null) {
                return true;
            }
            return value.size() == size;
        });
        return this;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public MapSchema shape(Map<String, BaseSchema<?>> schemas) {
        addCheck("shape", value -> {
            if (value == null) {
                return true;
            }
            for (Map.Entry<String, BaseSchema<?>> entry : schemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema schema = entry.getValue();
                Object actualValue = value.get(key);

                if (!schema.isValid(actualValue)) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }
}