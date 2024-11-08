import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const BASIC_URL = 'http://localhost:8081/api';
@Injectable({
  providedIn: 'root'
})
export class EmployeService {

  constructor(private http: HttpClient) { }
  
  getEmployees(): Observable<any[]> {
    return this.http.get<any[]>(`${BASIC_URL}/employees`);
  }

  getGroups(): Observable<any[]> {
    return this.http.get<any[]>(`${BASIC_URL}/groups`);
  }

  addEmployee(employeeData: any): Observable<any> {
    return this.http.post<any>(`${BASIC_URL}/employees/add`, employeeData);
  }

  assignEmployeeToGroup(employeeId: number, groupId: number): Observable<void> {
    return this.http.post<void>(`${BASIC_URL}/employees/${employeeId}/assign-to-group/${groupId}`, {});
  }

  updateEmployee(employeeId: number, employeeData: any): Observable<any> {
    return this.http.put<any>(`${BASIC_URL}/employees/${employeeId}`, employeeData);
  }

  deleteEmployee(employeeId: number): Observable<void> {
    return this.http.delete<void>(`${BASIC_URL}/employees/${employeeId}`);
  }
}
