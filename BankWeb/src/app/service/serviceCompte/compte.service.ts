import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class CompteService {

  private apiUrl = 'http://localhost:8081/api/compts';

  constructor(private http: HttpClient) {}

  // Get all comptes
  listAllComptes(): Observable<any> {
    return this.http.get(this.apiUrl);
  }

  // Add a new compte
  addCompte(compte: any): Observable<any> {
    return this.http.post(this.apiUrl, compte);
  }

  updateCompte(codeCompte: number, compte: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/${codeCompte}`, compte);
  }

  // Delete a compte
  deleteCompte(codeCompte: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${codeCompte}`);
  }

   // Fetch a Compte by its code
   findCompteByCode(codeCompte: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/${codeCompte}`);
  }

  findComptesByClientId(clientId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/client/${clientId}`);
  }
}
