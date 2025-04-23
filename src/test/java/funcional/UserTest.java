package funcional;

import clients.UserClient;
import dataprovider.UserDataProvider;
import dto.UserDTO;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Feature("Testes para validar CRUD de Usuários")
public class UserTest {

    private UserClient userClient;
    private int userIdDelecao;

    @BeforeClass
    public void setUp() {
        userClient = new UserClient();
    }


    @Story("Criação de novo usuário")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Este teste valida a criação de um novo usuário na API utilizando dados válidos.")
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
    @Severity(SeverityLevel.NORMAL)
    @Story("Consultar Listagem de Usuarios")
    public void validarConsultaListaDeUsuariosTeste() {
        userClient.getUsers()
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("data.size()", org.hamcrest.Matchers.greaterThan(0));
    }

    @Test(dataProvider = "atualizarUsuario", dataProviderClass = UserDataProvider.class, dependsOnMethods = "validarCriacaoNovoUsuarioTeste")
    @Story("Atualizar dados de usuário válido")
    public void validarAtualizarUsuarioTeste(UserDTO usuarioAtualizado) {
        userClient.updateUser(userIdDelecao, usuarioAtualizado)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("name", org.hamcrest.Matchers.equalTo(usuarioAtualizado.getName()))
                .body("job", org.hamcrest.Matchers.equalTo(usuarioAtualizado.getJob()))
                .body("updatedAt", org.hamcrest.Matchers.notNullValue());
    }

    @Test(dependsOnMethods = "validarAtualizarUsuarioTeste")
    @Story("Deletar registro de usuário")
    public void validarDelecaoUsuarioTeste() {
        // Criando um usuário para o teste de deleção
        UserDTO usuarioCriacao = new UserDTO();
        usuarioCriacao.setName("Deletar Teste");
        usuarioCriacao.setJob("Deletar Job");

        Response responseCriacao = userClient.createUser(usuarioCriacao);
        Assert.assertEquals(responseCriacao.getStatusCode(), HttpStatus.SC_CREATED, "Falha ao criar usuário para o teste de deleção");
        userIdDelecao = responseCriacao.jsonPath().getInt("id");
        Assert.assertNotNull(userIdDelecao, "ID do usuário para deleção não deve ser nulo");

        // Deletando usuario
        userClient.deleteUser(userIdDelecao)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_NO_CONTENT);

        // Teste tentativa de consultar usuario deletado
        Response response = userClient.getUserById(userIdDelecao);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND, "Usuário foi deletado, id não encontrado!");


    }
}
