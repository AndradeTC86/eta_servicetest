package eta_servicetest;

import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

public class Usuario {

    private String nome;

    private String email;

    private String password;

    private String administrador;

    public Usuario(String nome, String email, String password, String administrador){
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.administrador = administrador;
    }

    public void listUsuarios(){
        when().
                get("http://localhost:3000/usuarios")
        .then()
                .statusCode(HttpStatus.SC_OK)
    }

    public String cadastrarUsuario(Usuario usuario){
        String userID = given().body("{\n" +
                "  \"nome\": \"" + usuario.nome + "\",\n" +
                "  \"email\": \"" + usuario.email + "\",\n" +
                "  \"password\": \"" + usuario.password + "\",\n" +
                "  \"administrador\": \"" + usuario.administrador + "\"\n" +
                "}")
                .contentType("application/json")
        .when()
                .post("http://localhost:3000/usuarios")
        .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("message", is("Cadastro realizado com sucesso"))
                .extract().path("_id");
        return userID;
    }

    public void listarUsuarioPorID(String userID){
        given()
                .pathParam("_id", userID)
        .when()
                .get("http://localhost:3000/usuarios/{_id}")
        .then()
                .statusCode(HttpStatus.SC_OK)
                .body("_id", is(userID));
    }

    public void deletarUsuario(String userID){
        given()
                .pathParam("_id", userID)
        .when()
                .delete("http://localhost:3000/usuarios/{_id}")
        .then()
                .statusCode(HttpStatus.SC_OK)
                .body("message", is("Registro excluído com sucesso"));
    }

    public void editarUsuario(String userID, Usuario usuario, Boolean exists){

        private String message;
        private HttpStatus statusCode;

        if (exists){
            statusCode = HttpStatus.SC_OK;
            message = "Registro alterado com sucesso";
        }
        else{
            statusCode = HttpStatus.SC_CREATED;
            message = "Cadastro realizado com sucesso";
        }
        given()
                .pathParam("_id", userID)
                .body("{\n" +
                        "  \"nome\": \"" + usuario.nome + "\",\n" +
                        "  \"email\": \"" + usuario.email + "\",\n" +
                        "  \"password\": \"" + usuario.password + "\",\n" +
                        "  \"administrador\": \"" + usuario.administrador + "\"\n" +
                        "}")
        .when()
                .put("http://localhost:3000/usuarios/{_id}")
        .then()
                .statusCode(is(statusCode))
                .body("message", is(message));
    }

}
