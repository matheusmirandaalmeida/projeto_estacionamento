package projeto_integrador.estacionamento.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVeiculo;
    private String modelo;
    private int ano;
    private String placa;
    private String montadora;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

}
