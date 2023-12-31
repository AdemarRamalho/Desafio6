package br.com.lembretes.desagio6.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.ArrayList;
import java.util.List;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é Obrigatório")
    @Column(name = "nome")
    private String nome;

    @Min(value = 0,message = "A idade deve ser maior que 0")
    @Column(name = "idade")
    private int idade;

    @NotBlank(message = "O CPF é Obrigatorio")
    @CPF(message = "CPF inválido")
    @Column(name = "cpf")
    private String cpf;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Lembrete> lembrete = new ArrayList<>();

    public Pessoa(Long id,String nome,String cpf, int idade) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
    }

}
