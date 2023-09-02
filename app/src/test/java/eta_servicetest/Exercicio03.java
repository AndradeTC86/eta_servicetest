package eta_servicetest;

import com.github.javafaker.Faker;
import org.junit.Before;
import org.junit.Test;

public class Exercicio03 {

    private Usuario usuario;

    private Produto produto;

    private Carrinho carrinho;

    private Login login;

    private Faker faker;

    public Exercicio03() {
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
    public void verificarEstoque(){
        String userID = usuario.cadastrarUsuario(usuario);
        String userToken = login.efetuarLogin(login);
        String productID = produto.cadastrarProduto(produto, userToken);
        carrinho.cadastrarCarrinho(productID, 5, userToken);
        produto.listarProdutoPorID(productID, 95);
        carrinho.cancelarCompra(userToken);
        produto.listarProdutoPorID(productID, 100);
        produto.deletarProdutoPorID(productID, userToken);
        usuario.deletarUsuario(userID);
    }
}
