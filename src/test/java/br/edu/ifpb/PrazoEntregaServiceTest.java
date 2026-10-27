package br.edu.ifpb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PrazoEntregaServiceTest {

    private PrazoEntregaService service;

    @BeforeEach
    void setUp() {
        service = new PrazoEntregaService();
    }

    @Test
    void distancia_1_deveRetornarUmDia() {
        assertEquals(1, service.calcularPrazo(1));
    }

    @Test
    void distancia_50_deveRetornarUmDia() {
        assertEquals(1, service.calcularPrazo(50));
    }

    @Test
    void distancia_51_deveRetornarTresDias() {
        assertEquals(3, service.calcularPrazo(51));
    }

    @Test
    void distancia_200_deveRetornarTresDias() {
        assertEquals(3, service.calcularPrazo(200));
    }

    @Test
    void distancia_201_deveRetornarCincoDias() {
        assertEquals(5, service.calcularPrazo(201));
    }

    @Test
    void distanciaZero_deveLancarExcecao() {
        assertThrows(
                IllegalArgumentException.class,
                () -> service.calcularPrazo(0)
        );
    }

    @Test
    void distancia_51_expressa_deveRetornarUmDiaAMenos() {
        assertEquals(2, service.calcularPrazo(51, true));
    }

    @Test
    void distancia_201_expressa_deveRetornarQuatroDias() {
        assertEquals(4, service.calcularPrazo(201, true));
    }

    @Test
    void expressa_nuncaDeveFicarAbaixoDeUmDia() {
        assertEquals(1, service.calcularPrazo(10, true));
    }

    // 27/10/26 - Rodada 2

    @ParameterizedTest(name = "{0} km / Prime={1} -> R${2}")
    @CsvSource({
            "50, false, 10",
            "50, true, 5",
            "200, true, 10",
            "201, true, 20"
    })
    void calcularTaxa_deveConsiderarClientePrime(
            int distanciaKm,
            boolean clientePrime,
            int taxaEsperada) {

        assertEquals(
                taxaEsperada,
                service.calcularTaxa(distanciaKm, clientePrime)
        );
    }

    @Test
    void calcularTaxa_distanciaZero_deveLancarExcecao() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.calcularTaxa(0, true);
        });
    }
}