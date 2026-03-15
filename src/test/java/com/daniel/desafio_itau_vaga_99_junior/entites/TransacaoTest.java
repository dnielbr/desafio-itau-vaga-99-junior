package com.daniel.desafio_itau_vaga_99_junior.entites;

import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TransacaoTest {

    @Test
    void deveRetornarTrueQuandoTransacaoValida() {
        Transacao transacao = new Transacao(100.0, OffsetDateTime.now().minusSeconds(1));
        assertTrue(transacao.isValid());
    }

    @Test
    void deveRetornarTrueQuandoValorForZero() {
        Transacao transacao = new Transacao(0.0, OffsetDateTime.now().minusSeconds(1));
        assertTrue(transacao.isValid());
    }

    @Test
    void deveRetornarFalseQuandoValorForNegativo() {
        Transacao transacao = new Transacao(-0.01, OffsetDateTime.now().minusSeconds(1));
        assertFalse(transacao.isValid());
    }

    @Test
    void deveRetornarFalseQuandoDataHoraEstiverNoFuturo() {
        Transacao transacao = new Transacao(100.0, OffsetDateTime.now().plusSeconds(10));
        assertFalse(transacao.isValid());
    }

    @Test
    void deveRetornarFalseQuandoValorNegativoEDataNoFuturo() {
        Transacao transacao = new Transacao(-50.0, OffsetDateTime.now().plusSeconds(10));
        assertFalse(transacao.isValid());
    }
}