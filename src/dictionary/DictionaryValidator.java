package dictionary;

public abstract class DictionaryValidator {
    public abstract boolean isValid(String key);

    protected boolean isNotEmpty(String key) {
        return key != null && !key.trim().isEmpty();
    }
}