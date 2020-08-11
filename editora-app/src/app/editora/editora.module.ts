import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { EditoraRoutingModule } from './editora-routing.module';
import { FilialFormComponent } from './filial-form/filial-form.component';
import { NoticiaFormComponent } from './noticia-form/noticia-form.component';
import { FilialListaComponent } from './filial-lista/filial-lista.component';
import { NoticiaListaComponent } from './noticia-lista/noticia-lista.component';


@NgModule({
  declarations: [
    FilialFormComponent, 
    NoticiaFormComponent, 
    FilialListaComponent, 
    NoticiaListaComponent
  ],
  imports: [
    CommonModule,
    EditoraRoutingModule,
    FormsModule
  ], exports: [
    FilialFormComponent, 
    NoticiaFormComponent, 
    FilialListaComponent, 
    NoticiaListaComponent
  ]

})
export class EditoraModule { }
