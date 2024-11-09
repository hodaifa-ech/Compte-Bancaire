import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OperationService {

  private baseUrl = 'http://localhost:8081/api';

  constructor(private http: HttpClient) {}
  getAllOperations(): Observable<any> {
    return this.http.get(`${this.baseUrl}/operation`);
  }

  getClients(): Observable<any> {
    return this.http.get(`${this.baseUrl}/clients`);
  }

  getEmployees(): Observable<any> {
    return this.http.get(`${this.baseUrl}/employees`);
  }

  versement(versmentDto: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/operation/versement`, versmentDto);
  }

  retrait(retraitDto: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/operation/retrait`, retraitDto);
  }

  versementTo(versmentDto: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/operation/versementTo`, versmentDto);
  }

}
