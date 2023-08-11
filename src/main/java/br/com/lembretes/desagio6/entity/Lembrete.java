package br.com.lembretes.desagio6.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;
@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_lembrete", schema = "public")
public class Lembrete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "descrição", nullable = false)
    @NotBlank(message = "O texto do lembrete é obrigatorio")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    @JsonIgnore
    private Pessoa pessoa;
}
