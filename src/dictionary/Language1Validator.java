package dictionary;

public class Language1Validator extends DictionaryValidator {
    @Override
    public boolean isValid(String key) {
        return isNotEmpty(key) && key.matches("[a-zA-Z]{4}");
    }
}