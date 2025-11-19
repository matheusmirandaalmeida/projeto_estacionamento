package projeto_integrador.estacionamento.service;

import org.springframework.stereotype.Service;
import projeto_integrador.estacionamento.dto.RegistroRequest;
import projeto_integrador.estacionamento.entity.Cliente;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ClienteService {

    private static final List<Cliente> CLIENTES_CADASTRADOS = new ArrayList<>();
    // Simulador de sequência de ID
    private static final AtomicLong ID_SEQUENCE = new AtomicLong(0);

    public Cliente registrarNovoCliente(RegistroRequest request) {

        // --- 1. SIMULAÇÃO DE CHECAGEM DE UNICIDADE ---
        if (CLIENTES_CADASTRADOS.stream().anyMatch(c -> c.getCpfCnpj().equals(request.getCpf()))) {
            throw new RuntimeException("CPF já cadastrado.");
        }
        if (CLIENTES_CADASTRADOS.stream().anyMatch(c -> c.getEmail().equals(request.getEmail()))) {
            throw new RuntimeException("Email já cadastrado.");
        }

        String senhaHasheada = "HASH_DE_" + request.getSenha().toUpperCase();

        String cpfLimpo = request.getCpf().replaceAll("[^0-9]", "");
        String telefoneLimpo = request.getTelefone().replaceAll("[^0-9]", "");

        // 2.1. Criar o Cliente
        Cliente novoCliente = Cliente.builder()
                .idCliente(ID_SEQUENCE.incrementAndGet()) // Simula a atribuição de ID
                .nome(request.getNome())
                .email(request.getEmail())
                .cpfCnpj(cpfLimpo)
                .telefone(telefoneLimpo)
                // O campo 'endereco' não existe na sua entidade Cliente.java (apenas nos DTOs do Angular)
                // Se ele fosse persistido, você precisaria adicioná-lo à entidade Cliente.java.
                .dataCadastro(LocalDateTime.now()) // Simula o @PrePersist
                .build();

        // 2.2. Simula o salvamento e adiciona à lista
        CLIENTES_CADASTRADOS.add(novoCliente);
        System.out.println("teste para pull");

        System.out.println("--- REGISTRO SIMULADO COM SUCESSO ---");
        System.out.println("ID: " + novoCliente.getIdCliente() + " | Cliente: " + novoCliente.getNome());
        System.out.println("-------------------------------------");
        System.out.println("teste");
        return novoCliente;
    }

    //Método para retornar a lista de todos os clientes salvos em memória
    public List<Cliente> buscarTodosClientes() {
        return CLIENTES_CADASTRADOS;
    }
}