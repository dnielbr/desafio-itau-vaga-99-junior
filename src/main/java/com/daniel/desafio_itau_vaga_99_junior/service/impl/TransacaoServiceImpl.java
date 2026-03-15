package com.daniel.desafio_itau_vaga_99_junior.service.impl;

import com.daniel.desafio_itau_vaga_99_junior.dto.StatisticsResponse;
import com.daniel.desafio_itau_vaga_99_junior.entites.Transacao;
import com.daniel.desafio_itau_vaga_99_junior.service.TransacaoService;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransacaoServiceImpl implements TransacaoService {

    private List<Transacao> transacoes = new ArrayList<>();

    @Override
    public boolean save(Transacao transacao) {
        if(transacao.isValid()) {
            transacoes.add(transacao);
            return true;
        }
        return false;
    }

    @Override
    public void delete() {
        transacoes.clear();
    }

    @Override
    public StatisticsResponse getStatistics() {

        OffsetDateTime inicio = OffsetDateTime.now().minusSeconds(60);

        DoubleSummaryStatistics statistics = this.transacoes.stream().
                filter((t) -> !t.getDataHora().isBefore(inicio))
                .collect(Collectors.summarizingDouble(Transacao::getValor));

        return new StatisticsResponse(
                statistics.getCount(),
                statistics.getSum(),
                statistics.getAverage(),
                statistics.getMin() > 0 ? statistics.getMin() : 0,
                statistics.getMax() > 0 ? statistics.getMax() : 0
        );
    }
}
