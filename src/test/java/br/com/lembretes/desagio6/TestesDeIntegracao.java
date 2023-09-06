package br.com.lembretes.desagio6;

import br.com.lembretes.desagio6.controller.LembreteController;
import br.com.lembretes.desagio6.controller.PessoaController;
import br.com.lembretes.desagio6.dto.LembreteDTO;
import br.com.lembretes.desagio6.dto.PessoaDTO;
import br.com.lembretes.desagio6.entity.Lembrete;
import br.com.lembretes.desagio6.entity.Pessoa;
import br.com.lembretes.desagio6.service.LembreteService;
import br.com.lembretes.desagio6.service.PessoaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@WebMvcTest({PessoaController.class, LembreteController.class})
@AutoConfigureMockMvc
public class TestesDeIntegracao{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PessoaService pessoaService;

    @MockBean
    private LembreteService lembreteService;

    private Pessoa pessoa;
    private Lembrete lembrete;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Ademar");
        pessoa.setCpf("190.132.187-84");
        pessoa.setIdade(23);

        lembrete = new Lembrete();
        lembrete.setId(1L);
        lembrete.setDescricao("Lembrete para Ademar");
        lembrete.setPessoa(pessoa);

        objectMapper = new ObjectMapper();
    }

    @Test
    public void testListarPessoasComLembretes() throws Exception {
        List<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(pessoa);

        when(pessoaService.listarPessoas()).thenReturn(pessoas);

        mockMvc.perform(MockMvcRequestBuilders.get("/pessoas"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("Ademar"));
    }

    @Test
    public void testBuscarPessoaPorNome() throws Exception {
        when(pessoaService.buscarPessoaPornome("Ademar")).thenReturn(Optional.of(pessoa));

        mockMvc.perform(MockMvcRequestBuilders.get("/pessoas/Ademar"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Ademar"));
    }

    @Test
    public void testSalvarPessoa() throws Exception {
        when(pessoaService.salvarPessoa(Mockito.any(Pessoa.class))).thenReturn(pessoa);

        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setNome("Gabriele");
        pessoaDTO.setCpf("9876543210");
        pessoaDTO.setIdade(25);

        mockMvc.perform(MockMvcRequestBuilders.post("/pessoas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pessoaDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Ademar"));
    }

    @Test
    public void testAdicionarLembrete() throws Exception {
        when(pessoaService.buscarPessoaPorId(1L)).thenReturn(Optional.of(pessoa));
        when(lembreteService.salvarLembrete(Mockito.any(Lembrete.class))).thenReturn(lembrete);

        LembreteDTO lembreteDTO = new LembreteDTO();
        lembreteDTO.setDescricao("Lembrete para Ademar");

        mockMvc.perform(MockMvcRequestBuilders.post("/lembretes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(lembreteDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.descricao").value("Lembrete para Ademar"));
    }
}