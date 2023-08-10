package br.com.lembretes.desagio6.controller;

import br.com.lembretes.desagio6.dto.LembreteDTO;
import br.com.lembretes.desagio6.entity.Lembrete;
import br.com.lembretes.desagio6.service.LembreteService;
import br.com.lembretes.desagio6.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lembretes")
public class LembreteController {
    private final LembreteService lembreteService;
    private final PessoaService pessoaService;

    @Autowired
    public LembreteController(LembreteService lembreteService, PessoaService pessoaService) {
        this.lembreteService = lembreteService;
        this.pessoaService = pessoaService;
    }

    @PostMapping("/{pessoaId}")
    public ResponseEntity<Lembrete> adicionarLembrete(
            @PathVariable Long pessoaId, @Valid @RequestBody LembreteDTO lembreteDTO) {
        return pessoaService.buscarPessoaPorId(pessoaId)
                .map(pessoa -> {
                    Lembrete lembrete = new Lembrete();
                    lembrete.setDescricao(lembreteDTO.getDescricao());
                    lembrete.setPessoa(pessoa);

                    Lembrete lembreteSalvo = lembreteService.salvarLembrete(lembrete);
                    return ResponseEntity.ok(lembreteSalvo);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}