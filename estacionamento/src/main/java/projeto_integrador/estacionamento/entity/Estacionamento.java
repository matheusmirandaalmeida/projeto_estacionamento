package projeto_integrador.estacionamento.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Data
public class Estacionamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nome;
    private String endereco;
    private String cnpj;
    private String cpf;
    private String telefone;
    private String horarioFuncionamento;
    private Double tarifa;

    @OneToMany(mappedBy = "estacionamento", cascade = CascadeType.ALL)
    private List<Vaga> vagas;

}
