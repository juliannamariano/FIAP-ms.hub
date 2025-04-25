package com.github.RicardoNeto01.ms_pagamentos.repository;

import com.github.RicardoNeto01.ms_pagamentos.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

}
