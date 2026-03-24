package br.com.dogapi.tests;

import br.com.dogapi.client.DogApiClient;
import br.com.dogapi.config.BaseConfig;
import br.com.dogapi.model.RandomImageResponse;
import br.com.dogapi.utils.UrlValidator;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Dog API")
@Feature("Random Image")
public class RandomImageTest {

    private static DogApiClient dogApiClient;

    @BeforeAll
    static void setUp() {
        BaseConfig.setup();
        dogApiClient = new DogApiClient();
    }

    @Test
    @Story("Random image")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Deve retornar uma imagem aleatória com sucesso")
    void shouldReturnRandomImageSuccessfully() {
        Response response = buscarImagemAleatoria();
        RandomImageResponse body = response.as(RandomImageResponse.class);

        assertEquals(200, response.statusCode(), "Status code deveria ser 200");
        assertEquals("success", body.getStatus(), "Status da resposta deveria ser success");
        assertNotNull(body.getMessage(), "A URL da imagem não deveria ser nula");
        assertFalse(body.getMessage().trim().isEmpty(), "A URL da imagem não deveria estar vazia");
    }

    @Test
    @Story("Random image")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Deve retornar uma URL válida para imagem aleatória")
    void shouldReturnValidRandomImageUrl() {
        Response response = buscarImagemAleatoria();
        RandomImageResponse body = response.as(RandomImageResponse.class);

        assertEquals(200, response.statusCode(), "Status code deveria ser 200");
        assertTrue(UrlValidator.isValid(body.getMessage()), "A URL retornada deveria ser válida");
        assertTrue(body.getMessage().startsWith("https://"), "A URL deveria iniciar com https://");
    }

    @Test
    @Story("Random image")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Deve retornar estrutura válida para imagem aleatória")
    void shouldReturnValidRandomImageStructure() {
        Response response = buscarImagemAleatoria();
        RandomImageResponse body = response.as(RandomImageResponse.class);

        assertEquals(200, response.statusCode(), "Status code deveria ser 200");
        assertNotNull(body.getStatus(), "O campo status não deveria ser nulo");
        assertNotNull(body.getMessage(), "O campo message não deveria ser nulo");
        assertFalse(body.getStatus().trim().isEmpty(), "O campo status não deveria estar vazio");
        assertFalse(body.getMessage().trim().isEmpty(), "O campo message não deveria estar vazio");
    }

    private Response buscarImagemAleatoria() {
        return dogApiClient.getRandomImage();
    }
}