package dictionary;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Dictionary {
    private final Map<String, String> dictionary;
    private final String filePath;
    private final DictionaryValidator validator;

    public Dictionary(String filePath, DictionaryValidator validator) {
        this.filePath = filePath;
        this.validator = validator;
        this.dictionary = new HashMap<>();
        loadDictionary();
    }

    private void loadDictionary() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" - ");
                if (parts.length == 2) {
                    dictionary.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка загрузки словаря: " + e.getMessage());
        }
    }

    public void saveDictionary() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, String> entry : dictionary.entrySet()) {
                writer.write(entry.getKey() + " - " + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Ошибка сохранения словаря: " + e.getMessage());
        }
    }

    public boolean add(String key, String value) {
        if (validator.isValid(key)) {
            dictionary.put(key, value);
            saveDictionary();
            return true;
        }
        return false;
    }

    public String findEntry(String key) {
        return dictionary.get(key);
    }

    public boolean removeEntry(String key) {
        if (dictionary.containsKey(key)) {
            dictionary.remove(key);
            saveDictionary();
            return true;
        }
        return false;
    }

    public void print() {
        if (dictionary.isEmpty()) {
            System.out.println("Словарь пуст");
            return;
        }

        dictionary.forEach((key, value) ->
                System.out.println(key + " - " + value));
    }
}