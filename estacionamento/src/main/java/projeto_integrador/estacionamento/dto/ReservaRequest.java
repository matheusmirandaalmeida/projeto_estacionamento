package projeto_integrador.estacionamento.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ReservaRequest(@NotNull Long clienteId,
                             @NotNull Long vagaId,
                             @NotNull @Future LocalDateTime inicio,
                             @NotNull @Future LocalDateTime fim) {
}
