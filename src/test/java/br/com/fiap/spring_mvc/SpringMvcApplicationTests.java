package br.com.fiap.spring_mvc;

import br.com.fiap.spring_mvc.controller.FilmeController;
import br.com.fiap.spring_mvc.service.FilmeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SpringMvcApplicationTests {

	@Autowired
	private FilmeService filmeService;

	@Autowired
	private FilmeController filmeController;

	@Test
	void contextLoads() {
		// Verifica se o contexto da aplicação foi carregado corretamente
		assertNotNull(filmeService);
		assertNotNull(filmeController);
	}

}
