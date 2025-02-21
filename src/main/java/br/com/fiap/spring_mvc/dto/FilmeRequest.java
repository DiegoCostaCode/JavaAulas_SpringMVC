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
        @NotBlank(message = "Titulo não pode ser nulo")
        @Size(min = 3, max = 30, message = "O título deve ter entre 3 e 30 caracteres")
        private String titulo;
        @Size(min = 3, max = 30, message = "O nome do diretor deve ter entre 3 e 30 caracteres")
        @NotBlank(message = "Diretor não pode ser nulo")
        private String diretor;
        @NotNull(message = "Categoria não pode ser nulo")
        Categoria categoria;
        @Size(min = 3, max = 30, message = "O título deve ter entre 3 e 20 caracteres")
        @NotBlank(message = "Streaming não pode ser nulo")
        private String streaming;
    }
    