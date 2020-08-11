import { Filial } from '../../editora/filial';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FilialService {

  apiURL: string = environment.apiURLBase + '/api/filial';

  constructor( private http: HttpClient ) {}

  salvar(filial: Filial ) : Observable<Filial> {
    return this.http.post<Filial>( `${this.apiURL}` , filial);
  }

  atualizar(filial: Filial ) : Observable<any> {
    return this.http.put<Filial>(`${this.apiURL}?idFilial=${filial.id}` , filial);
  }

  deletar(filial: Filial) : Observable<any> {
    return this.http.delete<any>(`${this.apiURL}?idFilial=${filial.id}`);
  } 

 getFiliais() : Observable<any> {
    return this.http.get<any[]>(this.apiURL);
  }
  
  getFilialById(id: number) : Observable<Filial> {
    return this.http.get<any>(`${this.apiURL}/listaPorId?idFilial=${id}`);
  }
 
}
