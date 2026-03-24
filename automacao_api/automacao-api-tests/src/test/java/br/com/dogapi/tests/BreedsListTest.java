package br.com.dogapi.tests;

import br.com.dogapi.client.DogApiClient;
import br.com.dogapi.config.BaseConfig;
import br.com.dogapi.model.BreedsListResponse;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Dog API")
@Feature("Breeds")
public class BreedsListTest {

    private static DogApiClient dogApiClient;

    @BeforeAll
    static void setUp() {
        BaseConfig.setup();
        dogApiClient = new DogApiClient();
    }

    @Test
    @Story("List all breeds")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Deve listar todas as raças com status success")
    void shouldListAllBreedsSuccessfully() {
        Response response = buscarTodasAsRacas();
        BreedsListResponse body = response.as(BreedsListResponse.class);

        assertEquals(200, response.statusCode(), "Status code deveria ser 200");
        assertEquals("success", body.getStatus(), "Status da resposta deveria ser success");
        assertNotNull(body.getMessage(), "O campo message não deveria ser nulo");
        assertFalse(body.getMessage().isEmpty(), "A lista de raças não deveria estar vazia");
        assertTrue(body.getMessage().containsKey("hound"), "A raça hound deveria existir na resposta");
    }

    @Test
    @Story("List all breeds")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Deve retornar estrutura válida para lista de raças")
    void shouldReturnValidBreedsStructure() {
        Response response = buscarTodasAsRacas();
        BreedsListResponse body = response.as(BreedsListResponse.class);

        assertEquals(200, response.statusCode(), "Status code deveria ser 200");
        assertNotNull(body.getStatus(), "O campo status não deveria ser nulo");
        assertNotNull(body.getMessage(), "O campo message não deveria ser nulo");

        validarEstruturaDasRacas(body.getMessage());
    }

    @Test
    @Story("List all breeds")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Deve retornar sub-raças como lista, mesmo quando vazia")
    void shouldReturnSubBreedsAsList() {
        Response response = buscarTodasAsRacas();
        BreedsListResponse body = response.as(BreedsListResponse.class);

        assertEquals(200, response.statusCode(), "Status code deveria ser 200");

        body.getMessage().forEach((breed, subBreeds) -> {
            assertNotNull(subBreeds, "A lista de sub-raças da raça " + breed + " não deveria ser nula");
            assertInstanceOf(List.class, subBreeds, "O valor de sub-raças deveria ser uma lista");
        });
    }

    private Response buscarTodasAsRacas() {
        return dogApiClient.getAllBreeds();
    }

    private void validarEstruturaDasRacas(Map<String, List<String>> breeds) {
        breeds.forEach((breed, subBreeds) -> {
            assertNotNull(breed, "O nome da raça não deveria ser nulo");
            assertFalse(breed.trim().isEmpty(), "O nome da raça não deveria estar vazio");
            assertNotNull(subBreeds, "A lista de sub-raças não deveria ser nula");
        });
    }
}