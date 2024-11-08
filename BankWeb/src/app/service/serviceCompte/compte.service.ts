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
}
