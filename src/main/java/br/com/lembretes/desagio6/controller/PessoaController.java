package br.com.lembretes.desagio6.controller;

import br.com.lembretes.desagio6.dto.PessoaDTO;
import br.com.lembretes.desagio6.dto.PessoaLembreteDTO;
import br.com.lembretes.desagio6.entity.Pessoa;
import br.com.lembretes.desagio6.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    private final PessoaService pessoaService;

    @Autowired
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public ResponseEntity<List<PessoaLembreteDTO>> listarPessoasComLembretes() {
        List<Pessoa> pessoas = pessoaService.listarPessoas();
        List<PessoaLembreteDTO> pessoasLembretes = new ArrayList<>();

        for (Pessoa pessoa : pessoas) {
            PessoaLembreteDTO pessoaLembretes = new PessoaLembreteDTO();
            pessoaLembretes.setId(pessoa.getId());
            pessoaLembretes.setNome(pessoa.getNome());
            pessoaLembretes.setCpf(pessoa.getCpf());
            pessoaLembretes.setIdade(pessoa.getIdade());
            pessoaLembretes.setLembretes(pessoa.getLembrete());

            pessoasLembretes.add(pessoaLembretes);
        }

        return ResponseEntity.ok(pessoasLembretes);
    }

    @GetMapping("/{nome}")
    public ResponseEntity<PessoaLembreteDTO> buscarPessoaPorNome(@PathVariable String nome) {
        return pessoaService.buscarPessoaPornome(nome)
                .map(pessoa -> {
                    PessoaLembreteDTO pessoaComLembretes = new PessoaLembreteDTO();
                    pessoaComLembretes.setId(pessoa.getId());
                    pessoaComLembretes.setNome(pessoa.getNome());
                    pessoaComLembretes.setCpf(pessoa.getCpf());
                    pessoaComLembretes.setIdade(pessoa.getIdade());
                    pessoaComLembretes.setLembretes(pessoa.getLembrete());

                    return ResponseEntity.ok(pessoaComLembretes);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pessoa> salvarPessoa(@RequestBody PessoaDTO pessoaDTO) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setCpf(pessoaDTO.getCpf());
        pessoa.setIdade(pessoaDTO.getIdade());

        Pessoa pessoaSalva = pessoaService.salvarPessoa(pessoa);
        return ResponseEntity.ok(pessoaSalva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
        pessoaService.deletarPessoa(id);
        return ResponseEntity.noContent().build();
    }
}