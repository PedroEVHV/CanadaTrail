package ct.game.graphql;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.net.URISyntaxException;

public interface GraphQlClientInterface {
    static HttpResponse request(String query, String url) throws RuntimeException, IOException, URISyntaxException {
        //source : https://techndeck.com/post-request-with-json-body-using-apache-httpclient/

        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");

        StringEntity stringEntity = new StringEntity(query);
        request.setEntity(stringEntity);
        HttpResponse response = null;
        try {
            response = client.execute(request);
        } catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }


        if(response.getStatusLine().getStatusCode() != 200) {
            return response;
        } else {
            throw new RuntimeException("HTTP error" + response.getStatusLine().getStatusCode());
        }
    }
}
