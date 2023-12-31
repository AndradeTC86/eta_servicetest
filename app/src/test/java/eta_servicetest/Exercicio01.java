package eta_servicetest;

import com.github.javafaker.Faker;
import org.junit.Before;
import org.junit.Test;

public class Exercicio01 {

    private Usuario usuario;

    private Faker faker;

    public Exercicio01() {
    }

    @Before
    public void preCondition(){
        faker = new Faker();
        String userName = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();
        usuario = new Usuario(userName, email, password, "true");
    }

    @Test
    public void verificarUsuario(){
        usuario.listUsuarios();
        String userID = usuario.cadastrarUsuario(usuario);
        usuario.listarUsuarioPorID(userID);
        usuario.deletarUsuario(userID);
    }
}
