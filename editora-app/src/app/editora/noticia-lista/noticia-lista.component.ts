import { Filial } from './../filial';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Noticia } from '../noticia';
import { Page } from './../../models/model';
import { NoticiaService } from 'src/app/service/noticia/noticia.service';

@Component({
  selector: 'app-noticia-lista',
  templateUrl: './noticia-lista.component.html',
  styleUrls: ['./noticia-lista.component.css']
})
export class NoticiaListaComponent implements OnInit {

  noticias: Noticia[] = [];
  page: Page;   
  noticiaSelecionada: Noticia;
  mensagemSucesso: string;
  mensagemErro: string; 
  filial : Filial;

  constructor(
    private service: NoticiaService,
    private router: Router
    ){ 
      this.filial = new Filial();
  } 

  ngOnInit(): void {
    this.service.getNoticias().subscribe(dados => {
      //tipagem de dados pra pegar o array
      this.page = dados;      
      this.noticias = this.page.content;
    })  
  }

  novoCadastro(){
    this.router.navigate(['noticia-form'])
  }

  preparaDelecao(noticia: Noticia){
    this.noticiaSelecionada = noticia;
  }

  deletarNoticia(){
    this.service
      .deletar(this.noticiaSelecionada)
      .subscribe( 
        response => {
          this.mensagemSucesso = 'Notícia deletada com sucesso!'
          this.ngOnInit();
        },
        erro => this.mensagemErro = 'Ocorreu um erro ao deletar a notícia.'  
      )
  }

}
