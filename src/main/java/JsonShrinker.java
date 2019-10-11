import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonShrinker {

    public static void main(String[] args) throws IOException {
        JsonShrinker shrinker;

        shrinker = new JsonShrinker();
        shrinker.createJsonFile(shrinker.getCardsNames());
    }

    private JSONArray getCardsNames() {
        JSONParser jsonParser;
        FileReader reader;
        Object     obj;
        JSONObject jsonObject;
        JSONObject temp;
        List       values;
        String     nameEN;
        String     namePT;
        JSONArray  foreignData;
        JSONObject foreignLanguage;
        JSONArray  outputArray;
        JSONObject outputObject;

        jsonParser  = new JSONParser();
        outputArray = new JSONArray();
        try {
            reader     = new FileReader(System.getProperty("user.dir") +"/src/resources/AllCards.json");
            obj        = jsonParser.parse(reader);
            jsonObject = (JSONObject) obj;

            values = new ArrayList(jsonObject.values());

            for (int i = 0; i < values.size(); i++) {
                outputObject = new JSONObject();

                temp   = (JSONObject) values.get(i);
                nameEN = temp.get("name").toString();
                outputObject.put("nameEN", nameEN);

                foreignData = (JSONArray) temp.get("foreignData");

                if (foreignData.size() > 0) {
                    for (int y = 0; y < foreignData.size(); y++) {
                        foreignLanguage = (JSONObject) foreignData.get(y);
                        if (foreignLanguage.get("language").equals("Portuguese (Brazil)")) {
                            namePT = foreignLanguage.get("name").toString();
                            outputObject.put("namePT", namePT);
                        }
                    }
                }
                outputArray.add(outputObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputArray;
    }

    private void createJsonFile(JSONArray content) throws IOException {
        try (FileWriter file = new FileWriter(System.getProperty("user.dir") +"/src/output/AllCards.json")) {
            file.write(String.valueOf(content));
            System.out.println("Successfully Copied JSON Object to File...");
        }
    }
}
