package projeto_integrador.estacionamento.excecoesPersonalisadas;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
