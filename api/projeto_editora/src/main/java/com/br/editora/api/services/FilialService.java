package com.br.editora.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.br.editora.api.entities.Filial;

/**
 * @author Leonardo Gallotti
 *
 */
public interface FilialService {

	/**
	 * Cadastra ou atualiza uma filial na base de dados.
	 * 
	 * @param filial
	 * @return Filial
	*/
	Filial salvar(Filial filial);

	/**
	 * Remove filial na base de dados.
	 * 
	 * @param filial
	*/
	void remover(Filial filial);
	
	/**
	 * Retorna uma filial pelo id.
	 * 
	 * @param idFilial
	 * @return Optional<Filial>
	*/
	Optional<Filial> findById(Integer idFilial);

	/**
	 * Retorna uma filial pelo nome.
	 * 
	 * @param nomeFilial
	 * @return Optional<Filial>
	*/
	Optional<Filial> findByNomeFilial(String nomeFilial);
	
	/**
	 * Retorna todas filiais com paginação.
	 * 
	 * @param page 	 
	 * @return Page<Filial>
	*/
	Page<Filial> findAll(PageRequest page);	

}
