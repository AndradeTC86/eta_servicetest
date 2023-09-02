package eta_servicetest;

import com.github.javafaker.Faker;
import org.junit.Before;
import org.junit.Test;

public class Exercicio02 {

    private Usuario usuario;

    private Login login;

    private Faker faker;

    public Exercicio02() {
    }

    @Before
    public void preCondition(){
        faker = new Faker();
        String userName = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();
        usuario = new Usuario(userName, email, password, "true");
        login = new Login(email, password);
    }

    @Test
    public void verificarUsuario(){
        usuario.listUsuarios();
        String userID = usuario.cadastrarUsuario(usuario);
        usuario.listarUsuarioPorID(userID);
        usuario.validarCadastrarUsuarioRepetido(usuario);
        login.efetuarLogin(login);
        usuario.deletarUsuario(userID);
    }
}
