import { FilialService } from './../../service/filial/filial.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Filial } from '../filial';
import { Page } from './../../models/model';

@Component({
  selector: 'app-filial-lista',
  templateUrl: './filial-lista.component.html',
  styleUrls: ['./filial-lista.component.css']
})
export class FilialListaComponent implements OnInit {

  filiais: Filial[] = [];
  page: Page;
  filialSelecionada: Filial;
  mensagemSucesso: string;
  mensagemErro: string; 

  constructor(
    private service: FilialService,
    private router: Router){}

  ngOnInit(): void {
    this.service.getFiliais().subscribe(dados => {
      //tipagem de dados pra pegar o array
      this.page = dados;      
      this.filiais = this.page.content;
    })  
  }

  novoCadastro(){
    this.router.navigate(['filial-form'])
  }

  preparaDelecao(filial: Filial){
    this.filialSelecionada = filial;
  }

  deletarFilial(){
    this.service
      .deletar(this.filialSelecionada)
      .subscribe( 
        response => {
          this.mensagemSucesso = 'Filial deletada com sucesso!'
          this.ngOnInit();
        },
        erro => this.mensagemErro = 'Ocorreu um erro ao deletar a filial.'  
      )
  }

}
