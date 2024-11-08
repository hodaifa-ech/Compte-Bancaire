import { Component } from '@angular/core';
import { CompteService } from '../../service/serviceCompte/compte.service';
import { EmployeService } from '../../service/serviceEmploye/employe.service';
import { ServiceBankService } from '../../service/service-bank.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-compte',
  templateUrl: './compte.component.html',
  styleUrl: './compte.component.scss'
})
export class CompteComponent {
  comptes: any[] = [];
  clients: any[] = [];
  employees: any[] = [];
  compteForm: FormGroup;
  showAddComptePopup: boolean = false;
  showDisplayComptePopup: boolean = false; // New property for display mode
  isEditMode: boolean = false;

  constructor(
    private compteService: CompteService,
    private employeService: EmployeService,
    private clientService: ServiceBankService,
    private fb: FormBuilder
  ) {
    this.compteForm = this.fb.group({
      codeCompte: ['', Validators.required],
      dateCreation: ['', Validators.required],
      clientId: ['', Validators.required],
      employeId: ['', Validators.required],
      type: ['', Validators.required],
      solde: ['', Validators.required],
      taux: [{ value: '', disabled: true }],
      decouvert: [{ value: '', disabled: true }],
      nomEmploye: [''],
     nomClient: ['']

    });
  }

  ngOnInit(): void {
    this.loadComptes();
    this.loadClients();
    this.loadEmployees();

    this.compteForm.get('type')?.valueChanges.subscribe((type) => {
      if (type === 'CE') {
        this.compteForm.get('taux')?.enable();
        this.compteForm.get('decouvert')?.disable();
        this.compteForm.get('decouvert')?.reset();
      } else if (type === 'CC') {
        this.compteForm.get('taux')?.disable();
        this.compteForm.get('taux')?.reset();
        this.compteForm.get('decouvert')?.enable();
      }
    });
  }

  loadComptes(): void {
    this.compteService.listAllComptes().subscribe(data => {
      this.comptes = data;
    });
  }

  loadClients(): void {
    this.clientService.getClients().subscribe(data => {
      this.clients = data;
    });
  }

  loadEmployees(): void {
    this.employeService.getEmployees().subscribe(data => {
      this.employees = data;
    });
  }

  toggleAddComptePopup(): void {
    this.showAddComptePopup = !this.showAddComptePopup;
    if (!this.showAddComptePopup) {
      this.compteForm.reset();
      this.isEditMode = false;
    }
  }

  editCompte(compte: any): void {
    this.isEditMode = true;
    this.compteForm.patchValue({
      codeCompte: compte.codeCompte,
      dateCreation: compte.dateCreation,
      clientId: compte.clientId,
      employeId: compte.employeId,
      type: compte.type,
      solde: compte.solde,
      taux: compte.taux || '',
      decouvert: compte.decouvert || ''
    });
    this.showAddComptePopup = true;
  }

  addCompte(): void {
    if (this.compteForm.valid) {
      const newCompte: any = this.compteForm.value;
      this.compteService.addCompte(newCompte).subscribe(() => {
        this.loadComptes();
        this.toggleAddComptePopup();
      });
    }
  }

  updateCompte(): void {
    if (this.compteForm.valid) {
      const updatedCompte = this.compteForm.value;
      const codeCompte = this.compteForm.get('codeCompte')?.value;
      this.compteService.updateCompte(codeCompte, updatedCompte).subscribe(() => {
        this.loadComptes();
        this.toggleAddComptePopup();
      });
    }
  }

  deleteCompte(codeCompte: number): void {
    Swal.fire({
      title: 'Are you sure?',
      text: 'You won\'t be able to revert this!',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes, delete it!',
      cancelButtonText: 'No, keep it'
    }).then((result) => {
      if (result.isConfirmed) {
        this.compteService.deleteCompte(codeCompte).subscribe(() => {
          Swal.fire('Deleted!', 'Your compte has been deleted.', 'success');
          this.loadComptes();
        }, error => {
          Swal.fire('Error!', 'There was a problem deleting the compte.', 'error');
        });
      }
    });
  }

  // New method to show account details in the popup
  showCompteDetails(codeCompte: number): void {
    this.compteService.findCompteByCode(codeCompte).subscribe(compte => {
      this.compteForm.patchValue({
        codeCompte: compte.codeCompte,
        dateCreation: compte.dateCreation,
        
        type: compte.type,
        solde: compte.solde,
        taux: compte.taux || '',
        decouvert: compte.decouvert || '',
        nomEmploye: compte.nomEmploye,
        nomClient: compte.nomClient,
      });
      this.showDisplayComptePopup = true; // Show the details popup
      this.showAddComptePopup = false;    // Hide the add/edit popup if open
    });
  }

  closeDisplayPopup(): void {
    this.showDisplayComptePopup = false;
  }
}