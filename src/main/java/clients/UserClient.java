package clients;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import spec.ReqResAPISpecification;

public class UserClient {

    private RequestSpecification requestSpec;

    public UserClient() {
        requestSpec = RestAssured.given().spec(ReqResAPISpecification.getSpec());
    }

    public Response getUsers() {
        return requestSpec.when().get("/users");
    }

    public Response createUser(Object userPayload) {
        return requestSpec.body(userPayload).post("/users");
    }

}
