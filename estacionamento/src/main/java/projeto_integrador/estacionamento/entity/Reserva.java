package projeto_integrador.estacionamento.entity;

import jakarta.persistence.*;
import lombok.*;
import projeto_integrador.estacionamento.enuns.ReservaStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "reserva",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_reserva_vaga_horario", columnNames = {"vaga_id", "horario_id"})
        },
        indexes = {
                @Index(name = "idx_reserva_cliente", columnList = "cliente_id"),
                @Index(name = "idx_reserva_status", columnList = "status")
        }
)
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Long idReserva;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "horario_id", nullable = false)
    private Horario horario;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "vaga_id", nullable = false)
    private Vaga vaga;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ReservaStatus status;

    @Column(name = "valor_previsto", precision = 10, scale = 2)
    private BigDecimal valorPrevisto;

    @PrePersist
    void prePersist() {
        this.dataCriacao = LocalDateTime.now();
        if (this.status == null) this.status = ReservaStatus.PENDENTE;
    }
}
