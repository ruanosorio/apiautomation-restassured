import clients.UserClient;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTest;

import static org.apache.http.HttpStatus.SC_OK;

public class SmokeTest extends BaseTest {

    @Test
    public void deveriaRetornarListagemDeUsuarios(){

        UserClient userClient = new UserClient();

        Response response = userClient.getUsers();

        // Validando statuscode
        Assert.assertEquals(response.getStatusCode(), SC_OK, "Status code deve ser 200");

        // Validando se o response body não está vazio
        Assert.assertFalse(response.getBody().asString().isEmpty(), "Response body não deve estar vazio");

    }
}
