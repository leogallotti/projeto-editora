package com.br.editora.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.editora.api.entities.Filial;

/**
 * @author Leonardo Gallotti
 *
 */
public interface FilialRepository extends JpaRepository<Filial, Integer> {
	
	Optional<Filial> findByNomeFilialIgnoreCase(String nomeFilial);	
	
}
