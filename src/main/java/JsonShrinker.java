import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JsonShrinker {

    public static void main(String[] args) {
        JSONParser jsonParser;
        FileReader reader;
        Object     obj;
        JSONArray  cardsList = new JSONArray();
        List<Object> cards = new ArrayList<>();

        jsonParser = new JSONParser();
        try {
            reader    = new FileReader(System.getProperty("user.dir") +"/src/main/resources/AllCards.json");
            obj       =  jsonParser.parse(reader);
            cards.add(obj);
//            cardsList.add(obj);

            for (int i = 0; i < 10; i++) {
                System.out.println(cards.get(i));
            }

//            cardsList.forEach(card -> System.out.println(card));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
