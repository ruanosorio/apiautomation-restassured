package clients;

import dto.UserDTO;
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

    public Response updateUser(int userId, UserDTO userPayload) {
        return requestSpec.body(userPayload).put("/users/" + userId);
    }

    public Response deleteUser(int userId) {
        return requestSpec.when().delete("/users/" + userId);
    }

    public Response getUserById(int userId) {
        return requestSpec.when().get("/users/"+ userId);
    }

}
