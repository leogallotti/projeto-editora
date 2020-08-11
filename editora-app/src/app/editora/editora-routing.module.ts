import { NoticiaListaComponent } from './noticia-lista/noticia-lista.component';
import { FilialListaComponent } from './filial-lista/filial-lista.component';
import { NoticiaFormComponent } from './noticia-form/noticia-form.component';
import { FilialFormComponent } from './filial-form/filial-form.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

//rotas dos formulários
const routes: Routes = [  
  { path : 'filial-form', component: FilialFormComponent },
  { path : 'filial-form/:id', component: FilialFormComponent },
  { path : 'filiais-lista', component:  FilialListaComponent},
  
  { path : 'noticia-form', component: NoticiaFormComponent },  
  { path : 'noticia-form/:id', component: NoticiaFormComponent },  
  { path : 'noticias-lista', component:  NoticiaListaComponent}
     
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EditoraRoutingModule { }
