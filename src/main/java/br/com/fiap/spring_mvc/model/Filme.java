package br.com.fiap.spring_mvc.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "filmes")
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "diretor")
    private String diretor;
    @Column(name = "categoria")
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    public Filme(){};

    public Filme(Long id, String titulo, String diretor, Categoria categoria) {
        this.id = id;
        this.titulo = titulo;
        this.diretor = diretor;
        this.categoria = categoria;
    }
}
