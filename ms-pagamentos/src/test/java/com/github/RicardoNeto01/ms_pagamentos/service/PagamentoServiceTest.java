package com.github.RicardoNeto01.ms_pagamentos.service;

import com.github.RicardoNeto01.ms_pagamentos.dto.PagamentoDTO;
import com.github.RicardoNeto01.ms_pagamentos.entity.Pagamento;
import com.github.RicardoNeto01.ms_pagamentos.exceptions.ResourceNotFoundException;
import com.github.RicardoNeto01.ms_pagamentos.repository.PagamentoRepository;
import com.github.RicardoNeto01.ms_pagamentos.tests.Factory;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class PagamentoServiceTest {

    /*
    Referenciando PagamentoService
    @Autowired = sem injeção
    Mock = injentando.
    */
    @InjectMocks
    private PagamentoService service;
    @Mock
    private PagamentoRepository repository;

    private Long existingId;
    private Long nonExistingId;

    private Pagamento pagamento;
    private PagamentoDTO pagamentoDTO;

    @BeforeEach
    void setup() throws Exception{
        existingId = 1L;
        nonExistingId = 10L;

        // Precisa simular o comportamento do objeto mockado
        // Delete = quando id existe
        Mockito.when(repository.existsById(existingId)).thenReturn(true);
        // Delete = quando id não existe
        Mockito.when(repository.existsById(nonExistingId)).thenReturn(false);
        // Delete = primeiro caso, deleta.
        // não execute quando:
        Mockito.doNothing().when(repository).deleteById(existingId);

        pagamento = Factory.creatPagamento();
        pagamentoDTO = new PagamentoDTO(pagamento);
        Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(pagamento));
        Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

        Mockito.when(repository.save(any())).thenReturn(pagamento);
        Mockito.when(repository.getReferenceById(existingId)).thenReturn(pagamento);
        Mockito.when(repository.getReferenceById(nonExistingId)).thenThrow(EntityNotFoundException.class);
    }

    @Test
    @DisplayName("Delete deveria não fazer nada quando id existe")
    public void deleteShouldDoNothingWhenIdExists(){
        Assertions.assertDoesNotThrow(
                () -> {
                    service.deletePagamento(existingId);
                }
        );
    }
    @Test
    @DisplayName("delete deveria lançar exceção quando id não existe")
    public void deleteShouldResourceNotFoundExceptionWhenIdDoesNotExists(){
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> {
                    service.deletePagamento(nonExistingId);
                }
                );
    }
    @Test
    public void getByIdShouldReturnPagamentoDTOWhenIdExists() {
        // PagamentoDTO result = service.insert(pagamentoDTO);
        // nome da variável para ficar igual ao controller
        // não é obrigatório
        PagamentoDTO dto = service.getById(existingId);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(dto.getId(), existingId);
        Assertions.assertEquals(dto.getValor(), pagamento.getValor());
    }
    @Test
    public void getByIdShouldReturnResourceNotFoundExceptionWhenIdDoesNotExist() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.getById(nonExistingId);
        });
    }
    @Test
    public void createPagamentoShouldReturnPagamentoDTOWhenPagamentoIsCreated() {
        PagamentoDTO dto = service.createPagamento(pagamentoDTO);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(dto.getId(), pagamento.getId());
    }
    @Test
    public void updatePagamentoShouldReturnPagamentoDTOWhenIdExists() {
        PagamentoDTO dto = service.updateProduto(pagamento.getId(), pagamentoDTO);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(dto.getId(), existingId);
        Assertions.assertEquals(dto.getValor(), pagamento.getValor());
    }

    @Test
    public void updatePagamentoShouldReturnResourceNotFoundExceptionWhenIdDoesNotExist() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.updateProduto(nonExistingId, pagamentoDTO);
        });
    }
}
