package projeto_integrador.estacionamento.service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import projeto_integrador.estacionamento.enuns.ReservaStatus;
import projeto_integrador.estacionamento.enuns.VagaStatus;
import projeto_integrador.estacionamento.entity.Cliente;
import projeto_integrador.estacionamento.entity.Horario;
import projeto_integrador.estacionamento.entity.Reserva;
import projeto_integrador.estacionamento.entity.Vaga;
import projeto_integrador.estacionamento.excecoesPersonalisadas.ConflictException;
import projeto_integrador.estacionamento.excecoesPersonalisadas.NotFoundException;
import projeto_integrador.estacionamento.repository.ClienteRepository;
import projeto_integrador.estacionamento.repository.ReservaRepository;
import projeto_integrador.estacionamento.repository.VagaRepository;
import projeto_integrador.estacionamento.repository.HorarioRepository;

import java.util.List;

@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final VagaRepository vagaRepository;
    private final ClienteRepository clienteRepository;
    private final HorarioRepository horarioRepository; // <— adicionado

    @Transactional
    public Reserva criar(Reserva req) {
        if (req.inicio() == null || req.fim() == null)
            throw new ConflictException("Início e fim são obrigatórios");
        if (!req.fim().isAfter(req.inicio()))
            throw new ConflictException("Fim deve ser após o início");

        Cliente cliente = clienteRepository.findById(req.clienteId())
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
        Vaga vaga = vagaRepository.findById(req.vagaId())
                .orElseThrow(() -> new NotFoundException("Vaga não encontrada"));

        var bloqueantes = List.of(ReservaStatus.PENDENTE, ReservaStatus.CONFIRMADO);
        boolean conflito = reservaRepository.existeConflito(
                vaga.getIdVaga(), req.inicio(), req.fim(), bloqueantes);

        if (conflito) throw new ConflictException("Vaga indisponível no intervalo informado");
        if (vaga.getStatus() == VagaStatus.OCUPADA)
            throw new ConflictException("Vaga ocupada por ticket em aberto");

        Horario horario = horarioRepository.findByInicioAndFim(req.inicio(), req.fim())
                .orElseGet(() -> horarioRepository.save(
                        Horario.builder().inicio(req.inicio()).fim(req.fim()).build()));

        Reserva reserva = Reserva.builder()
                .cliente(cliente)
                .vaga(vaga)
                .horario(horario)
                .status(ReservaStatus.CONFIRMADO)
                .build();

        if (reserva.getStatus() == ReservaStatus.CONFIRMADO)
            vaga.setStatus(VagaStatus.RESERVADA);

        return reservaRepository.save(reserva);
    }
}
