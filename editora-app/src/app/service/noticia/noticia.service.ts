import { Page } from './../../models/model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { Noticia } from 'src/app/editora/noticia';
import { environment } from 'src/environments/environment';
@Injectable({
  providedIn: 'root'
})
export class NoticiaService {

  apiURL: string = environment.apiURLBase + '/api/noticia';

  constructor( private http: HttpClient ) {}

  salvar(noticia: Noticia ) : Observable<Noticia> {
    return this.http.post<Noticia>( `${this.apiURL}` , noticia);
  }

  atualizar(noticia: Noticia ) : Observable<any> {
    return this.http.put<Noticia>(`${this.apiURL}?idNoticia=${noticia.id}` , noticia);
  }

  deletar(noticia: Noticia) : Observable<any> {
    return this.http.delete<any>(`${this.apiURL}?idNoticia=${noticia.id}`);
  } 

  getNoticias() : Observable<Page> {
    return this.http.get<Page>(this.apiURL);
  }  

  getNoticiaById(id: number) : Observable<Noticia> {
    return this.http.get<any>(`${this.apiURL}/listaPorId?idNoticia=${id}`);
  }
 
}
