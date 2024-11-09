import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { OperationService } from '../../service/serviceOperation/operation.service';

@Component({
  selector: 'app-operation',
  templateUrl: './operation.component.html',
  styleUrl: './operation.component.scss'
})
export class OperationComponent {
  operations: any[] = [];
  clients: any[] = [];
  employees: any[] = [];
  versementForm: FormGroup;
  retraitForm: FormGroup;
  virementForm: FormGroup;

  constructor(private operationService: OperationService, private fb: FormBuilder) {
    this.versementForm = this.fb.group({
      client: ['', Validators.required],
      employee: ['', Validators.required],
      solde: ['', [Validators.required, Validators.min(0.01)]]
    });

    this.retraitForm = this.fb.group({
      client: ['', Validators.required],
      employee: ['', Validators.required],
      solde: ['', [Validators.required, Validators.min(0.01)]]
    });

    this.virementForm = this.fb.group({
      clientFrom: ['', Validators.required],
      clientTo: ['', Validators.required],
      employee: ['', Validators.required],
      solde: ['', [Validators.required, Validators.min(0.01)]]
    });
  }

  ngOnInit(): void {
    this.loadOperations();
    this.loadClients();
    this.loadEmployees();
  }

  loadOperations(): void {
    this.operationService.getAllOperations().subscribe(data => {
      this.operations = data;
    });
  }

  loadClients(): void {
    this.operationService.getClients().subscribe(data => {
      this.clients = data;
    });
  }

  loadEmployees(): void {
    this.operationService.getEmployees().subscribe(data => {
      this.employees = data;
    });
  }

  submitVersement(): void {
    if (this.versementForm.valid) {
      this.operationService.versement(this.versementForm.value).subscribe(() => this.loadOperations());
    }
  }

  submitRetrait(): void {
    if (this.retraitForm.valid) {
      this.operationService.retrait(this.retraitForm.value).subscribe(() => this.loadOperations());
    }
  }

  submitVirement(): void {
    if (this.virementForm.valid) {
      this.operationService.versementTo(this.virementForm.value).subscribe(() => this.loadOperations());
    }
  }
}
