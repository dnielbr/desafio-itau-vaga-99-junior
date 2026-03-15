package com.daniel.desafio_itau_vaga_99_junior.service.impl;

import com.daniel.desafio_itau_vaga_99_junior.dto.StatisticsResponse;
import com.daniel.desafio_itau_vaga_99_junior.entites.Transacao;
import com.daniel.desafio_itau_vaga_99_junior.service.TransacaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransacaoServiceImpl implements TransacaoService {

    private Logger logger = LoggerFactory.getLogger(TransacaoServiceImpl.class);
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

        long start = System.nanoTime();
        DoubleSummaryStatistics statistics = this.transacoes.stream().
                filter((t) -> !t.getDataHora().isBefore(inicio))
                .collect(Collectors.summarizingDouble(Transacao::getValor));
        long duracao = System.nanoTime() - start;
        logger.info("Duração para executar a busca de estatísticas foi de: {}ms", duracao/1000000 );

        return new StatisticsResponse(
                statistics.getCount(),
                statistics.getSum(),
                statistics.getAverage(),
                statistics.getCount() > 0 ? statistics.getMin() : 0,
                statistics.getCount() > 0 ? statistics.getMax() : 0
        );
    }
}
