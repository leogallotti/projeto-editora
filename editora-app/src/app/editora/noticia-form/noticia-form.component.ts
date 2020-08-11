import { Filial } from './../filial';
import { Page } from './../../models/model';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params  } from '@angular/router';
import { Noticia } from '../noticia';
import { NoticiaService } from 'src/app/service/noticia/noticia.service';
import { FilialService } from 'src/app/service/filial/filial.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-noticia-form',
  templateUrl: './noticia-form.component.html',
  styleUrls: ['./noticia-form.component.css']
})
export class NoticiaFormComponent implements OnInit {
  filiais: Array<Filial> = [];
  page: Page;
  noticia: Noticia;  
  success: boolean = false;
  errors: String[];
  id: number;
 

  constructor(
    private noticiaService: NoticiaService,
    private filialService: FilialService,
    private router: Router,
    private activatedRoute: ActivatedRoute
    ){ 
    this.noticia = new Noticia();    
  }

  ngOnInit(): void {
    this.getFiliais();
    let params : Observable<Params> = this.activatedRoute.params
    params.subscribe( urlParams => {
      this.id = urlParams['id'];
      if(this.id){
        this.noticiaService
        .getNoticiaById(this.id)
        .subscribe( 
          response => this.noticia = response ,
          errorResponse => this.noticia = new Noticia()
        )
      }
    })    
  }

  getFiliais(){
    this.filialService.getFiliais().subscribe(dados => {
      //tipagem de dados pra pegar o array
      this.page = dados;      
      this.filiais = this.page.content;
    })   
  }

  voltarParaListagem(){
    this.router.navigate(['noticias-lista'])
  }

  onSubmit(){
    if(this.id){
      this.noticiaService
      .atualizar(this.noticia)
      .subscribe(response => {
        this.success = true;
        this.errors = null;
      }, errorResponse => {
           this.errors = ['Erro ao atualizar a notícia.']
         })
    }else{
      this.noticiaService
        .salvar(this.noticia)
        .subscribe( response => {
          this.success = true;
          this.errors = null;
          this.noticia = response;
        }, errorResponse => {
             this.success = false;
             this.errors = errorResponse.error.errors;
           })
    } 
  } 
  
}
