package com.daniel.desafio_itau_vaga_99_junior.controller;

import com.daniel.desafio_itau_vaga_99_junior.dto.StatisticsResponse;
import com.daniel.desafio_itau_vaga_99_junior.entites.Transacao;
import com.daniel.desafio_itau_vaga_99_junior.service.TransacaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransacaoController {

    private final Logger logger = LoggerFactory.getLogger(TransacaoController.class);
    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping("/transacao")
    public ResponseEntity<?> criarTransacao(@RequestBody Transacao transacao){
        logger.info("Transacao iniciada com valor: {} e data: {}", transacao.getValor(), transacao.getDataHora());
        if(transacaoService.save(transacao)) {
            logger.info("Transacao criada com sucesso: {}", transacao);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        logger.warn("Erro ao processar a transacao: {}", transacao);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }

    @DeleteMapping("/transacao")
    public ResponseEntity<?> deletarTransacoes(){
        logger.info("Deletando todas as transações da memória.");
        transacaoService.delete();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/estatistica")
    public ResponseEntity<StatisticsResponse> buscarEstatisticas(){
        logger.info("Buscando estatísticas das transações realizadas nos ultimos 1 minuto");
        StatisticsResponse response = transacaoService.getStatistics();
        logger.info("Totais de transações feitas nos ultimos 1 minuto: {}", response.count());
        return ResponseEntity.ok(response);
    }

}
