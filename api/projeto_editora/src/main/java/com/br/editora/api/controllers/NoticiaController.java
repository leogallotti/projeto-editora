package com.br.editora.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.br.editora.api.entities.Noticia;
import com.br.editora.api.services.NoticiaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author Leonardo Gallotti
 *
 */
@Api(value = "Conjunto de serviços da notícia.")
@RestController
@RequestMapping("/api/noticia")
public class NoticiaController {

	@Autowired
	private NoticiaService service;

	
	@ApiOperation("Criação da notícia.")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Noticia salvar(@RequestBody @Valid @ApiParam(value = "Dados para criação da notícia.") Noticia noticia) {		
		return this.service.salvar(noticia);
	}

	@ApiOperation("Atualização da notícia.")
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Noticia atualizar(@RequestParam @ApiParam(value = "Dados para atualização da notícia.") Integer idNoticia,
			                 @RequestBody @Valid Noticia noticiaAtualizada) {

		return this.service.findById(idNoticia).map(noticia -> {
													noticia.setTitulo(noticiaAtualizada.getTitulo());
													noticia.setConteudo(noticiaAtualizada.getConteudo());
													noticia.setFilial(noticiaAtualizada.getFilial());
													return this.service.salvar(noticia);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Notícia não encontrada"));

	}
	
	@ApiOperation("Remove uma notícia.")
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@RequestParam @ApiParam(value = "Removendo uma notícia.") Integer idNoticia){
		
		this.service.findById(idNoticia)
		            .map( noticia -> {
                          this.service.remover(noticia);
                          return Void.TYPE;
            })
            .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Notícia não encontrado") );
    }
	
	@ApiOperation("Consulta todas notícias.")
	@GetMapping
    public Page<Noticia> BuscarNoticia(@RequestParam(value = "page", defaultValue = "0")   Integer pagina,
    						           @RequestParam(value = "size", defaultValue = "10")  Integer tamanhoPagina){
        Sort sort = Sort.by(Sort.Direction.ASC, "titulo");
        PageRequest pageRequest = PageRequest.of(pagina, tamanhoPagina, sort);
        return service.findAll(pageRequest);
    }
	
	
	@ApiOperation("Consulta notícia por título.")
	@GetMapping("/listaPorTitulo")
	public Noticia BuscarNoticiaPorTitulo(@RequestParam @ApiParam(value = "Informe o título da notícia.", required = true) String titulo) {
		return this.service.findByTitulo(titulo)
				           .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Notícia não econtrada."));
	}
	
	@ApiOperation("Consulta notícia por id.")
	@GetMapping("/listaPorId")
	public Noticia BuscarNoticiaPorId(@RequestParam @ApiParam(value = "Informe o id da notícia.", required = true) Integer idNoticia) {
		return this.service.findById(idNoticia)
				           .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Notícia não econtrada."));
	}
	
	
}
