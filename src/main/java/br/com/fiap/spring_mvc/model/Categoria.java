package br.com.fiap.spring_mvc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Categoria {
    ROMANCE("Romance"),
    FICCAO("Ficção"),
    TERROR("Terror"),
    DRAMA("Drama"),
    ACAO("Ação");

    private String descricao;
}
