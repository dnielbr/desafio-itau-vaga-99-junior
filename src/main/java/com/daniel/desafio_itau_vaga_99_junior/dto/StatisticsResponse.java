package com.daniel.desafio_itau_vaga_99_junior.dto;

public record StatisticsResponse(
        long count,
        double sum,
        double avg,
        double min,
        double max
) {
}
