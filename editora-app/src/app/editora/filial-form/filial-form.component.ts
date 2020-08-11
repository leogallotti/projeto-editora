import { FilialService } from './../../service/filial/filial.service';
import { Component, OnInit } from '@angular/core';
import { Filial } from '../filial';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-filial-form',
  templateUrl: './filial-form.component.html',
  styleUrls: ['./filial-form.component.css']
})
export class FilialFormComponent implements OnInit {
  
  filial: Filial;
  success: boolean = false;
  errors: String[];
  id: number;

  constructor(
    private service: FilialService,
    private router: Router,
    private activatedRoute: ActivatedRoute
    ){ 
    this.filial = new Filial();
  }

  ngOnInit(): void {
    let params : Observable<Params> = this.activatedRoute.params
    params.subscribe( urlParams => {
      this.id = urlParams['id'];
      if(this.id){
        this.service
        .getFilialById(this.id)
        .subscribe( 
          response => this.filial = response ,
          errorResponse => this.filial = new Filial()
        )
      }
    })
  }

  voltarParaListagem(){
    this.router.navigate(['filiais-lista'])
  }

  onSubmit(){
    if(this.id){
      this.service
      .atualizar(this.filial)
        .subscribe(response => {
          this.success = true;
          this.errors = null;
      } , errorResponse => {
            this.errors = ['Erro ao atualizar a filial.']
         })
    }else{
      this.service
        .salvar(this.filial)
          .subscribe( response => {
            this.success = true;
            this.errors = null;
            this.filial = response;
          } , errorResponse => {
                this.success = false;
                this.errors = errorResponse.error.errors;
         })
    }

  }

}
