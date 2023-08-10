package br.com.lembretes.desagio6.service;

import br.com.lembretes.desagio6.entity.Lembrete;
import br.com.lembretes.desagio6.repository.LembreteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LembreteService {
    private final LembreteRepository lembreteRepository;

    @Autowired
    public LembreteService(LembreteRepository lembreteRepository) {
        this.lembreteRepository = lembreteRepository;
    }

    public Lembrete salvarLembrete(Lembrete lembrete) {
        return lembreteRepository.save(lembrete);
    }
}