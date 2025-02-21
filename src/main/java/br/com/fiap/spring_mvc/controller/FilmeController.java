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

    @GetMapping(value = "/")
    public ModelAndView filmeCreate(){
        ModelAndView mv = new ModelAndView("createFilme");

        mv.addObject("filmeRequest", new FilmeRequest());

        return mv;
    }

    @PostMapping(value = "/cadastro")
    public ModelAndView filmeCreate(@Valid @ModelAttribute FilmeRequest filmeRequest){

        Filme filme = filmeService.saveFilme(filmeRequest);

        ModelAndView mv = new ModelAndView("createFilme");

        mv.addObject("filmeRequest", new FilmeRequest());

        return filmeCreate();
    }


}
