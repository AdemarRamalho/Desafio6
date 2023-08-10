package br.com.lembretes.desagio6.repository;

import br.com.lembretes.desagio6.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa,Long> {
}
