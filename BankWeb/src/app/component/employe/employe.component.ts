import { Component } from '@angular/core';
import { EmployeService } from '../../service/serviceEmploye/employe.service';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-employe',
  templateUrl: './employe.component.html',
  styleUrl: './employe.component.scss'
})
export class EmployeComponent {
  employees: any[] = [];
  groups: any[] = [];
  showAddEmployeeModal: boolean = false;
  showEditEmployeeModal: boolean = false;
  showAssignGroupModal: boolean = false;
  newEmployeeName: string = '';
  editEmployeeName: string = '';
  selectedEmployeeId: number | null = null;
  selectedGroupId: number | null = null;
  isFormValid: boolean = false;
  isEditFormValid: boolean = false;
  employeeToEdit: any = null;
  currentPage: number = 1;
  newEmployeeSupervisorId: number | null = null; // Track selected supervisor for new employee
editEmployeeSupervisorId: number | null = null;
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

  openEditEmployeeModal(employee: any): void {
    this.showEditEmployeeModal = true;
    this.editEmployeeName = employee.nomEmploye;
    this.employeeToEdit = employee;
    this.validateEditForm();
  }

  openAssignGroupModal(): void {
    this.showAssignGroupModal = true;
    this.selectedEmployeeId = null;
    this.selectedGroupId = null;
  }

  closeModal(): void {
    this.showAddEmployeeModal = false;
    this.showEditEmployeeModal = false;
    this.showAssignGroupModal = false;
  }

  validateForm(): void {
    this.isFormValid = this.newEmployeeName.trim().length > 0;
  }

  validateEditForm(): void {
    this.isEditFormValid = this.editEmployeeName.trim().length > 0;
  }

  addEmployee(): void {
    const employeeData = { nomEmploye: this.newEmployeeName ,
      employeSupId: this.newEmployeeSupervisorId || null
    };

    console.log(employeeData);
    this.employeeService.addEmployee(employeeData).subscribe(() => {
      this.loadEmployees();
      this.closeModal();
    });
  }

  updateEmployee(): void {
    if (this.employeeToEdit) {
      const updatedData = { nomEmploye: this.editEmployeeName,
        employeSupId: this.editEmployeeSupervisorId || null
       };
      this.employeeService.updateEmployee(this.employeeToEdit.codeEmploye, updatedData).subscribe(() => {
        this.loadEmployees();
        this.closeModal();
      });
    }
  }

  deleteEmployee(employeeId: number): void {
  Swal.fire({
    title: 'Are you sure?',
    text: "You won't be able to revert this!",
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: 'Yes, delete it!'
  }).then((result) => {
    if (result.isConfirmed) {
      this.employeeService.deleteEmployee(employeeId).subscribe({
        next: () => {
          this.loadEmployees();
          Swal.fire('Deleted!', 'The employee has been deleted.', 'success');
        },
        error: (error) => {
          console.error('Error deleting employee:', error);
          Swal.fire('Error!', 'There was a problem deleting the employee.', 'error');
        }
      });
    }
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
