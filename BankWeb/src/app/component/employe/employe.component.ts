import { Component } from '@angular/core';
import { EmployeService } from '../../service/serviceEmploye/employe.service';

@Component({
  selector: 'app-employe',
  templateUrl: './employe.component.html',
  styleUrl: './employe.component.scss'
})
export class EmployeComponent {
  employees: any[] = [];
  groups: any[] = [];
  showAddEmployeeModal = false;
  showAssignGroupModal = false;
  newEmployeeName = '';
  isFormValid = false;
  selectedEmployeeId: number | null = null;
  selectedGroupId: number | null = null;

  constructor(private employeeService: EmployeService) {}

  ngOnInit(): void {
    this.loadEmployees();
    this.loadGroups();
  }

  loadEmployees(): void {
    this.employeeService.getEmployees().subscribe(data => {
      this.employees = data;
    });
  }

  loadGroups(): void {
    this.employeeService.getGroups().subscribe(data => {
      this.groups = data;
    });
  }

  openAddEmployeeModal(): void {
    this.showAddEmployeeModal = true;
    this.newEmployeeName = '';
    this.validateForm();
  }

  openAssignGroupModal(): void {
    this.showAssignGroupModal = true;
    this.selectedEmployeeId = null;
    this.selectedGroupId = null;
  }

  closeModal(): void {
    this.showAddEmployeeModal = false;
    this.showAssignGroupModal = false;
  }

  validateForm(): void {
    this.isFormValid = this.newEmployeeName.trim().length > 0;
  }

  addEmployee(): void {
    const employeeData = { nomEmploye: this.newEmployeeName };
    this.employeeService.addEmployee(employeeData).subscribe(() => {
      this.loadEmployees();
      this.closeModal();
    });
  }

  assignEmployeeToGroup(): void {
    if (this.selectedEmployeeId && this.selectedGroupId) {
      this.employeeService.assignEmployeeToGroup(this.selectedEmployeeId, this.selectedGroupId).subscribe(() => {
        this.loadEmployees();
        this.closeModal();
      });
    }
  }
}
