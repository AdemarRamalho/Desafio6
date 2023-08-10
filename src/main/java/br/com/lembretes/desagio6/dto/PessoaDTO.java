package br.com.lembretes.desagio6.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class PessoaDTO {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @Min(value = 0,message = "A idade deve ser maior que 0")
    private int idade;

    @NotBlank(message = "O CPF é Obrigatorio")
    @CPF(message = "CPF inválido")
    private String cpf;

}
