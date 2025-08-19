package com.thetestingacademy.base;

import com.thetestingacademy.asserts.AssertActions;
import com.thetestingacademy.endpoints.APIConstant;
import com.thetestingacademy.modules.PayloadManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    public RequestSpecification requestSpecification;
    public ValidatableResponse validatableResponse;
    public PayloadManager payloadManager;
    public AssertActions assertActions;
    public JsonPath jsonPath;
    public Response response;

    @BeforeTest
    public void setUp() {
        payloadManager = new PayloadManager();
        assertActions = new AssertActions();

        requestSpecification = RestAssured.given()
                .baseUri(APIConstant.BASE_URL)
                .contentType(ContentType.JSON)
                .log().all();
    }


    public String getToken() {
        requestSpecification = RestAssured.given()
                .baseUri(APIConstant.BASE_URL)
                .basePath(APIConstant.AUTH_URL);

        // Setting up the payload
        String payload = payloadManager.setAuthPayload();
        //get the token
        response = requestSpecification.contentType(ContentType.JSON).body(payload).when().post();

        // String Extraction
        String token = payloadManager.getTokenFromJSON(response.asString());
        return token;
    }
}
