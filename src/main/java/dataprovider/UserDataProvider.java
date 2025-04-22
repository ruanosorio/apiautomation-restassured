package dataprovider;

import dto.UserDTO;
import org.testng.annotations.DataProvider;

public class UserDataProvider {

    @DataProvider(name = "criarNovoUsuario")
    public static Object[][] criarUsuarioData() {
        UserDTO usuario = new UserDTO();
        usuario.setName("Automation");
        usuario.setJob("Specialist Quality Engineer Automation");
        return new Object[][]{{usuario}};
    }

    @DataProvider(name = "atualizarUsuario")
    public static Object[][] atualizarUsuarioData() {
        UserDTO usuarioAtualizado = new UserDTO();
        usuarioAtualizado.setName("Updated Automation");
        usuarioAtualizado.setJob("Tech Lead Engineer Automation");
        return new Object[][]{{usuarioAtualizado}};
    }

}
