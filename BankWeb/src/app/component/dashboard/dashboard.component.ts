import { Component } from '@angular/core';
import { DashboardService } from '../../service/dashboardService/dashboard.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent {
  operationCount?: number;
  groupCount?: number;
  employerCount?: number;
  compteCount?: number;
  clientCount?: number;
  hover: boolean = false;
  constructor(private dashboardService: DashboardService) {}

  ngOnInit(): void {
    this.dashboardService.getOperationCount().subscribe(count => this.operationCount = count);
    this.dashboardService.getGroupCount().subscribe(count => this.groupCount = count);
    this.dashboardService.getEmployerCount().subscribe(count => this.employerCount = count);
    this.dashboardService.getCompteCount().subscribe(count => this.compteCount = count);
    this.dashboardService.getClientCount().subscribe(count => this.clientCount = count);
    console.log(this.operationCount);
  }
}
