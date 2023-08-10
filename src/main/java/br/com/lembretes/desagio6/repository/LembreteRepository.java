package br.com.lembretes.desagio6.repository;

import br.com.lembretes.desagio6.entity.Lembrete;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LembreteRepository extends JpaRepository <Lembrete,Long>{
}
