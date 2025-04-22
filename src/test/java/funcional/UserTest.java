package funcional;

import clients.UserClient;
import dataprovider.UserDataProvider;
import dto.UserDTO;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserTest {

    private UserClient userClient;

    @BeforeClass
    public void setUp() {
        userClient = new UserClient();
    }

    @Test(dataProvider = "criarNovoUsuario", dataProviderClass = UserDataProvider.class)
    public void validarCriacaoNovoUsuarioTeste(UserDTO usuario) {
        userClient.createUser(usuario)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .body("name", Matchers.equalTo(usuario.getName()))
                .body("job", Matchers.equalTo(usuario.getJob()));
    }

    @Test
    public void validarConsultaListaDeUsuariosTeste() {
        userClient.getUsers()
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("data.size()", org.hamcrest.Matchers.greaterThan(0));
    }



}
