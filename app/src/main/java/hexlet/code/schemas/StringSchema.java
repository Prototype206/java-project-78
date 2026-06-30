package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        addCheck("required", value -> value != null && !value.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        addCheck("minLength", value -> {
            if (value == null) {
                return true;
            }
            return value.length() >= length;
        });
        return this;
    }

    public StringSchema contains(String substring) {
        addCheck("contains", value -> {
            if (value == null) {
                return true;
            }
            return value.contains(substring);
        });
        return this;
    }
}