package br.com.fiap.spring_mvc.service;

import br.com.fiap.spring_mvc.dto.FilmeRequest;
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

    public void deletarFilmeById(long id)
    {
        filmeRepository.deleteById(id);
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

    public FilmeRequest filmeToRequest(Filme filme)
    {
        FilmeRequest filmeRequest = new FilmeRequest();

        BeanUtils.copyProperties(filme, filmeRequest);

        return filmeRequest;
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
