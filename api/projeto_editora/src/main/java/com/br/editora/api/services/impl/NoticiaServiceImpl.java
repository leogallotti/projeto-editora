package com.br.editora.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.br.editora.api.entities.Noticia;
import com.br.editora.api.repositories.NoticiaRepository;
import com.br.editora.api.services.NoticiaService;

/**
 * @author Leonardo Gallotti
 *
 */
@Service
public class NoticiaServiceImpl implements NoticiaService {

	private static final Logger log = LoggerFactory.getLogger(NoticiaServiceImpl.class);

	@Autowired
	private NoticiaRepository noticiaRepository;

	/**
	 * Método save faz tratamento para salvar ou ataulizar.
	*/
	@Override
	public Noticia salvar(Noticia noticia) {
		log.info("Salvando notícia: {}", noticia);
		return this.noticiaRepository.save(noticia);
	}

	@Override
	public Optional<Noticia> findById(Integer idNoticia) {
		log.info("Buscando uma notícia pelo ID: {}", idNoticia);
		return this.noticiaRepository.findById(idNoticia);
	}

	@Override
	public void remover(Noticia noticia) {
		log.info("Removendo notícia: {}", noticia);
		this.noticiaRepository.delete(noticia);
	}
	
	@Override
	public Page<Noticia> findAll(PageRequest page) {
		log.info("Buscando todas notícias.");
		return this.noticiaRepository.findAll(page);
	}

	@Override
	public Optional<Noticia> findByTitulo(String titulo) {
		log.info("Buscando notícia por título: {}", titulo);
		return this.noticiaRepository.findByTituloIgnoreCase(titulo);
	}


}
