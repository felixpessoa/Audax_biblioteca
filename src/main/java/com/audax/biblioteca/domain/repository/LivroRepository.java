package com.audax.biblioteca.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.audax.biblioteca.domain.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {

	Optional<Livro> findByNome(String nome);

}
