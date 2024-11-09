import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { OperationService } from '../../service/serviceOperation/operation.service';
import { CompteService } from '../../service/serviceCompte/compte.service';

@Component({
  selector: 'app-operation',
  templateUrl: './operation.component.html',
  styleUrl: './operation.component.scss'
})
export class OperationComponent {
  operations: any[] = [];
Comptes: any[] = [];
  employees: any[] = [];
  versementForm: FormGroup;
  retraitForm: FormGroup;
  virementForm: FormGroup;
  currentPage: number = 1;
  // Modal visibility control
  showVersementModal: boolean = false;
  showRetraitModal: boolean = false;
  showVirementModal: boolean = false;

  constructor(private operationService: OperationService, private fb: FormBuilder,private compteService: CompteService) {
    this.versementForm = this.fb.group({
      compteId: ['', Validators.required],
      employeId: ['', Validators.required],
      montant: ['', [Validators.required, Validators.min(0.01)]]
    });

    this.retraitForm = this.fb.group({
      compteId: ['', Validators.required],
      employeId: ['', Validators.required],
      montant: ['', [Validators.required, Validators.min(0.01)]]
    });

    this.virementForm = this.fb.group({
      compteSource: ['', Validators.required],
      compteId: ['', Validators.required],
      employeId: ['', Validators.required],
      montant: ['', [Validators.required, Validators.min(0.01)]]
    });
  }

  ngOnInit(): void {
    this.loadOperations();
    this.loadCompte();
    this.loadEmployees();
  }

  loadOperations(): void {
    this.operationService.getAllOperations().subscribe(data => {
      this.operations = data;
    });
  }

  loadCompte(): void {
    this.compteService.listAllComptes().subscribe(data => {
      this.Comptes = data;
    });
  }

  loadEmployees(): void {
    this.operationService.getEmployees().subscribe(data => {
      this.employees = data;
    });
  }

  // Modal open/close methods
  openVersementModal() {
    this.showVersementModal = true;
  }

  closeVersementModal() {
    this.showVersementModal = false;
  }

  openRetraitModal() {
    this.showRetraitModal = true;
  }

  closeRetraitModal() {
    this.showRetraitModal = false;
  }

  openVirementModal() {
    this.showVirementModal = true;
  }

  closeVirementModal() {
    this.showVirementModal = false;
  }

  // Form submit methods
  submitVersement(): void {
    if (this.versementForm.valid) {
      this.operationService.versement(this.versementForm.value).subscribe(response => {
        this.loadOperations();
        this.closeVersementModal();
      });
    }
  }

  submitRetrait(): void {
    if (this.retraitForm.valid) {
      this.operationService.retrait(this.retraitForm.value).subscribe(response => {
        this.loadOperations();
        this.closeRetraitModal();
      });
    }
  }

  submitVirement(): void {
    if (this.virementForm.valid) {
      this.operationService.versementTo(this.virementForm.value).subscribe(response => {
        this.loadOperations();
        this.closeVirementModal();
      });
    }
  }
}
