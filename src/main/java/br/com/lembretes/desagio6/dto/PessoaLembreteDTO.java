package br.com.lembretes.desagio6.dto;

import br.com.lembretes.desagio6.entity.Lembrete;
import lombok.Data;

import java.util.List;


public class PessoaLembreteDTO {
    private Long id;
    private String nome;
    private String cpf;
    private int idade;
    private List<Lembrete> lembretes;

    public void setId(Long id) {
    }

    public void setNome(String nome) {
    }

    public void setCpf(String cpf) {
    }

    public void setIdade(int idade) {
    }

    public void setLembretes(List<Lembrete> lembrete) {
    }
}
