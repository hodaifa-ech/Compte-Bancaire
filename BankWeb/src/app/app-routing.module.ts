import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientComponent } from './component/client/client.component';
import { EmployeComponent } from './component/employe/employe.component';
import { GroupComponent } from './component/group/group.component';
import { CompteComponent } from './component/compte/compte.component';

const routes: Routes = [
  {path:'client',component: ClientComponent },
  {path:'employe',component:EmployeComponent},
  {path:'group',component:GroupComponent},
  {path:'compte',component:CompteComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
