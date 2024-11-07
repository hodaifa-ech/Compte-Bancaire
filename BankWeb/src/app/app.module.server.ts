import { NgModule } from '@angular/core';
import { ServerModule } from '@angular/platform-server';

import { AppModule } from './app.module';
import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

@NgModule({
  
  imports: [
    AppModule,
    ServerModule,
    ReactiveFormsModule,
    BrowserModule
  ],
  bootstrap: [AppComponent],
})
export class AppServerModule {}
