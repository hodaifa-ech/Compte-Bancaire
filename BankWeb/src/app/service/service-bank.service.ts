import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
const BASIC_URL = 'http://localhost:8081/api';
@Injectable({
  providedIn: 'root'
})
export class ServiceBankService {

  constructor(private http: HttpClient) {}

  // Function to get the list of clients
  getClients(): Observable<any> {
    return this.http.get(`${BASIC_URL}/clients`); // Template literal for clean URL
  }

  // Function to create a new client
  createClient(client: any): Observable<any> {
    return this.http.post(`${BASIC_URL}/clients`, client); // Template literal for clean URL
  }

   // Function to update an existing client
   updateClient(codeClient: number, client: any): Observable<any> {
    return this.http.put(`${BASIC_URL}/clients/${codeClient}`, client);
  }

  // Function to delete a client
  deleteClient(codeClient: number): Observable<any> {
    return this.http.delete(`${BASIC_URL}/clients/${codeClient}`);
  }
}
