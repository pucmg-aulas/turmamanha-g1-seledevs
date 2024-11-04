package br.com.javaParking.model;

import br.com.javaParking.model.tiposVaga.Comum;
import br.com.javaParking.model.tiposVaga.Idoso;
import br.com.javaParking.model.tiposVaga.PCD;
import br.com.javaParking.model.tiposVaga.VIP;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.function.Executable;

class ParqueTest {

    private Parque parque;

    @BeforeEach
    void setUp() {
        // Inicializa um objeto Parque para ser usado nos testes, com valores válidos
        parque = new Parque(1, "Parque Central", 10, 2);
    }

    @Test
    void testConstructorWithValidValues() {
        // Verifica se o construtor inicializou o parque corretamente com valores válidos
        assertEquals(1, parque.getId());
        assertEquals("Parque Central", parque.getNomeParque());
        assertEquals(10, parque.getNumeroVagas());
        assertEquals(2, parque.getVagasPorFileira());
    }

    @Test
    void testConstructorWithInvalidValues() {
        // Verifica se uma exceção é lançada ao criar um parque com valores negativos
        assertThrows(RuntimeException.class, () -> new Parque(2, "Parque Inválido", -5, -2));
    }

    @Test
    void testGettersAndSetters() {
        // Testa os métodos de getter e setter para atributos
        parque.setNomeParque("Novo Parque");
        assertEquals("Novo Parque", parque.getNomeParque());

        parque.setNumeroVagas(20);
        assertEquals(20, parque.getNumeroVagas());

        parque.setVagasPorFileira(5);
        assertEquals(5, parque.getVagasPorFileira());
    }

    @Test
    void testValidarNumeroDeVagas() {
        // Valida o método validarNumeroDeVagas indiretamente pelo construtor
        Parque parqueValido = new Parque(3, "Parque Válido", 10, 5);
        assertDoesNotThrow(parqueValido::validarNumeroDeVagas);

        // Confirma que um número de vagas muito alto lança uma exceção
        assertThrows(RuntimeException.class, () -> new Parque(4, "Parque Invalido", 1000, 1));
    }

    @Test
    void testMontarVagas() {
        // Executa o método montarVagas e confirma que a quantidade de vagas é correta
        parque.montarVagas();
        
        List<Vaga> vagas = parque.listarVagas();
        assertNotNull(vagas);
        assertEquals(10, vagas.size(), "O número de vagas deve ser igual ao número especificado");

        // Opcional: Aqui você poderia adicionar verificações adicionais para tipos de vagas se desejar
    }

    @Test
    void testAtualizarNumeroVagasWithValidValue() {
        // Testa o método atualizarNumeroVagas com um valor válido
        assertTrue(parque.atualizarNumeroVagas(8));
        assertEquals(8, parque.getNumeroVagas());
    }

    @Test
    void testAtualizarNumeroVagasWithInvalidValue() {
        // Testa o método atualizarNumeroVagas com um valor inválido
        assertFalse(parque.atualizarNumeroVagas(-5));
    }
}
