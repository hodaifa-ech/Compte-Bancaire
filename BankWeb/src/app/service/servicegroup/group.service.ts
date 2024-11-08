import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GroupService {

  private apiUrl = 'http://localhost:8081/api/groups'; // Update this to match your backend URL

  constructor(private http: HttpClient) {}

  getAllGroups(): Observable<any> {

    return this.http.get(this.apiUrl);
  }

  addGroup(group: any): Observable<any> {

    return this.http.post(`${this.apiUrl}/add`, group);
  }
}
