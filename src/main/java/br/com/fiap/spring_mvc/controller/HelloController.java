package br.com.fiap.spring_mvc.controller;

import br.com.fiap.spring_mvc.model.Categoria;
import br.com.fiap.spring_mvc.model.Filme;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Controller
public class HelloController {

    @GetMapping("/")
    public ModelAndView hello2(){

        ModelAndView mv = new ModelAndView("hello");
        mv.addObject("message","Helloooo");

        return mv;
    };

//    @GetMapping("/filme")
//    public ModelAndView filme(){
//
//        Filme filme = new Filme();
//
//        filme.setTitulo("Django");
//        filme.setDiretor("Tarantino");
//        filme.setCategoria(Categoria.DRAMA);
//
//        Filme filme2 = new Filme();
//
//        filme2.setTitulo("Bastardos Inglorios");
//        filme2.setDiretor("Michael");
//        filme2.setCategoria(Categoria.DRAMA);
//
//        List<Filme> filmes = Arrays.asList(filme,filme2);
//
//        ModelAndView mv = new ModelAndView("filme");
//
//        mv.addObject("filme",filme);
//        mv.addObject("filmes",filmes);
//
//        return mv;
//    };



}
