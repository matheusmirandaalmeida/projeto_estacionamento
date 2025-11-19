package projeto_integrador.estacionamento.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto_integrador.estacionamento.entity.Horario;

import java.util.List;

public class HorarioController {
    private final HorarioService horarioService;

    @GetMapping
    public ResponseEntity<List<Horario>> listarTodos() {
        return ResponseEntity.ok(horarioService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<Horario> criar(@RequestBody Horario horario) {
        return ResponseEntity.ok(horarioService.salvar(horario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        horarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
