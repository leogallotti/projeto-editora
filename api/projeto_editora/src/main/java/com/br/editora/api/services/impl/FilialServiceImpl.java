package com.br.editora.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.br.editora.api.entities.Filial;
import com.br.editora.api.repositories.FilialRepository;
import com.br.editora.api.services.FilialService;

/**
 * @author Leonardo Gallotti
 *
 */
@Service
public class FilialServiceImpl implements FilialService {

	private static final Logger log = LoggerFactory.getLogger(FilialServiceImpl.class);

	@Autowired
	private FilialRepository filialRepository;

	/**
	 * Método save faz tratamento para salvar ou ataulizar.
	*/
	@Override
	public Filial salvar(Filial filial) {
		log.info("Salvando filial: {}", filial);
		return this.filialRepository.save(filial);
	}

	@Override
	public void remover(Filial filial) {
		log.info("Removendo filial: {}", filial);
		this.filialRepository.delete(filial);
	}

	@Override
	public Optional<Filial> findByNomeFilial(String nomeFilial) {
		log.info("Buscando filial por nome: {}", nomeFilial);
		return this.filialRepository.findByNomeFilialIgnoreCase(nomeFilial);
	}

	@Override
	public Page<Filial> findAll(PageRequest page) {		
		log.info("Buscando todas filiais.");
		return  this.filialRepository.findAll(page);
	}

	@Override
	public Optional<Filial> findById(Integer idFilial) {
		log.info("Buscando filial por id: {}.", idFilial);
		return  this.filialRepository.findById(idFilial);
	}

}
