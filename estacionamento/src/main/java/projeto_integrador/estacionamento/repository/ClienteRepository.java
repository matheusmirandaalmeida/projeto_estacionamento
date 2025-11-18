package projeto_integrador.estacionamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto_integrador.estacionamento.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
