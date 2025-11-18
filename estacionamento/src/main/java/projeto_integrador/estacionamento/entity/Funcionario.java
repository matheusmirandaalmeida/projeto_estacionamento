package projeto_integrador.estacionamento.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "funcionario")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcionario")
    private Long idFuncionario;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(nullable = false)
    private String cargo;

    @Column(nullable = false)
    private Boolean ativo = true;

    // Lado inverso; o dono é Usuario (tem a FK)
    @OneToOne(mappedBy = "funcionario", fetch = FetchType.LAZY)
    private Usuario usuario;
}
