package br.com.dogapi.tests;

import br.com.dogapi.client.DogApiClient;
import br.com.dogapi.config.BaseConfig;
import br.com.dogapi.model.BreedImagesResponse;
import br.com.dogapi.utils.UrlValidator;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Dog API")
@Feature("Breed Images")
public class BreedImagesTest {

    private static final String VALID_BREED = "beagle";
    private static final String INVALID_BREED = "racainexistente123";

    private static DogApiClient dogApiClient;

    @BeforeAll
    static void setUp() {
        BaseConfig.setup();
        dogApiClient = new DogApiClient();
    }

    @Test
    @Story("Images by breed")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Deve retornar imagens da raça com sucesso")
    void shouldReturnImagesByBreedSuccessfully() {
        // Arrange / Act
        Response response = buscarImagensPorRaca(VALID_BREED);
        BreedImagesResponse body = response.as(BreedImagesResponse.class);

        // Assert
        assertEquals(200, response.statusCode(), "Status code deveria ser 200");
        assertEquals("success", body.getStatus(), "Status da resposta deveria ser success");
        assertNotNull(body.getMessage(), "A lista de imagens não deveria ser nula");
        assertFalse(body.getMessage().isEmpty(), "A lista de imagens não deveria estar vazia");
    }

    @Test
    @Story("Images by breed")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Deve retornar somente URLs válidas para a raça informada")
    void shouldReturnValidImageUrlsByBreed() {
        // Arrange / Act
        Response response = buscarImagensPorRaca(VALID_BREED);
        BreedImagesResponse body = response.as(BreedImagesResponse.class);

        // Assert
        assertEquals(200, response.statusCode(), "Status code deveria ser 200");
        assertNotNull(body.getMessage(), "A lista de imagens não deveria ser nula");

        validarUrlsDaRaca(body.getMessage(), VALID_BREED);
    }

    @Test
    @Story("Images by breed")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Deve retornar estrutura válida para imagens por raça")
    void shouldReturnValidBreedImagesStructure() {
        // Arrange / Act
        Response response = buscarImagensPorRaca(VALID_BREED);
        BreedImagesResponse body = response.as(BreedImagesResponse.class);

        // Assert
        assertEquals(200, response.statusCode(), "Status code deveria ser 200");
        assertNotNull(body.getStatus(), "O campo status não deveria ser nulo");
        assertNotNull(body.getMessage(), "O campo message não deveria ser nulo");

        body.getMessage().forEach(url -> {
            assertNotNull(url, "A URL da imagem não deveria ser nula");
            assertFalse(url.trim().isEmpty(), "A URL da imagem não deveria estar vazia");
        });
    }

    @Test
    @Story("Images by breed")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Deve retornar erro para raça inválida")
    void shouldReturnErrorForInvalidBreed() {
        // Arrange / Act
        Response response = buscarImagensPorRaca(INVALID_BREED);

        // Assert
        assertEquals(404, response.statusCode(), "Status code deveria ser 404");
        assertEquals("error", response.jsonPath().getString("status"), "Status da resposta deveria ser error");
        assertNotNull(response.jsonPath().getString("message"), "A mensagem de erro não deveria ser nula");
        assertFalse(response.jsonPath().getString("message").trim().isEmpty(), "A mensagem de erro não deveria estar vazia");
    }

    private Response buscarImagensPorRaca(String breed) {
        return dogApiClient.getImagesByBreed(breed);
    }

    private void validarUrlsDaRaca(List<String> urls, String breed) {
        urls.forEach(url -> {
            assertTrue(UrlValidator.isValid(url), "URL inválida encontrada: " + url);
            assertTrue(url.contains(breed), "A URL não parece pertencer à raça " + breed + ": " + url);
        });
    }
}