package projeto_integrador.estacionamento.service;

import org.springframework.stereotype.Service;
import projeto_integrador.estacionamento.entity.Funcionario;
import projeto_integrador.estacionamento.entity.Usuario;
import projeto_integrador.estacionamento.repository.FuncionarioRepository;
import projeto_integrador.estacionamento.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final FuncionarioRepository funcionarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, FuncionarioRepository funcionarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    //  Listar todos
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    //  Buscar por ID
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));
    }

    // 🟣 3. Criar
    public Usuario criar(Usuario usuario, Long idFuncionario) {
        Funcionario funcionario = funcionarioRepository.findById(idFuncionario)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com ID: " + idFuncionario));

        usuario.setFuncionario(funcionario);
        return usuarioRepository.save(usuario);
    }

    // Atualizar
    public Usuario atualizar(Long id, Usuario usuarioAtualizado) {
        Usuario existente = buscarPorId(id);

        existente.setLogin(usuarioAtualizado.getLogin());
        existente.setSenhaHash(usuarioAtualizado.getSenhaHash());

        return usuarioRepository.save(existente);
    }


    public void deletar(Long id) {
        Usuario usuario = buscarPorId(id);
        usuarioRepository.delete(usuario);
    }
}
