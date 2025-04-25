package com.juliannamariano.ms_pedido.dto;

import com.juliannamariano.ms_pedido.entities.ItemDoPedido;
import com.juliannamariano.ms_pedido.entities.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ItemDoPedidoDTO {

    private Long id;

    @NotNull(message = "Quantidade requerido")
    @Positive(message = "Quantidade deve ser um número positivo")
    private Integer quantidade;

    @NotEmpty(message = "Descrição requerido")
    private String descricao;

    @NotNull(message = "Valor unitário requerido")
    @Positive(message = "Valor unitário deve ser um número positivo")
    private BigDecimal valorUnitario;


    public ItemDoPedidoDTO (ItemDoPedido entity){
        id = entity.getId();
        quantidade = entity.getQuantidade();
        descricao = entity.getDescricao();
        valorUnitario = entity.getValorUnitario();

    }

}
