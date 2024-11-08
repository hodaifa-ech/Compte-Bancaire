import { Component } from '@angular/core';
import { CompteService } from '../../service/serviceCompte/compte.service';
import { EmployeService } from '../../service/serviceEmploye/employe.service';
import { ServiceBankService } from '../../service/service-bank.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

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
      decouvert: [{ value: '', disabled: true }]
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
    }
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
}
