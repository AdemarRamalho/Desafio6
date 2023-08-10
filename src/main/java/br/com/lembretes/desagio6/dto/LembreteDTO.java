package br.com.lembretes.desagio6.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LembreteDTO {

    @NotBlank(message = "O texto do lembrete é obrigatorio")
    private String descricao;
}
