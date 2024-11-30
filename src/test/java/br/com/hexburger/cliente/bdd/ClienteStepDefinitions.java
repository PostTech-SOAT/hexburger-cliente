package br.com.hexburger.cliente.bdd;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

public class ClienteStepDefinitions {
    private static final String ENDPOINT_CLIENTES = "http://localhost:8080/v1/cliente";

    private Response response;
    private String payload;

    @Dado("o payload de cliente válido")
    public void oPayloadDeClienteValido() {
        payload = "{\"cpf\": \"12348888901\", \"nome\": \"João da Silva\", \"email\": \"joao@email.com\"}";
    }

    @Dado("o payload de cliente com CPF inválido")
    public void oPayloadDeClienteComCpfInvalido() {
        payload = "{\"cpf\": \"0\", \"nome\": \"João da Silva\", \"email\": \"joao@email.com\"}";
    }

    @Dado("o payload do cliente existente")
    public void oPayloadDoClienteExistente() {
        payload = "{\"cpf\": \"12345678900\", \"nome\": \"João da Silva\", \"email\": \"joao@email.com\"}";
    }

    @Dado("o payload de cliente com campos obrigatórios ausentes")
    public void oPayloadDeClienteComCamposObrigatoriosAusentes() {
        payload = "{\"cpf\": \"12348888901\"}";
    }

    @Dado("um cliente já existente no sistema")
    public void umClienteJaExistenteNoSistema() {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(payload)
                .when()
                .post(ENDPOINT_CLIENTES)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Quando("o cliente for criado")
    public void oClienteForCriado() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(payload)
                .when()
                .post(ENDPOINT_CLIENTES);
    }

    @Quando("o cliente for buscado pelo CPF")
    public void oClienteForBuscadoPeloCpf() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(ENDPOINT_CLIENTES + "/{cpf}", "12345678900");
    }

    @Quando("um cliente inexistente for buscado pelo CPF")
    public void umClienteInexistenteForBuscadoPeloCpf() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(ENDPOINT_CLIENTES + "/{cpf}", "12377778900");
    }

    @Então("a resposta deve ter o status {int} OK")
    public void aRespostaDeveTerOStatusOk() {
        response.then()
                .statusCode(HttpStatus.OK.value());
    }

    @Então("a resposta deve ter o status {int} Bad Request")
    public void aRespostaDeveTerOStatusBadRequest() {
        response.then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Então("a resposta deve ter o status {int} Conflict")
    public void aRespostaDeveTerOStatusConflict() {
        response.then()
                .statusCode(HttpStatus.CONFLICT.value());
    }

    @Então("a resposta deve ter o status {int} Not Found")
    public void aRespostaDeveTerOStatusNotFound() {
        response.then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}
