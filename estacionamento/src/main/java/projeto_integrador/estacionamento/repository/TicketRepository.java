package projeto_integrador.estacionamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projeto_integrador.estacionamento.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
