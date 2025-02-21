package br.com.fiap.spring_mvc.service;

import br.com.fiap.spring_mvc.dto.FilmeRequest;
import br.com.fiap.spring_mvc.model.Filme;
import br.com.fiap.spring_mvc.repository.FilmeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmeService {

    @Autowired
    FilmeRepository filmeRepository;

    public Filme saveFilme(FilmeRequest filmeRequest)
    {
        Filme filme = requestToFilme(filmeRequest);
        return filmeRepository.save(filme);
    };

    public Filme requestToFilme(FilmeRequest filmeRequest)
    {
        Filme filme = new Filme();


        filme.setTitulo(filmeRequest.getTitulo());
        filme.setDiretor(filmeRequest.getDiretor());
        filme.setCategoria(filmeRequest.getCategoria());
        filme.setStreaming(filmeRequest.getStreaming());

        return filme;
    }
}
