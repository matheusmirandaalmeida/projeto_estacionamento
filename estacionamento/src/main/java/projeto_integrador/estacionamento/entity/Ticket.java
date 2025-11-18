package projeto_integrador.estacionamento.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //colunas do banco de dados
    private Long idTicket;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private Double valor;
    private String formaPagamento;
    private String statusPagamento;
    private String statusTicket;
    private Double desconto;

    @ManyToOne
    @JoinColumn(name = "vaga_id")
    private Vaga vaga;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

}
