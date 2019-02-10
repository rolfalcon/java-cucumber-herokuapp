package support;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.body.Body;
import com.mashape.unirest.request.body.RequestBodyEntity;
import org.json.JSONObject;

import java.util.ConcurrentModificationException;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class RestWrapper {

    private String token;
    private String baseUrl = "https://skryabin.com/recruit/api/v1/";
    private int jobID;

    private final String CONTENT_TYPE = "Content-Type";
    private final String JSON = "application/json";

    public void login(HashMap<String, String> creds) throws UnirestException {
        RequestBodyEntity request = Unirest.post(baseUrl + "login")
                .header(CONTENT_TYPE, JSON)
                .body(new JSONObject(creds));

        HttpResponse<JsonNode> response = request.asJson();
        assertThat(response.getStatus()).isEqualTo(200);

        JSONObject body = response.getBody().getObject();
        token = body.getString("token");

    }

    public int getCandidates () throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(baseUrl + "positions")
                .header(CONTENT_TYPE, JSON)
                .asJson();

        assertThat(response.getStatus()).isEqualTo(200);
        return response.getBody().getArray().length();


    }

    public void createPosition (HashMap<String, String> position) throws UnirestException {

        HttpResponse<JsonNode> jsonResponse = Unirest.post(baseUrl + "positions")
                .header("x-access-token", token)
                .header(CONTENT_TYPE, JSON)
                .body(new JSONObject(position))
                .asJson();

        assertThat(jsonResponse.getStatus()).isEqualTo(201);
        JSONObject body = jsonResponse.getBody().getObject();

        jobID = body.getInt("id");



    }

    public String getJobID () {
        return String.valueOf(jobID);
    }

    public void deletePosition (String id) throws UnirestException {
        String urlToDeletePositions = baseUrl +  "positions/" + id;
        HttpResponse<JsonNode> jsonResponse = Unirest.delete(urlToDeletePositions)
                .header("x-access-token", token)
                .asJson();

        assertThat(jsonResponse.getStatus()).isEqualTo(204);



    }
}


