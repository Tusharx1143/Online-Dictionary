import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class DictionaryService {
    private static final String API_BASE_URL = "https://api.dictionaryapi.dev/api/v2/entries/en/";

    public static List<String> getMeanings(String word) {
        List<String> meanings = new ArrayList<>();

        try {
            URL url = new URL(API_BASE_URL + word);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            try (JsonReader jsonReader = Json.createReader(new StringReader(response.toString()))) {
                JsonArray jsonArray = jsonReader.readArray();
                if (jsonArray.size() > 0) {
                    JsonObject entry = jsonArray.getJsonObject(0);
                    JsonArray meaningsArray = entry.getJsonArray("meanings");
                    for (int i = 0; i < meaningsArray.size(); i++) {
                        JsonObject meaningObject = meaningsArray.getJsonObject(i);
                        JsonArray definitionsArray = meaningObject.getJsonArray("definitions");
                        for (int j = 0; j < definitionsArray.size(); j++) {
                            JsonObject definitionObject = definitionsArray.getJsonObject(j);
                            String definition = definitionObject.getString("definition");
                            if (definition != null) {
                                meanings.add(definition);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return meanings;
    }

    public static List<String> getExamples(String word) {
        List<String> examples = new ArrayList<>();

        try {
            URL url = new URL(API_BASE_URL + word);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            try (JsonReader jsonReader = Json.createReader(new StringReader(response.toString()))) {
                JsonArray jsonArray = jsonReader.readArray();
                if (jsonArray.size() > 0) {
                    JsonObject entry = jsonArray.getJsonObject(0);
                    JsonArray meaningsArray = entry.getJsonArray("meanings");
                    for (int i = 0; i < meaningsArray.size(); i++) {
                        JsonObject meaningObject = meaningsArray.getJsonObject(i);
                        JsonArray definitionsArray = meaningObject.getJsonArray("definitions");
                        for (int j = 0; j < definitionsArray.size(); j++) {
                            JsonObject definitionObject = definitionsArray.getJsonObject(j);
                            if (definitionObject.containsKey("example")) {
                                String example = definitionObject.getString("example");
                                if (example != null) {
                                    examples.add(example);
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return examples;
    }

    public static List<String> getSynonyms(String word) {
        List<String> synonyms = new ArrayList<>();

        try {
            URL url = new URL(API_BASE_URL + word);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            try (JsonReader jsonReader = Json.createReader(new StringReader(response.toString()))) {
                JsonArray jsonArray = jsonReader.readArray();
                if (jsonArray.size() > 0) {
                    JsonObject entry = jsonArray.getJsonObject(0);
                    JsonArray meaningsArray = entry.getJsonArray("meanings");
                    for (int i = 0; i < meaningsArray.size(); i++) {
                        JsonObject meaningObject = meaningsArray.getJsonObject(i);
                        if (meaningObject.containsKey("synonyms")) {
                            JsonArray synonymsArray = meaningObject.getJsonArray("synonyms");
                            for (int j = 0; j < synonymsArray.size(); j++) {
                                String synonym = synonymsArray.getString(j);
                                synonyms.add(synonym);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return synonyms;
    }

    public static List<String> getAntonyms(String word) {
        List<String> antonyms = new ArrayList<>();

        try {
            URL url = new URL(API_BASE_URL + word);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            try (JsonReader jsonReader = Json.createReader(new StringReader(response.toString()))) {
                JsonArray jsonArray = jsonReader.readArray();
                if (jsonArray.size() > 0) {
                    JsonObject entry = jsonArray.getJsonObject(0);
                    JsonArray meaningsArray = entry.getJsonArray("meanings");
                    for (int i = 0; i < meaningsArray.size(); i++) {
                        JsonObject meaningObject = meaningsArray.getJsonObject(i);
                        if (meaningObject.containsKey("antonyms")) {
                            JsonArray antonymsArray = meaningObject.getJsonArray("antonyms");
                            for (int j = 0; j < antonymsArray.size(); j++) {
                                String antonym = antonymsArray.getString(j);
                                antonyms.add(antonym);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return antonyms;
    }

}
