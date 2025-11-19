package projeto_integrador.estacionamento.service;

import org.springframework.stereotype.Service;
import projeto_integrador.estacionamento.entity.Funcionario;
import projeto_integrador.estacionamento.excecoesPersonalisadas.ResourceNotFoundException;
import projeto_integrador.estacionamento.repository.FuncionarioRepository;

import java.util.List;

@Service
public class FuncionarioService{
    private final  FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository){
        this.repository = repository;
    }

    public List<Funcionario> listarTodos(){
        return repository.findAll();
    }

    public Funcionario buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado"));
    }

    public Funcionario criar(Funcionario funcionario){
        return repository.save(funcionario);
    }

    public Funcionario atualizar (Long id, Funcionario funcionarioAtualizado){
        Funcionario funcionarioExistente = buscarPorId(id);

        funcionarioExistente.setNome(funcionarioAtualizado.getNome());
        funcionarioExistente.setCpf(funcionarioAtualizado.getCpf());
        funcionarioExistente.setCargo(funcionarioAtualizado.getCargo());
        funcionarioExistente.setAtivo(funcionarioAtualizado.getAtivo());

        return repository.save(funcionarioExistente);
    }

    public void deletar(Long id){
        Funcionario funcionario = buscarPorId(id);
        repository.delete(funcionario);
    }
}
