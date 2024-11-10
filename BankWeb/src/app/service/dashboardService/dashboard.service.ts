import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  private baseUrl = 'http://localhost:8081/api';

  constructor(private http: HttpClient) {}

  getOperationCount(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/clients/count`);
  }

  getGroupCount(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/groups/count`);
  }

  getEmployerCount(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/employees/count`);
  }

  getCompteCount(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/compts/count`);
  }

  getClientCount(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/operation/count`);
  }
}
