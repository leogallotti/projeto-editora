import { FilialService } from './service/filial/filial.service';
import { EditoraModule } from './editora/editora.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { TemplateModule } from './template/template.module';
import { HomeComponent } from './home/home.component'

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,    
    AppRoutingModule,
    TemplateModule,
    EditoraModule
  ],
  providers: [
    FilialService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
