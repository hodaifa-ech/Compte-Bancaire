import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientComponent } from './component/client/client.component';
import { EmployeComponent } from './component/employe/employe.component';
import { GroupComponent } from './component/group/group.component';
import { CompteComponent } from './component/compte/compte.component';
import { OperationComponent } from './component/operation/operation.component';
import { DashboardComponent } from './component/dashboard/dashboard.component';

const routes: Routes = [
  {path:'client',component: ClientComponent },
  {path:'employe',component:EmployeComponent},
  {path:'group',component:GroupComponent},
  {path:'compte',component:CompteComponent},
  {path:'operation',component:OperationComponent},
  {path:'',  component:DashboardComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
