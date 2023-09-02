package eta_servicetest;

import com.github.javafaker.Faker;
import org.junit.Before;
import org.junit.Test;

public class Exercicio04 {

    private Usuario usuario;

    private Produto produto;

    private Carrinho carrinho;

    private Login login;

    private Faker faker;

    public Exercicio04() {
    }

    @Before
    public void preCondition(){
        faker = new Faker();
        String userName = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();
        usuario = new Usuario(userName, email, password, "true");
        login = new Login(email, password);
        String productName = faker.book().title();
        produto = new Produto(productName, 50, "Livro", 100);
        carrinho = new Carrinho();
    }

    @Test
    public void verificarExcluirUsuarioComCarrinho(){
        String userID = usuario.cadastrarUsuario(usuario);
        String userToken = login.efetuarLogin(login);
        String productID = produto.cadastrarProduto(produto, userToken);
        carrinho.cadastrarCarrinho(productID, 5, userToken);
        usuario.validarDeletarUsuarioComCarrinho(userID);
        carrinho.cancelarCompra(userToken);
        produto.deletarProdutoPorID(productID, userToken);
        usuario.deletarUsuario(userID);
    }
}
