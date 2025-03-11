package br.com.fiap.spring_mvc.dto;
import br.com.fiap.spring_mvc.model.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmeRequest
    {
        @NotBlank(message = "{titulo.notBlank}")
        @Size(min = 3, max = 30, message = "{titulo.size}")
        private String titulo;

        @NotBlank(message = "{diretor.notBlank}")
        @Size(min = 3, max = 30, message = "{diretor.size}")
        private String diretor;

        @NotNull(message = "{categoria.notNull}")
        private Categoria categoria;

        @NotBlank(message = "{streaming.notBlank}")
        @Size(min = 3, max = 30, message = "{streaming.size}")
        private String streaming;

    }
    