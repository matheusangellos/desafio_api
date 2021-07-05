package restricoes;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import test.BaseAPI;

public class RestricoesTest extends BaseAPI {

    @Test
    public void pessoaComRetricao() {
        String cpf = "97093236014";

        given().
            pathParam("cpf", cpf).
        when().
            get("/v1/restricoes/{cpf}").
        then().
            statusCode(HttpStatus.SC_OK).
            body("mensagem", is("O CPF " + cpf + " tem problema"));
    }

    @Test
    public void pessoaSemRestricao() {
        String cpf = "87365927862";

        given().
            pathParam("cpf", cpf).
        when().
            get("/v1/restricoes/{cpf}").
        then().
            statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
