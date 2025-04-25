package com.github.RicardoNeto01.ms_pagamentos.dto;


import com.github.RicardoNeto01.ms_pagamentos.entity.Pagamento;
import com.github.RicardoNeto01.ms_pagamentos.entity.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter

public class PagamentoDTO {

    public PagamentoDTO(Pagamento entity) {
        id = entity.getId();
        valor = entity.getValor();
        nome = entity.getNome();
        numeroDoCartao = entity.getNumeroDoCartao();
        validade = entity.getValidade();
        codigoDeSeguranca = entity.getCodigoDeSeguranca();
        status = entity.getStatus();
        pedidoId = entity.getPedidoId();
        formaDePagamentoId = entity.getFormaDePagamentoId();
    }

    private Long id;
    @NotNull(message = "Campo obrigatório")
    @Positive(message = "O nome deve ser obrigatório")
    private BigDecimal valor;
    @Size(max = 100, message = "Nome deve ter até 100 caracteres")
    private String nome;
    @Size(max = 19, message = "Número do cartão deve ter no máximo 19 caracteres")
    private String numeroDoCartao;
    @Size(max = 5, message = "Validade do cartão deve ter 5 caracteres")
    private String validade;
    @Size(min = 3, max = 3, message = "Código de segurança deve ter 3 caracteres")
    private String codigoDeSeguranca;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    @NotNull(message = "Pedido ID é obrigatório")
    private long pedidoId;
    @NotNull(message = "Forma de pagamento ID é obrigatório")
    private long formaDePagamentoId;
}
