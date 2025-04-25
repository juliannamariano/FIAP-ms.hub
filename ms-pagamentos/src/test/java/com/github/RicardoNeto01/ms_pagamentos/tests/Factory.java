package com.github.RicardoNeto01.ms_pagamentos.tests;

import com.github.RicardoNeto01.ms_pagamentos.entity.Pagamento;
import com.github.RicardoNeto01.ms_pagamentos.entity.Status;

import java.math.BigDecimal;

public class Factory {

    public static Pagamento creatPagamento(){

        return new Pagamento(1L, BigDecimal.valueOf(32.25),"Jon Snow", "23654789645", "07/32", "585", Status.CRIADO, 1L, 2L);
    }

}
