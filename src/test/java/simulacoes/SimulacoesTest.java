package simulacoes;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import test.BaseAPI;
import api.body.Simulacao;

import java.math.BigDecimal;
import java.util.Random;

public class SimulacoesTest extends BaseAPI {

    Faker feku = new Faker();
    Random gerador = new Random();

    @Test
    public void validarRegraValor() {
        String nome = feku.name().fullName();
        String cpf = "0123456789" + gerador.nextInt(9);
        String email = feku.name().firstName() + gerador.nextInt(100) + "@test.com";
        int valor = gerador.nextInt(100) + 40000;
        int parcelas = gerador.nextInt(46) + 2;
        boolean seguro = gerador.nextBoolean();

        Simulacao simulacao =
                Simulacao.builder().nome(nome).cpf(cpf).email(email).valor(valor).parcelas(parcelas).seguro(seguro).build();

        given().
                contentType(ContentType.JSON).
                body(simulacao).
                when().
                post("/v1/simulacoes").
                then().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                body(containsString("Valor deve ser menor ou igual a R$ 40.000"));
    }

    @Test
    public void validarRegraParcela() {
        String nome = feku.name().fullName();
        String cpf = "0123456789" + gerador.nextInt(9);
        String email = feku.name().firstName() + gerador.nextInt(100) + "@test.com";
        int valor = gerador.nextInt(39000) + 1000;
        int parcelas = 0;
        boolean seguro = gerador.nextBoolean();

        Simulacao simulacao =
                Simulacao.builder().nome(nome).cpf(cpf).email(email).valor(valor).parcelas(parcelas).seguro(seguro).build();

        given().
                contentType(ContentType.JSON).
                body(simulacao).
                when().
                post("/v1/simulacoes").
                then().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                body(containsString("Parcelas deve ser igual ou maior que 2"));
    }

    @Test
    public void retornarTodasSimulacoesComSucesso() {
        given().
                when().
                get("/v1/simulacoes").
                then().
                statusCode(HttpStatus.SC_OK).
                log().all();
    }

    @Test
    public void atualizarSimulacaoCpfJaExistente() {
        String nome = feku.name().fullName();
        String cpf = "66414919004";
        String email = feku.name().firstName() + gerador.nextInt(100) + "@test.com";
        int valor = gerador.nextInt(39000) + 1000;
        int parcelas = gerador.nextInt(46) + 2;
        boolean seguro = gerador.nextBoolean();

        Simulacao simulacao =
                Simulacao.builder().nome(nome).cpf(cpf).email(email).valor(valor).parcelas(parcelas).seguro(seguro).build();

        given().
                contentType(ContentType.JSON).
                pathParam("cpf", "17822386034").
                body(simulacao).
                when().
                put("/v1/simulacoes/{cpf}").
                then().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                body("mensagem", is("CPF duplicado"));
    }

    @Test
    public void atualizarSimulacaoExistenteComSucesso() {
        String nome = feku.name().fullName();
        String cpf = "0123456787" + gerador.nextInt(9);
        String email = feku.name().firstName() + gerador.nextInt(100) + "@test.com";
        int valor = gerador.nextInt(39000) + 1000;
        int parcelas = gerador.nextInt(46) + 2;
        boolean seguro = gerador.nextBoolean();

        Simulacao simulacao =
                Simulacao.builder().nome(nome).cpf(cpf).email(email).valor(valor).parcelas(parcelas).seguro(seguro).build();

        given().
                contentType(ContentType.JSON).
                pathParam("cpf", "66414919004").
                body(simulacao).
                when().
                put("/v1/simulacoes/{cpf}").
                then().
                statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void atualizarSimulacaoCpfInexistente() {
        String nome = feku.name().fullName();
        String cpf = "0123456787" + gerador.nextInt(9);
        String email = feku.name().firstName() + gerador.nextInt(100) + "@test.com";
        int valor = gerador.nextInt(39000) + 1000;
        int parcelas = gerador.nextInt(46) + 2;
        boolean seguro = gerador.nextBoolean();

        Simulacao simulacao =
                Simulacao.builder().nome(nome).cpf(cpf).email(email).valor(valor).parcelas(parcelas).seguro(seguro).build();

        given().
                contentType(ContentType.JSON).
                pathParam("cpf", "000000").
                body(simulacao).
                when().
                put("/v1/simulacoes/{cpf}").
                then().
                statusCode(HttpStatus.SC_NOT_FOUND).
                body("mensagem", is("CPF 000000 não encontrado"));
    }

    @Test
    public void inserirNovaSimulacaoComSucesso() {
        String nome = feku.name().fullName();
        String cpf = "0123456789" + gerador.nextInt(9);
        String email = feku.name().firstName() + gerador.nextInt(100) + "@test.com";
        int valor = gerador.nextInt(39000) + 1000;
        int parcelas = gerador.nextInt(46) + 2;
        boolean seguro = gerador.nextBoolean();

        Simulacao simulacao =
                Simulacao.builder().nome(nome).cpf(cpf).email(email).valor(valor).parcelas(parcelas).seguro(seguro).build();

        given().
                contentType(ContentType.JSON).
                body(simulacao).
                when().
                post("/v1/simulacoes").
                then().
                statusCode(HttpStatus.SC_CREATED).
                body(
                        "nome", is(simulacao.getNome()),
                        "cpf", is(simulacao.getCpf()),
                        "email", is(simulacao.getEmail()),
                        "valor", is(simulacao.getValor()),
                        "parcelas", is(simulacao.getParcelas()),
                        "seguro", is(simulacao.isSeguro())
                );
    }

    @Test
    public void inserirNovaSimulacaoComErro() {
        String nome = feku.name().fullName();
        String cpf = "0123456789" + gerador.nextInt(9);
        String email = feku.name().firstName() + gerador.nextInt(100) + "@test";
        int valor = gerador.nextInt(39000) + 1000;
        int parcelas = gerador.nextInt(46) + 2;
        boolean seguro = gerador.nextBoolean();

        Simulacao simulacao =
                Simulacao.builder().nome(nome).cpf(cpf).email(email).valor(valor).parcelas(parcelas).seguro(seguro).build();

        given().
                contentType(ContentType.JSON).
                body(simulacao).
                when().
                post("/v1/simulacoes").
                then().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                body(containsString("E-mail deve ser um e-mail válido"));
    }

    @Test
    public void inserirNovaSimulacaoCpfJaExistente() {
        String nome = feku.name().fullName();
        String cpf = "17822386034";
        String email = feku.name().firstName() + gerador.nextInt(100) + "@test.com";
        int valor = gerador.nextInt(39000) + 1000;
        int parcelas = gerador.nextInt(46) + 2;
        boolean seguro = gerador.nextBoolean();

        Simulacao simulacao =
                Simulacao.builder().nome(nome).cpf(cpf).email(email).valor(valor).parcelas(parcelas).seguro(seguro).build();

        given().
                contentType(ContentType.JSON).
                body(simulacao).
                when().
                post("/v1/simulacoes").
                then().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                body("mensagem", is("CPF duplicado"));
    }

    @Test
    public void consultarSimulacaoPeloCpf() {
        String cpf = "17822386034";

        given().
                pathParam("cpf", cpf).
                when().
                get("/v1/simulacoes/{cpf}").
                then().
                statusCode(HttpStatus.SC_OK).
                body(
                        "nome", equalTo("Deltrano"),
                        "email", equalTo("deltrano@gmail.com"),
                        "valor", equalTo(new BigDecimal("20000.00")),
                        "parcelas", equalTo(5),
                        "seguro", equalTo(false)
                );
    }

    @Test
    public void consultarSimulacaoPeloCpfNaoEncontrado() {
        String cpf = "01234567890";

        given().
                pathParam("cpf", cpf).
                when().
                get("/v1/simulacoes/{cpf}").
                then().
                statusCode(HttpStatus.SC_NOT_FOUND).
                body("mensagem", is("CPF " + cpf + " não encontrado"));
    }

    @Test
    public void removerSimulacaoComSucesso() {
        String id = "11";

        given().
            pathParam("id", id).
        when().
            delete("/v1/simulacoes/{id}").
        then().
            statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void removerSimulacaoNaoEncontrada() {
        int id = gerador.nextInt(100) + 500;

        given().
            pathParam("id", id).
        when().
            delete("/v1/simulacoes/{id}").
        then().
            statusCode(HttpStatus.SC_OK);
    }
}
