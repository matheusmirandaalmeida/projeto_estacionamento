package projeto_integrador.estacionamento.service;

import projeto_integrador.estacionamento.entity.Estacionamento;
import projeto_integrador.estacionamento.repository.EstacionamentoRepository;

import java.util.List;

public class EstacionamentoService {

    private final EstacionamentoRepository repository;

    public  EstacionamentoService(EstacionamentoRepository repository){
        this.repository = repository;
    }

    public List<Estacionamento> listarTodos(){
        return repository.findAll();
    }

    public Estacionamento buscarPorId(Long id){
        return repository.findById(id).orElseThrow(() ->new RuntimeException("Estacionamento não encontrado"));
    }
    public Estacionamento criar(Estacionamento estacionamento){
        return repository.save(estacionamento);
    }

    public Estacionamento atualizar(Long id, Estacionamento estacionamentoAtualizado){
        Estacionamento estacionamentoExistente = buscarPorId(id);

        estacionamentoExistente.setNome(estacionamentoAtualizado.getNome());
        estacionamentoExistente.setEndereco(estacionamentoAtualizado.getEndereco());
        estacionamentoExistente.setCnpj(estacionamentoAtualizado.getCnpj());
        estacionamentoExistente.setTelefone(estacionamentoAtualizado.getTelefone());
        estacionamentoExistente.setHorarioFuncionamento(estacionamentoAtualizado.getHorarioFuncionamento());
        estacionamentoExistente.setTarifa(estacionamentoAtualizado.getTarifa());

        return repository.save(estacionamentoExistente);
    }

    public void deletar(Long id){
        Estacionamento estacionamento = buscarPorId(id);
        repository.delete(estacionamento);
    }
}
