package br.com.fiap.spring_mvc.controller;

import br.com.fiap.spring_mvc.dto.FilmeRequest;
import br.com.fiap.spring_mvc.model.Filme;
import br.com.fiap.spring_mvc.repository.FilmeRepository;
import br.com.fiap.spring_mvc.service.FilmeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/filme")
public class FilmeController {

    @Autowired
    FilmeService filmeService;

    @Autowired
    private FilmeRepository filmeRepository;

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
}
