import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ClientComponent } from './component/client/client.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { EmployeComponent } from './component/employe/employe.component';
import { GroupComponent } from './component/group/group.component';
import { CompteComponent } from './component/compte/compte.component';

@NgModule({
  declarations: [
    AppComponent,
    ClientComponent,
    EmployeComponent,
    GroupComponent,
    CompteComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
   HttpClientModule,
   FontAwesomeModule,
   FormsModule


  ],
  providers: [
    provideClientHydration()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
