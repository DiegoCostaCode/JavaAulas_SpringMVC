package br.com.fiap.spring_mvc.controller;

import br.com.fiap.spring_mvc.dto.FilmeRequest;
import br.com.fiap.spring_mvc.dto.FilmeResponse;
import br.com.fiap.spring_mvc.model.Filme;
import br.com.fiap.spring_mvc.repository.FilmeRepository;
import br.com.fiap.spring_mvc.service.FilmeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/filme")
public class FilmeController {

    @Autowired
    FilmeService filmeService;

    @Autowired
    private FilmeRepository filmeRepository;

    // MVC

    @GetMapping()
    public String filmeCreate(Model model){
        model.addAttribute("filmeRequest", new FilmeRequest());
        return "createFilme";
    }

    @GetMapping(value = "/list")
    public String filmeGetAll(Model model){
        model.addAttribute("filmes", filmeService.findAllFilmes());
        return "listFilme";
    }

    @PostMapping(value = "/register")
    public String filmePost(@Valid  FilmeRequest filmeRequest, BindingResult result, Model model){

        if(result.hasErrors()){
            return "createFilme";
        }

        Filme filme = filmeService.saveFilme(filmeRequest);
        return "redirect:/filme/list";
    }

    @GetMapping(value = "/edit/{id}")
    public String filmeEdit(@PathVariable Long id, Model model){

        Filme filme = filmeService.findFilmeById(id);

        if(filme == null){
            return filmeGetAll(model); // Caso não encontre o filme, exibe a lista
        }

        FilmeRequest filmeRequest = new FilmeRequest();
        filmeRequest.setTitulo(filme.getTitulo());
        filmeRequest.setDiretor(filme.getDiretor());
        filmeRequest.setCategoria(filme.getCategoria());
        filmeRequest.setStreaming(filme.getStreaming());

        model.addAttribute("filmeRequest", filmeRequest);
        model.addAttribute("idFilme", id);

        return "updateFilme";
    }

    @GetMapping(value = "/delete/{id}")
    public String filmeDelete(@PathVariable Long id, Model model){
        filmeService.deletarFilmeById(id);
        return filmeGetAll(model);
    }
    
    @PostMapping(value = "/update/{id}")
    public String filmeUpdate(@PathVariable Long id, @Valid  FilmeRequest filmeRequest, Model model){
        Filme filme = filmeService.updateFilme(filmeRequest, id);
        return filmeGetAll(model);
    }

    // API REST

    @GetMapping(value = "/api/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FilmeResponse>> filmeGetAll()
    {
        List<Filme> filmes = filmeService.findAllFilmes();

        List<FilmeResponse> filmesResponse = filmes.stream().map(filme ->
        {
            return filmeService.filmeToResponse(filme);
        }).toList();

        return new ResponseEntity<>(filmesResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/api/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FilmeResponse> filmePost(@Valid @RequestBody FilmeRequest filmeRequest)
    {
        Filme filme = filmeService.saveFilme(filmeRequest);
        FilmeResponse filmeResponse = filmeService.filmeToResponse(filme);

        return new ResponseEntity<>(filmeResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/api/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FilmeResponse> filmeGetById(@PathVariable Long id)
    {
        Filme filme = filmeService.findFilmeById(id);

        if(filme == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        FilmeResponse filmeResponse = filmeService.filmeToResponse(filme);
        return new ResponseEntity<>(filmeResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> filmeDelete(@PathVariable Long id)
    {
        boolean sucesso = filmeService.deletarFilmeById(id);

        if(!sucesso)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/api/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FilmeResponse> filmeUpdate(@PathVariable Long id, @Valid @RequestBody FilmeRequest filmeRequest)
    {
        Filme filme = filmeService.updateFilme(filmeRequest, id);

        if(filme == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        FilmeResponse filmeResponse = filmeService.filmeToResponse(filme);
        return new ResponseEntity<>(filmeResponse, HttpStatus.OK);
    }
}
