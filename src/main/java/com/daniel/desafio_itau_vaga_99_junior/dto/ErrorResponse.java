package com.daniel.desafio_itau_vaga_99_junior.dto;

import java.time.OffsetDateTime;

public record ErrorResponse(
        int status,
        String erro,
        String mensagem,
        OffsetDateTime timestamp
) {
}
