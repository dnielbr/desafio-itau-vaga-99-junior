package com.daniel.desafio_itau_vaga_99_junior.service;

import com.daniel.desafio_itau_vaga_99_junior.dto.StatisticsResponse;
import com.daniel.desafio_itau_vaga_99_junior.entites.Transacao;

public interface TransacaoService {
    boolean save(Transacao transacao);
    void delete();
    StatisticsResponse getStatistics();
}
