package com.github.RicardoNeto01.ms_pagamentos.repository;

import com.github.RicardoNeto01.ms_pagamentos.entity.Pagamento;
import com.github.RicardoNeto01.ms_pagamentos.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class PagamentoRepositoryTest {

    @Autowired
    private PagamentoRepository repository;

    private Long existingId;
    private Long nonExistingId;
    private Long countTotalPagamento;

    @BeforeEach
    void setup() throws Exception{
        //Arrange
        existingId = 1L;
        nonExistingId = 100L;
        countTotalPagamento = 3L;
    }


    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {
        //Act
        repository.deleteById(existingId);
        //Assert
        //Após deletar, tenta encontrar o objeto com o ID do repositório.
        Optional<Pagamento> result = repository.findById(existingId);
        //Testa se existe um obj dentro do optional
        /*
        Verifica se o objeto não está presente no repositório.
        Se result.isPresent() for false, significa que foi deletado com sucesso.
        */

        Assertions.assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Dado parâmetros validos e id Nulo "
            + "quando chamar CriarPagamento " +
             "então deve instanciar um pagamento")
    public void givenValidParamsAndIdIsNull_whenCallCreatePagamento_thenInstantiateAPagamento(){
        Pagamento pagamento = Factory.creatPagamento();
        pagamento.setId(null);
        pagamento = repository.save(pagamento);
        Assertions.assertNotNull(pagamento.getId());
        //Verifica se o Id gerado é o próximo
        Assertions.assertEquals(countTotalPagamento + 1 , pagamento.getId());
    }

    @Test
    @DisplayName("dado um ID existente quando chamar findById então deve retornar um Optional não vazio")
    public void givenAnExistingId_whenCallFindById_thenReturnNonEmptyOptional() {
        Optional<Pagamento> result = repository.findById(existingId);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    @DisplayName("dado um ID não existente quando chamar findById então deve retornar um Optional vazio")
    public void givenANonExistingId_whenCallFindById_thenReturnAnEmptyOptional() {
        Optional<Pagamento> result = repository.findById(nonExistingId);
        Assertions.assertFalse(result.isPresent());
        // ou
        Assertions.assertTrue(result.isEmpty());
    }
}
