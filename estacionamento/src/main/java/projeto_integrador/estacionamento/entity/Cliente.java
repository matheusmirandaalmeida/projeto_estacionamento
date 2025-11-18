package projeto_integrador.estacionamento.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import jakarta.validation.constraints.Email;

@Entity
@Table(
        name = "cliente",
        indexes = {
                @Index(name = "idx_cliente_nome", columnList = "nome")
        }
)
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(nullable = false)
    private String nome;

    // CPF (11) ou CNPJ (14)
    @Column(name = "cpf_cnpj", nullable = false, unique = true, length = 18)
    private String cpfCnpj;

    @Column(nullable = false, unique = true, length = 15)
    private String telefone;

    @Email
    @Column(nullable = false, unique = true, length = 120)
    private String email;

    @Column(name = "data_cadastro", nullable = false, updatable = false)
    private LocalDateTime dataCadastro;

    @PrePersist
    void prePersist() {
        this.dataCadastro = LocalDateTime.now();
    }
}
