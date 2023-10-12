package ct.game.graphql;

import ct.game.geographical.Location;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;

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

    static ArrayList<Location> listLocations(String query, String url) {
        ArrayList<Location> output = new ArrayList<>();
        HttpResponse response;
        InputStream stream;
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
}
