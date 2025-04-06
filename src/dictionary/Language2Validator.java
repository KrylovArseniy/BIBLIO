package dictionary;

public class Language2Validator extends DictionaryValidator {
    @Override
    public boolean isValid(String key) {
        return isNotEmpty(key) && key.matches("\\d{5}");
    }
}