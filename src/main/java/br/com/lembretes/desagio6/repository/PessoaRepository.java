package br.com.lembretes.desagio6.repository;

import br.com.lembretes.desagio6.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa,Long> {
    Optional<Pessoa> findByNome(String nome);
}
