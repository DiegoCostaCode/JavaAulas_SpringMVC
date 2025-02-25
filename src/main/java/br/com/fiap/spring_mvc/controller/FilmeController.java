package br.com.fiap.spring_mvc.controller;

import br.com.fiap.spring_mvc.dto.FilmeRequest;
import br.com.fiap.spring_mvc.model.Filme;
import br.com.fiap.spring_mvc.repository.FilmeRepository;
import br.com.fiap.spring_mvc.service.FilmeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/filme")
public class FilmeController {

    @Autowired
    FilmeService filmeService;
    @Autowired
    private FilmeRepository filmeRepository;

    @GetMapping(value = "/")
    public ModelAndView filmeCreate(){
        ModelAndView mv = new ModelAndView("createFilme");

        mv.addObject("filmeRequest", new FilmeRequest());

        return mv;
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView filmeEdit(@PathVariable Long id){

        Filme filme = filmeService.findFilmeById(id);

        if(filme == null){
            return filmeGetAll();
        }

        ModelAndView mv = new ModelAndView("updateFilme");

        mv.addObject("idFilme", id);
        mv.addObject("filmeRequest", filmeService.filmeToRequest(filme));

        return mv;
    }

    @PostMapping(value = "/register")
    public ModelAndView filmePost(@Valid @ModelAttribute FilmeRequest filmeRequest){

        Filme filme = filmeService.saveFilme(filmeRequest);

        ModelAndView mv = new ModelAndView("createFilme");

        mv.addObject("filmeRequest", new FilmeRequest());

        return filmeGetAll();
    }

    @GetMapping(value = "/list")
    public ModelAndView filmeGetAll(){
        ModelAndView mv = new ModelAndView("listFilme");

        mv.addObject("filmes", filmeService.findAllFilmes());

        return mv;
    }

    @GetMapping(value = "/delete/{id}")
    public ModelAndView filmeDelete(@PathVariable Long id){

        filmeService.deletarFilmeById(id);

        return filmeGetAll();
    }

}
