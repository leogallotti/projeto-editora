package com.br.editora.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.editora.api.entities.Noticia;

/**
 * @author Leonardo Gallotti
 *
 */
public interface NoticiaRepository extends JpaRepository<Noticia, Integer> {

	Optional<Noticia> findByTituloIgnoreCase(String titulo);

}
