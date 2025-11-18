package projeto_integrador.estacionamento;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto_integrador.estacionamento.dto.RegistroRequest;
import projeto_integrador.estacionamento.entity.Cliente;
import projeto_integrador.estacionamento.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/api/registro")
// 🚨 Mantenha o CrossOrigin para que o Angular possa acessar
@CrossOrigin(origins = "http://localhost:4200")
public class RegistroController {

    private final ClienteService clienteService;

    // Injeção de dependência via construtor
    public RegistroController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Endpoint POST (Seu cadastro que já funciona)
    @PostMapping
    public ResponseEntity<?> registrarCliente(@RequestBody RegistroRequest request) {
        try {
            Cliente novoCliente = clienteService.registrarNovoCliente(request);
            // Retorna 201 Created com o corpo do cliente
            return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
        } catch (RuntimeException e) {
            // Simulação de erro de validação/duplicidade
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .header("X-Error-Message", e.getMessage())
                    .body(null);
        }
    }

    // 🛑 NOVO: Endpoint GET para visualizar os clientes salvos na memória
    @GetMapping
    public List<Cliente> listarClientes() {
        // Retorna a lista que está na memória RAM
        return clienteService.buscarTodosClientes();
    }
}