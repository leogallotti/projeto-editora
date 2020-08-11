package com.br.editora.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.br.editora.api.entities.Noticia;

/**
 * @author Leonardo Gallotti
 *
 */
public interface NoticiaService {

	/**
	 * Cadastra ou atualiza uma notícia na base de dados.
	 * 
	 * @param noticia
	 * @return Noticia
	*/
	Noticia salvar(Noticia noticia);

	/**
	 * Remove uma notícia na base de dados.
	 * 
	 * @param noticia
	*/
	void remover(Noticia noticia);

	/**
	 * Retorna todas notícias.
	 * 
	 * @param page	 
	 * @return Page<Noticia>
	*/
	Page<Noticia> findAll(PageRequest page);	
	
	/**
	 * Retorna uma notícia pelo id.
	 * 
	 * @param idNoticia
	 * @return Optional<Noticia>
	*/
	Optional<Noticia> findById(Integer idNoticia);

	/**
	 * Retorna uma notícia pelo título.
	 * 
	 * @param titulo
	 * @return Optional<Noticia>
	*/
	Optional<Noticia> findByTitulo(String titulo);

}
