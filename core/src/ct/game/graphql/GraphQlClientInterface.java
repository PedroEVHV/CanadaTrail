package ct.game.graphql;

import ct.game.characters.Character;
import ct.game.characters.Trait;
import ct.game.events.Event;
import ct.game.events.EventOption;
import ct.game.geographical.Location;
import ct.game.inventories.Item;
import ct.game.utils.Setup;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

public interface GraphQlClientInterface {
    static HttpResponse request(String query, String url, HashMap<String, String> arguments) throws RuntimeException, IOException, URISyntaxException {
        //source : https://techndeck.com/post-request-with-json-body-using-apache-httpclient/
        // function mainly based-off source, we removed elements that were irrelevant to the project

        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");
        JSONObject json = new JSONObject();
        json.put("query", query);
        if(arguments != null) {
            JSONObject args = new JSONObject();
            for(String s : arguments.keySet()) {
                args.put(s, arguments.get(s));
            }
            json.put("variables", args);
            //System.out.println(json);

        }
        StringEntity stringEntity = new StringEntity(json.toString());
        request.setEntity(stringEntity);
        HttpResponse response = null;
        try {
            response = client.execute(request);
        } catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }


        if(response.getStatusLine().getStatusCode() == 200) {
            return response;
        } else {
            throw new RuntimeException("HTTP error " + response.getStatusLine().getStatusCode());
        }
    }

    static ArrayList<Character> listCharacters(String query, String url) {
        ArrayList<Character> output = new ArrayList<>();
        HttpResponse response;
        try {
            response = request(query, url, null);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line ="";
            StringBuffer stringBuffer = new StringBuffer();
            StringBuilder responseString = new StringBuilder();
            while((line = buffer.readLine()) != null) {
                responseString.append(line);
            }
            String parsedResponse = new String(responseString);
            //System.out.println(" no data !");
            JSONObject json = (JSONObject) new JSONObject(parsedResponse).get("data");
            JSONArray charactersArray = (JSONArray) json.get("characters");
            //System.out.println("array");
            for(int i = 0; i < charactersArray.length(); i++) {
                JSONObject tempObj = (JSONObject) charactersArray.get(i);

                output.add(new Character((String) tempObj.get("id"), (String) tempObj.get("name1"), (String) tempObj.get("name2"), new ArrayList<>()));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return output;
        }


        return output;
    }

    static ArrayList<Location> listLocations(String query, String url) {
        ArrayList<Location> output = new ArrayList<>();
        HttpResponse response;
        try {
            response = request(query, url, null);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line ="";
            StringBuffer stringBuffer = new StringBuffer();
            StringBuilder responseString = new StringBuilder();
            while((line = buffer.readLine()) != null) {
                responseString.append(line);
            }
            String parsedResponse = new String(responseString);
            //System.out.println(" no data !");
            JSONObject json = (JSONObject) new JSONObject(parsedResponse).get("data");
            JSONArray locationsArray = (JSONArray) json.get("locations");
            //System.out.println("array");
            for(int i = 0; i < locationsArray.length(); i++) {
                JSONObject tempObj = (JSONObject) locationsArray.get(i);

                output.add(new Location((String) tempObj.get("id"), (String) tempObj.get("name"), (String) tempObj.get("description"), (String) tempObj.get("spriteCode"), new ArrayList<>()));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return output;
        }


        return output;

    }

    static ArrayList<Item> listItems(String query, String url) {
        ArrayList<Item> output = new ArrayList<>();
        HttpResponse response;
        try {
            response = request(query, url, null);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line ="";
            StringBuilder responseString = new StringBuilder();
            while((line = buffer.readLine()) != null) {
                responseString.append(line);
            }
            String parsedResponse = new String(responseString);
            JSONObject json = (JSONObject) new JSONObject(parsedResponse).get("data");
            JSONArray itemsArray = (JSONArray) json.get("items");
            for(int i = 0; i < itemsArray.length(); i++) {
                JSONObject tempObj = (JSONObject) itemsArray.get(i);
                output.add(new Item((String) tempObj.get("id"), (String) tempObj.get("name"), (String) tempObj.get("description"), (String) tempObj.get("effectCode"), (String) tempObj.get("spriteCode")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return output;
        }


        return output;

    }


    static ArrayList<Trait> listTraits(String query, String url) {
        ArrayList<Trait> output = new ArrayList<>();
        HttpResponse response;
        try {
            response = request(query, url, null);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line ="";
            StringBuilder responseString = new StringBuilder();
            while((line = buffer.readLine()) != null) {
                responseString.append(line);
            }
            String parsedResponse = new String(responseString);
            JSONObject json = (JSONObject) new JSONObject(parsedResponse).get("data");
            JSONArray itemsArray = (JSONArray) json.get("traits");
            for(int i = 0; i < itemsArray.length(); i++) {
                JSONObject tempObj = (JSONObject) itemsArray.get(i);

                output.add(new Trait((String) tempObj.get("id"), (String) tempObj.get("name"), (String) tempObj.get("description"), (Integer) tempObj.get("duration"), (String) tempObj.get("effectCommand")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return output;
        }


        return output;

    }


    static Setup loadSetup(String query, String url){
        Setup output;
        HashMap<String, Integer> inv = new HashMap<>();
        HashMap<String, String> assets = new HashMap<>();
        int trav = 0;
        int eventCap = 0;
        HttpResponse response;
        try {
            response = request(query, url, null);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line ="";
            StringBuilder responseString = new StringBuilder();
            while((line = buffer.readLine()) != null) {
                responseString.append(line);
            }
            String parsedResponse = new String(responseString);
            JSONObject json = (JSONObject) new JSONObject(parsedResponse).get("data");

            JSONObject setupJson = json.getJSONObject("setup");
            //JSONObject tempObj = setupJson.getJSONObject("travelers");
            trav = setupJson.getInt("travelers");
            eventCap = setupJson.getInt("eventCap");
            JSONObject tempObj = setupJson.getJSONObject("food");

            //.out.println("first data");

            inv.put((String) tempObj.get("item"), (Integer) tempObj.get("amount"));
            tempObj = setupJson.getJSONObject("water");
            inv.put((String) tempObj.get("item"), (Integer) tempObj.get("amount"));
            tempObj = setupJson.getJSONObject("medical");
            inv.put((String) tempObj.get("item"), (Integer) tempObj.get("amount"));


            tempObj = setupJson.getJSONObject("assets");
            assets.put("main_menu_bg", (String) tempObj.get("main_menu_bg"));
            assets.put("map", (String) tempObj.get("map"));

            JSONArray itemArray = setupJson.getJSONArray("items");
            for(int i = 0; i < itemArray.length(); i++) {
                JSONObject object = (JSONObject) itemArray.get(i);
                inv.put((String) object.get("item"), (Integer) object.get("amount"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }


        return new Setup(inv, trav, eventCap, assets);
    }


    static Event getEvent(String query, String url, String eventCode) {
        String id, name, description;
        ArrayList<EventOption> options = new ArrayList<>();
        HttpResponse response;
        try{
            HashMap<String, String> args = new HashMap<>();
            args.put("code", eventCode);
            response = request(query, url, args);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line ="";
            StringBuilder responseString = new StringBuilder();
            while((line = buffer.readLine()) != null) {
                responseString.append(line);
            }
            String parsedResponse = new String(responseString);
            JSONObject json = (JSONObject) new JSONObject(parsedResponse).get("data");
            System.out.println(json);
            JSONObject eventJson = (JSONObject) json.get("event");
            //System.out.println(eventJson);
            id = String.valueOf(eventJson.get("id"));

            name = String.valueOf(eventJson.get("title"));

            description = String.valueOf(eventJson.get("description"));

            JSONArray optionsObject = new JSONArray(eventJson.getJSONArray("options"));
            for(int i = 0; i < optionsObject.length(); i++) {
                JSONObject obj = (JSONObject) optionsObject.get(i);

                options.add(new EventOption((Integer) obj.get("number"), (String) obj.get("text"), (String) obj.get("effectCode"), (String) obj.get("description")));

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return new Event(id, name, description, options, "");
    }
}
