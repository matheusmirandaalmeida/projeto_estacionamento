package projeto_integrador.estacionamento.dto;

import projeto_integrador.estacionamento.enuns.ReservaStatus;

import java.time.LocalDateTime;

public record ReservaResponse(
        Long id,
        Long clienteId,
        Long vagaId,
        LocalDateTime inicio,
        LocalDateTime fim,
        ReservaStatus status) {
}
