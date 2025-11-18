package projeto_integrador.estacionamento.excecoesPersonalisadas;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) { super(message); }
}
