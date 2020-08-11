package com.br.editora.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
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

import com.br.editora.api.entities.Filial;
import com.br.editora.api.services.FilialService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author Leonardo Gallotti
 *
 */
@Api(value = "Conjunto de serviços da filial.")
@RestController
@RequestMapping("/api/filial")
public class FilialController {

	@Autowired
	private FilialService service;

	@ApiOperation("Criação da filial")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Filial salvar(@RequestBody @Valid @ApiParam(value = "Dados para criação da filial") Filial filial) {		
		return this.service.salvar(filial);
	}
	
	@ApiOperation("Atualização da filial.")
	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Filial atualizar(@RequestParam @ApiParam(value = "Dados para atualização da filial.") Integer idFilial,
			                 @RequestBody @Valid Filial filialAtualizada) {

		return this.service.findById(idFilial).map(filial -> {
			                                       filial.setNomeFilial(filialAtualizada.getNomeFilial());													
													return this.service.salvar(filial);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Filial não encontrada"));

	}
	
	@ApiOperation("Remove uma filial.")
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@RequestParam @ApiParam(value = "Removendo uma filial.") Integer idFilial){
		
		this.service.findById(idFilial)
		            .map( filial -> {
                          this.service.remover(filial);
                          return Void.TYPE;
            })
            .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Filial não encontrado") );
    }
	
	@ApiOperation("Consulta todas filiais.")
	@GetMapping
    public Page<Filial> buscarFilial(@RequestParam(value = "page", defaultValue = "0")   Integer pagina,
    						         @RequestParam(value = "size", defaultValue = "10")  Integer tamanhoPagina){
        Sort sort = Sort.by(Sort.Direction.ASC, "nomeFilial");
        PageRequest pageRequest = PageRequest.of(pagina, tamanhoPagina, sort);
        return service.findAll(pageRequest);
    }

	@ApiOperation("Consulta da filial por nome.")
	@GetMapping("/listaPorNome")	
	public Filial buscarFilialPorNome(@RequestParam @ApiParam(value = "Informe o nome da filial", required = true) String nome) {
		return this.service.findByNomeFilial(nome)
				.orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Filial não econtrada.")  );
	}
	
	@ApiOperation("Consulta da filial por id.")
	@GetMapping("/listaPorId")	
	public Filial buscarFilialPorId(@RequestParam @ApiParam(value = "Informe o id da filial", required = true) Integer idFilial) {
		return this.service.findById(idFilial)
				.orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Filial não econtrada.")  );
	}

}
