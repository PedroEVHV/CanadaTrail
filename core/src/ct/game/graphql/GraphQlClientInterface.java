package ct.game.graphql;

import ct.game.characters.Character;
import ct.game.geographical.Location;
import ct.game.inventories.Item;
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
    static HttpResponse request(String query, String url) throws RuntimeException, IOException, URISyntaxException {
        //source : https://techndeck.com/post-request-with-json-body-using-apache-httpclient/

        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");
        JSONObject json = new JSONObject();
        json.put("query", query);
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
            response = request(query, url);
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

                output.add(new Character("SAV0", (String) tempObj.get("name1"), (String) tempObj.get("name2"), new ArrayList<>()));
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
            response = request(query, url);
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

                output.add(new Location("SAV0", (String) tempObj.get("name"), (String) tempObj.get("description"), (String) tempObj.get("spriteCode"), new ArrayList<>()));
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
            response = request(query, url);
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

                output.add(new Item("SAV0", (String) tempObj.get("name"), (String) tempObj.get("description"), (String) tempObj.get("effectCode")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return output;
        }


        return output;

    }


    static HashMap<String, Integer> loadInventorySetup(String query, String url){
        HashMap<String, Integer> output = new HashMap<String, Integer>();
        HttpResponse response;
        try {
            response = request(query, url);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line ="";
            StringBuilder responseString = new StringBuilder();
            while((line = buffer.readLine()) != null) {
                responseString.append(line);
            }
            String parsedResponse = new String(responseString);
            JSONObject json = (JSONObject) new JSONObject(parsedResponse).get("data");
            JSONObject setupJson = json.getJSONObject("setup");
            JSONObject tempObj = setupJson.getJSONObject("food");

            output.put((String) tempObj.get("item"), (Integer) tempObj.get("amount"));
            tempObj = setupJson.getJSONObject("water");
            output.put((String) tempObj.get("item"), (Integer) tempObj.get("amount"));
            tempObj = setupJson.getJSONObject("medical");
            output.put((String) tempObj.get("item"), (Integer) tempObj.get("amount"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return output;
        }


        return output;
    }
}
