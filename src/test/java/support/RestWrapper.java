package support;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.body.Body;
import com.mashape.unirest.request.body.RequestBodyEntity;
import cucumber.api.java.lv.Un;
import gherkin.deps.com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class RestWrapper {

    //private String token;
    private String baseUrl = "https://skryabin.com/recruit/api/v1/";
    private int jobID;

    private final String CONTENT_TYPE = "Content-Type";
    private final String JSON = "application/json";
    private static String loginToken;

    private final String TOKEN = "x-access-token";
    public static final String POSITIONS = "positions";
    public static final String POSITION = "position";


    public RestWrapper login(HashMap<String, String> credentials) throws UnirestException {
        JSONObject payload = new JSONObject(credentials);
        RequestBodyEntity request = Unirest.post(baseUrl + "login")
                .header(CONTENT_TYPE, JSON)
                .body(payload);

        HttpResponse<JsonNode> response = request.asJson();
        assertThat(response.getStatus()).isEqualTo(200);
        JSONObject body = response.getBody().getObject();
        loginToken = body.getString("token");
        System.out.println("Login successful! Token: " + loginToken);
        return this;
    }

    public int getCandidates () throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(baseUrl + "positions")
                .header(CONTENT_TYPE, JSON)
                .asJson();

        assertThat(response.getStatus()).isEqualTo(200);
        return response.getBody().getArray().length();


    }

    public JSONObject createPosition(HashMap<String, String> position) throws UnirestException {

        String dateOpen = position.get("dateOpen");
        String dateOpenISO = new SimpleDateFormat("yyyy-MM-dd").format(new Date(dateOpen));
        // workaround for the issue that always displays the time
        dateOpenISO = dateOpenISO + "T05:00:00.000Z";
        position.put("dateOpen", dateOpenISO);

        JSONObject positionJson = new JSONObject(position);
        RequestBodyEntity request = Unirest.post(baseUrl + POSITIONS)
                .header(CONTENT_TYPE, JSON)
                .header(TOKEN, loginToken)
                .body(positionJson);

        HttpResponse<JsonNode> response = request.asJson();
        assertThat(response.getStatus()).isEqualTo(201);
        JSONObject responsePositionJson = response.getBody().getObject();
        System.out.println("\n\nPosition created: " + responsePositionJson);

        TestContext.setTestData(POSITION, responsePositionJson);

        return responsePositionJson;
    }

    public JSONArray getPositions() throws Exception {
        GetRequest request = Unirest.get(baseUrl + POSITIONS);

        HttpResponse<JsonNode> response = request.asJson();

        assertThat(response.getStatus()).isBetween(200, 204);
        JSONArray positionsJson = response.getBody().getArray();

        return positionsJson;
    }

    public String getJobID () {
        return String.valueOf(jobID);
    }

    public void deletePosition (String id) throws UnirestException {
        String urlToDeletePositions = baseUrl +  "positions/" + id;
        HttpResponse<JsonNode> jsonResponse = Unirest.delete(urlToDeletePositions)
                .header(TOKEN, loginToken)
                .asJson();

        assertThat(jsonResponse.getStatus()).isEqualTo(204);



    }

    public JSONObject updatePosition (JSONObject position) throws UnirestException {
        String urlToUpdatePositons = baseUrl + POSITIONS + "/" + position.get("id");

        RequestBodyEntity request= Unirest.put(urlToUpdatePositons)
                .header(TOKEN, loginToken)
                .header(CONTENT_TYPE, JSON)
                .body(position);

        HttpResponse<JsonNode> response = request.asJson();
        assertThat(response.getStatus()).isEqualTo(200);
        JSONObject responsePositionJson = response.getBody().getObject();
        System.out.println("\n\nPosition updated: " + responsePositionJson);

        TestContext.setTestData(POSITION, responsePositionJson);

        return responsePositionJson;

    }

    public boolean verifyPosition (JSONObject position) throws Exception {

        String urlToVerifyPositons = baseUrl + POSITIONS + "/" + position.get("id");

        GetRequest request= Unirest.get(urlToVerifyPositons);

        HttpResponse<JsonNode> response = request.asJson();
        assertThat(response.getStatus()).isEqualTo(200);

        JSONObject data = response.getBody().getObject();

        JSONObject dataToVerify = TestContext.getJsonTestData(POSITION);

       return data.equals(dataToVerify);


    }

}


