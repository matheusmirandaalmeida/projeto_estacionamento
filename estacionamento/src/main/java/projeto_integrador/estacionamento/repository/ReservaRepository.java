package projeto_integrador.estacionamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projeto_integrador.estacionamento.entity.Cliente;
import projeto_integrador.estacionamento.entity.Reserva;
import projeto_integrador.estacionamento.enuns.ReservaStatus;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByCliente(Long cliente);

    @Query("""
       select (count(r) > 0)
       from Reserva r
       where r.vaga.idVaga = :vagaId
         and r.status in :statusAtivos
         and r.horario.inicio <= :fim
         and r.horario.fim >= :inicio
       """)
    boolean existeConflito(@Param("vagaId") Long vagaId,
                           @Param("inicio") LocalDateTime inicio,
                           @Param("fim") LocalDateTime fim,
                           @Param("statusAtivo") Collection<ReservaStatus> statusAtivos);
}
