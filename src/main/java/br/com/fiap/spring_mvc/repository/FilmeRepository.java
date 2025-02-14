package br.com.fiap.spring_mvc.repository;

import br.com.fiap.spring_mvc.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {
}
