import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ServiceBankService } from '../../service/service-bank.service';
import Swal from 'sweetalert2';
import { CompteService } from '../../service/serviceCompte/compte.service';
import { OperationService } from '../../service/serviceOperation/operation.service';
@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrl: './client.component.scss'
})
export class ClientComponent {
  currentPage: number = 1;
  page = 1; // For pagination
  clients: any[] = [];
  clientForm!: FormGroup;
  showForm: boolean = false;
  currentClient: any = null;
  operations: any[] = []; // Store operations for the selected client
  showOperationsModal: boolean = false; // Toggle to display operations modal
  comptes: any[] = []; // Store comptes for the selected client
  showComptesModal: boolean = false; // Toggle to display comptes modal
  constructor(private clientService: ServiceBankService, private fb: FormBuilder,private compteService:CompteService,private opertaionService: OperationService) {
    this.clientForm = this.fb.group({
      nomClient: [
        '',
        [
          Validators.required,
          Validators.pattern('^[a-zA-Z ]*$'), // Only allows letters and spaces
        ],
      ],
    });
  }

  ngOnInit(): void {
    this.loadClients();
  }
  // Fetch list of clients
  loadClients(): void {
    this.clientService.getClients().subscribe((data) => {
      this.clients = data;
    });
  }

 // Show the form to add or update a client
 openForm(client: any = null): void {
  if (client) {
    this.currentClient = client; // Set current client for updating
    this.clientForm.patchValue({
      nomClient: client.nomClient
    });
  } else {
    this.currentClient = null; // No client selected for adding
    this.clientForm.reset();
  }
  this.showForm = true;
}

// Close the form
closeForm(): void {
  this.showForm = false;
  this.clientForm.reset();
  this.currentClient = null;
}
openComptesModal(clientId: number): void {
  this.compteService.findComptesByClientId(clientId).subscribe((data) => {
    this.comptes = data; // Store comptes for display
    this.showComptesModal = true;
  });
}

closeComptesModal(): void {
  this.showComptesModal = false;
  this.comptes = [];
}
// Submit the form to create or update a client
onSubmit(): void {
  if (this.clientForm.valid) {
    if (this.currentClient) {
      // Update client
      this.clientService.updateClient(this.currentClient.codeClient, this.clientForm.value).subscribe((updatedClient) => {
        const index = this.clients.findIndex(client => client.codeClient === updatedClient.codeClient);
        this.clients[index] = updatedClient; // Update the client in the list
        this.closeForm();
      });
    } else {
      // Create new client
      this.clientService.createClient(this.clientForm.value).subscribe((newClient) => {
        this.clients.push(newClient); // Add the new client to the list
        this.closeForm();
      });
    }
  }
}

// Delete a client
deleteClient(client: any): void {
  Swal.fire({
    title: 'Are you sure?',
    text: 'Do you really want to delete this client?',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#d33',
    cancelButtonColor: '#3085d6',
    confirmButtonText: 'Yes, delete it!',
  }).then((result) => {
    if (result.isConfirmed) {
      this.clientService.deleteClient(client.codeClient).subscribe(() => {
        this.clients = this.clients.filter(c => c.codeClient !== client.codeClient);
        Swal.fire('Deleted!', 'The client has been deleted.', 'success');
      });
    }
  });
}

openOperationsModal(clientId: number): void {
  this.opertaionService.getOperationsByClientId(clientId).subscribe((data) => {
    this.operations = data; // Store operations for display
    this.showOperationsModal = true;
  });
}

// Close the operations modal
closeOperationsModal(): void {
  this.showOperationsModal = false;
  this.operations = [];
}
}
