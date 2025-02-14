package br.com.fiap.spring_mvc.controller;

import br.com.fiap.spring_mvc.model.Categoria;
import br.com.fiap.spring_mvc.model.Filme;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("message", "Hello World!");
        //--->Para mostrar no front, você vai acessar através da message
        return "hello"; //---> Se refere ao nome do template no HTML, ex: hello.hmtl
    };


    @GetMapping("/hello2")
    public ModelAndView hello2(){

        ModelAndView mv = new ModelAndView("hello");
        mv.addObject("message","Helloooo");

        return mv;
    };

    @GetMapping("/filme")
    public ModelAndView filme(){

        Filme filme = new Filme();

        filme.setTitulo("Django");
        filme.setDiretor("Tarantino");
        filme.setCategoria(Categoria.DRAMA);

        ModelAndView mv = new ModelAndView("filme");
        mv.addObject("filme",filme);

        return mv;
    };



}
