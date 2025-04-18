package br.com.fiap.spring_mvc.service;

import br.com.fiap.spring_mvc.dto.FilmeRequest;
import br.com.fiap.spring_mvc.dto.FilmeResponse;
import br.com.fiap.spring_mvc.model.Filme;
import br.com.fiap.spring_mvc.repository.FilmeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmeService {

    @Autowired
    FilmeRepository filmeRepository;

    public Filme saveFilme(FilmeRequest filmeRequest)
    {
        Filme filme = requestToFilme(filmeRequest);
        return filmeRepository.save(filme);
    };

    public Filme updateFilme(FilmeRequest filmeRequest, long id)
    {
        Filme filme = findFilmeById(id);

        if(filme != null)
        {
            BeanUtils.copyProperties(filmeRequest, filme);
            return filmeRepository.save(filme);
        }

        return null;
    }

    public boolean deletarFilmeById(long id)
    {
        Filme filme = findFilmeById(id);

        if(filme == null)
        {
            return false;
        }

        filmeRepository.deleteById(id);
        return true;
    }

    public Filme requestToFilme(FilmeRequest filmeRequest)
    {
        Filme filme = new Filme();

        filme.setTitulo(filmeRequest.getTitulo());
        filme.setDiretor(filmeRequest.getDiretor());
        filme.setCategoria(filmeRequest.getCategoria());
        filme.setStreaming(filmeRequest.getStreaming());

        return filme;
    }

    public FilmeResponse filmeToResponse(Filme filme)
    {
        FilmeResponse filmeResponse = new FilmeResponse();

        filmeResponse.setId(filme.getId());
        filmeResponse.setTitulo(filme.getTitulo());
        filmeResponse.setDiretor(filme.getDiretor());
        filmeResponse.setCategoria(filme.getCategoria());
        filmeResponse.setStreaming(filme.getStreaming());

        return filmeResponse;
    }

    public List<Filme> findAllFilmes()
    {
        return filmeRepository.findAll();
    }

    public Filme findFilmeById(long id)
    {
        Optional<Filme> filme = filmeRepository.findById(id);

        return filme.orElse(null);
    }
}
